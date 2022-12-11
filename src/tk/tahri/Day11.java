package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Day11 {
    public static final Path PATH = Path.of("data/day11.txt");

    public static void main(String[] args) throws IOException {

        var monkeys = Arrays.stream(Files.readString(PATH).split("\n\n"))
                .map(Monkey::new).toList();

        for (int round = 0; round < 10_000; round++) {
            for (int i = 0; i < monkeys.size(); i++) {
                var monkey = monkeys.get(i);
                monkey.countInspectedItems();

                for (int j = 0; j < monkey.items.size(); j++) {
                    var item = monkey.items.get(j);
                    //item %= 96577;
                    item %= 9699690;

                    item = monkey.applyWorryLevel(item);
                    //item = Math.floorDiv(item, 3);

                    if (item % monkey.divisibleBy == 0) {
                        monkeys.get(monkey.testIsTrueThrowToMonkey).items.add(item);
                    } else {
                        monkeys.get(monkey.testIsFalseThrowToMonkey).items.add(item);
                    }
                }
                monkey.items.clear();
            }

        }
        monkeys.forEach(monkey -> System.out.println(monkey.items));

        var level = monkeys.stream()
                .map(value -> value.inspectedItems)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList();

        System.out.println(level.get(0) * level.get(1));
    }
}

class Monkey {

    public List<Long> items;
    private String op;
    public final int divisibleBy;
    public final int testIsTrueThrowToMonkey;
    public final int testIsFalseThrowToMonkey;
    public long inspectedItems = 0;

    public Monkey(String note) {
        var obs = note.split("\n");
        items = Arrays.stream(obs[1].replace("Starting items: ", "")
                        .split(","))
                .map(String::trim)
                .map(Long::parseLong)
                .collect(Collectors.toList());

        op = obs[2].replace("Operation: new = old ", "").trim();
        divisibleBy = Integer.parseInt(obs[3].replace("Test: divisible by", "").trim());
        testIsTrueThrowToMonkey = Integer.parseInt(obs[4].replace("If true: throw to monkey ", "").trim());
        testIsFalseThrowToMonkey = Integer.parseInt(obs[5].replace("If false: throw to monkey ", "").trim());

    }

    public long applyWorryLevel(long numbre) {
        var ops = op.split(" ");
        if (ops[0].equals("*")) {
            if (ops[1].equals("old")) {
                return numbre * numbre;
            }
            return numbre * Integer.parseInt(ops[1]);
        } else {
            return numbre + Integer.parseInt(ops[1]);
        }
    }

    public void countInspectedItems() {
        this.inspectedItems += items.size();
    }

}