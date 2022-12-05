package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;


public class Day3 {
    public static final Path PATH = Path.of("data/day3.txt");

    public static void main(String[] args) throws IOException {
        Function<String, Character> findTheSameCaracter = line -> {
            var length = line.length();
            for (int i = 0; i < length / 2; i++) {
                for (int j = length / 2; j < length; j++) {
                    if (line.charAt(i) == line.charAt(j)) {
                        return line.charAt(i);
                    }
                }
            }
            return null;
        };

        Function<List<String>, Character> findTheSameCaracterInGroup = group -> {
            for (int i = 0; i < group.get(0).length(); i++) {
                var c = group.get(0).charAt(i);
                if (group.get(1).contains(String.valueOf(c)) && group.get(2).contains(String.valueOf(c))) {
                    return c;
                }
            }
            return null;
        };

        Function<Character, Integer> applyPriorities = caracter -> {
            if (Character.isLowerCase(caracter)) {
                return (int) caracter - 96;
            }
            return ((int) caracter - 65) + 27;
        };
        /*
        var data = Files.readAllLines(PATH)
                .stream()
                .map(findTheSameCaracter)
                .map(applayPriorities)
                .mapToInt(Integer::intValue)
                .sum();
         System.out.println(data);
         */

        var data = Files.readAllLines(PATH);

        var r = IntStream.iterate(0, i -> i < data.size(), i -> i + 3)
                .mapToObj(i -> data.subList(i, i + 3))
                .map(findTheSameCaracterInGroup)
                .map(applyPriorities)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(r);

    }
}