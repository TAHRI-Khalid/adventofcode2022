package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day6 {
    public static final Path PATH = Path.of("data/day6.txt");

    public static void main(String[] args) throws IOException {
        var data = Files.readString(PATH);

        final int packet = 14;
        for (int i = 0; i < data.length() - 3; i++) {
            var str = data.substring(i, i + packet);
            if (!hasDuplicateCaracters(str)) {
                System.out.println(i + packet);
                break;
            }
        }
    }

    public static boolean hasDuplicateCaracters(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) {
                    return true;
                }
            }
        }
        return false;
    }
}