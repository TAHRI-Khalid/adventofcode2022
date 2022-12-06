package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Day5 {
    public static final Path PATH = Path.of("data/day5.txt");

    public static void main(String[] args) throws IOException {
        var data = Files.readAllLines(PATH);
        var indexOfCrate = (int) data.stream().filter(str -> str.contains("[")).count();

        var crateData = data.subList(0, indexOfCrate);
        var moves = data.subList(indexOfCrate + 2, data.size());

        var stacks = fillTheStacks(crateData);

        for (var line : moves) {
            var m = line.split(" ");
            int num = Integer.parseInt(m[1]);
            int from = Integer.parseInt(m[3]);
            int to = Integer.parseInt(m[5]);

            moveCratesFromStackToStack(stacks, num, from, to);
        }
        var result = new StringBuilder();
        for (int i = 1; i <= stacks.size(); i++) {
            result.append(stacks.get(i).pop());
        }
        System.out.println(result);

    }

    private static void moveCratesFromStackToStack(Map<Integer, Stack<String>> stacks, int num, int from, int to) {
        var intermediatStack = new Stack<String>();
        for (int i = 0; i < num; i++) {
            intermediatStack.push(stacks.get(from).pop());
        }
        for (int i = 0; i < num; i++) {
            stacks.get(to).push(intermediatStack.pop());
        }
    }

    private static Map<Integer, Stack<String>> fillTheStacks(List<String> crateData) {
        var stacks = new HashMap<Integer, Stack<String>>();
        for (int i = crateData.size() - 1; i >= 0; i--) {
            var line = crateData.get(i);
            for (int J = 0; J < line.length(); J += 4) {
                var crate = line.substring(J + 1, J + 2);
                if (crate.isBlank()) {
                    continue;
                }
                var index = (J / 4) + 1;
                if (stacks.containsKey(index)) {
                    stacks.get(index).push(crate);
                } else {
                    var stack = new Stack<String>();
                    stack.push(crate);
                    stacks.putIfAbsent(index, stack);
                }
            }
        }
        return stacks;
    }
}