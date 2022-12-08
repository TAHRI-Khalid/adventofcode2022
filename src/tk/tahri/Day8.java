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
        int sumOfVisibleTree = 0;
        for (int row = 0; row < data.size(); row++) {
            for (int col = 0; col < data.get(row).length(); col++) {
                if (isVisibleFromLeft(col, row) || isVisibleFromRight(col, row) ||
                        isVisibleFromTop(col, row) || isVisibleFromBottom(col,row)) {
                    sumOfVisibleTree++;
                }
            }
        }
        System.out.println(sumOfVisibleTree);
    }

    public static boolean isVisibleFromLeft(int col, int row) {
        var value = getValue(col, row);
        for (int i = col - 1; i >= 0; i--) {
            if (value <= getValue(i, row)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVisibleFromRight(int col, int row) {
        var value = getValue(col, row);
        for (int i = col + 1; i < data.get(row).length(); i++) {
            if (value <= getValue(i, row)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVisibleFromTop(int col, int row) {
        var value = getValue(col, row);
        for (int i = row - 1; i >= 0; i--) {
            if (value <= getValue(col, i)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isVisibleFromBottom(int col, int row) {
        var value = getValue(col, row);
        for (int i = row + 1; i < data.size(); i++) {
            if (value <= getValue(col, i)) {
                return false;
            }
        }
        return true;
    }

    public static int getValue(int col, int row) {
        return Integer.parseInt(String.valueOf(data.get(row).charAt(col)));
    }
}