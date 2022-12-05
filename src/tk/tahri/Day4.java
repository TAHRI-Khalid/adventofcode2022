package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.IntStream;


public class Day4 {
    public static final Path PATH = Path.of("data/day4.txt");

    public static void main(String[] args) throws IOException {
        var data = Files.readAllLines(PATH)
                .stream()
                .map(Section::new)
                .filter(Section::HasOverlap)
                .count();

        System.out.println(data);
    }
}

class Section {
    public final int a1;
    public final int b1;
    public final int a2;
    public final int b2;

    Section(String str) {
        var paire = str.split(",");
        var paire1 = paire[0].split("-");
        var paire2 = paire[1].split("-");

        this.a1 = Integer.parseInt(paire1[0]);
        this.b1 = Integer.parseInt(paire1[1]);
        this.a2 = Integer.parseInt(paire2[0]);
        this.b2 = Integer.parseInt(paire2[1]);
    }

    public boolean HasOverlap() {
        //return a1<=a2 && b1>=b2 || a1>=a2 && b1<=b2;
        return a1 >= a2 && a1 <= b2 || b1 >= a2 && b1 <= b2 || a1 <= a2 && b1 >= b2 || a1 >= a2 && b1 <= b2;
    }
}