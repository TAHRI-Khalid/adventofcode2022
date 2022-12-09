package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class Day8 {
    public static final Path PATH = Path.of("data/day8.txt");

    public static Integer[][] data;

    public static void main(String[] args) throws IOException {

        //Convert data from string to Int[][]
        data = Files.readAllLines(PATH).stream()
                .map(line -> line.split(""))
                .map(strings -> Arrays.stream(strings).map(Integer::parseInt).toArray(Integer[]::new))
                .toArray(Integer[][]::new);


        int highestScenicScore = 0;
        for (int row = 0; row < data.length; row++) {
            for (int col = 0; col < data[row].length; col++) {
                var score = isVisibleFromLeft(col, row) * isVisibleFromRight(col, row) *
                        isVisibleFromTop(col, row) * isVisibleFromBottom(col, row);
                if (score > highestScenicScore) highestScenicScore = score;
            }
        }

        System.out.println(highestScenicScore);
    }

    public static int isVisibleFromLeft(int col, int row) {
        var value = data[row][col];
        var sumOfvisibleTree = 0;
        for (int i = col - 1; i >= 0; i--) {
            sumOfvisibleTree++;
            if (value <= data[row][i]) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }

    public static int isVisibleFromRight(int col, int row) {
        var value = data[row][col];
        var sumOfvisibleTree = 0;
        for (int i = col + 1; i < data[row].length; i++) {
            sumOfvisibleTree++;
            if (value <= data[row][i]) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }

    public static int isVisibleFromTop(int col, int row) {
        var value = data[row][col];
        var sumOfvisibleTree = 0;
        for (int i = row - 1; i >= 0; i--) {
            sumOfvisibleTree++;
            if (value <= data[i][col]) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }

    public static int isVisibleFromBottom(int col, int row) {
        var value = data[row][col];
        var sumOfvisibleTree = 0;
        for (int i = row + 1; i < data.length; i++) {
            sumOfvisibleTree++;
            if (value <= data[i][col]) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }
}