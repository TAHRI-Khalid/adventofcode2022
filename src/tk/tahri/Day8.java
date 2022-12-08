package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day8 {
    public static final Path PATH = Path.of("data/day8.txt");

    public static List<String> data;

    public static void main(String[] args) throws IOException {
        data = Files.readAllLines(PATH);
        int highestScenicScore = 0;
        for (int row = 0; row < data.size(); row++) {
            for (int col = 0; col < data.get(row).length(); col++) {
                var score = isVisibleFromLeft(col, row) * isVisibleFromRight(col, row) *
                        isVisibleFromTop(col, row) * isVisibleFromBottom(col, row);
                if (score > highestScenicScore) highestScenicScore = score;
            }
        }

        System.out.println(highestScenicScore);
    }

    public static int isVisibleFromLeft(int col, int row) {
        var value = getValue(col, row);
        var sumOfvisibleTree = 0;
        for (int i = col - 1; i >= 0; i--) {
            sumOfvisibleTree++;
            if (value <= getValue(i, row)) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }

    public static int isVisibleFromRight(int col, int row) {
        var value = getValue(col, row);
        var sumOfvisibleTree = 0;
        for (int i = col + 1; i < data.get(row).length(); i++) {
            sumOfvisibleTree++;
            if (value <= getValue(i, row)) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }

    public static int isVisibleFromTop(int col, int row) {
        var value = getValue(col, row);
        var sumOfvisibleTree = 0;
        for (int i = row - 1; i >= 0; i--) {
            sumOfvisibleTree++;
            if (value <= getValue(col, i)) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }

    public static int isVisibleFromBottom(int col, int row) {
        var value = getValue(col, row);
        var sumOfvisibleTree = 0;
        for (int i = row + 1; i < data.size(); i++) {
            sumOfvisibleTree++;
            if (value <= getValue(col, i)) {
                return sumOfvisibleTree;
            }
        }
        return sumOfvisibleTree;
    }

    public static int getValue(int col, int row) {
        return Integer.parseInt(String.valueOf(data.get(row).charAt(col)));
    }
}