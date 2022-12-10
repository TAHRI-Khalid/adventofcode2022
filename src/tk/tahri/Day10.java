package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day10 {

    public static final Path PATH = Path.of("data/day10.txt");

    public static void main(String[] args) throws IOException {

        int register = 1;
        int cycle = 0;

        var data = Files.readAllLines(PATH).stream()
                .map(s -> s.split(" "))
                .map(strings -> {
                    if (strings[0].equals("noop")) {
                        return new instruction(strings[0], 0, 1);
                    } else {
                        return new instruction(strings[0], Integer.parseInt(strings[1]), 2);
                    }
                }).toList();

        for (var instruction : data) {
            for (int i = 0; i < instruction.cycle(); i++) {
                if (cycle % 40 == 0) {
                    System.out.println();
                }
                var pos = cycle % 40;

                if (register == pos || register - 1 == pos || register + 1 == pos) {
                    System.out.print("#");
                } else {
                    System.out.print(".");
                }
                cycle++;
            }
            register += instruction.value();
        }
    }

}

record instruction(String instruction, int value, int cycle) {
}