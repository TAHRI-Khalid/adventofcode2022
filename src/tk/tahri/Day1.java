package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.ToIntFunction;

public class Day1 {
    public static void main(String[] args) throws IOException {
        ToIntFunction<String> sumCalories = s -> Arrays.stream(s.split("\n"))
                .mapToInt(Integer::parseInt)
                .sum();

        var data = Arrays.stream(Files.readString(Path.of("data/day1.txt"))
                .split("\n\n")).toList();

        /*
        var result = data.stream().map(sumCalories::applyAsInt)
                .mapToInt(Integer::intValue)
                .max();
         */

        var result = data.stream().map(sumCalories::applyAsInt)
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(Integer::intValue)
                .sum();
        System.out.println(result);
    }

}