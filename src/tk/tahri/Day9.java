package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;

public class Day9 {
    public static final Path PATH = Path.of("data/day9.txt");

    public static void main(String[] args) throws IOException {
        var set=new HashSet<Position>();
        var head = new Position(0, 0);
        var tail = new Position(0, 0);
        var data = Files.readAllLines(PATH).stream()
                .map(s -> s.split(" "))
                .map(strings -> new Move(strings[0], Integer.parseInt(strings[1])))
                .toList();

        for (var move : data) {

            for (int i = 0; i < move.step(); i++) {
                head = head.move(move.direction());
                if (Math.pow(tail.x() - head.x(), 2) + Math.pow(tail.y() - head.y(), 2) > 2) {
                    if (move.direction().equals("R")) {
                        tail = new Position(head.x() - 1, head.y());
                    } else if (move.direction().equals("L")) {
                        tail = new Position(head.x() + 1, head.y());
                    } else if (move.direction().equals("U")) {
                        tail = new Position(head.x(), head.y() - 1);
                    } else if (move.direction().equals("D")) {
                        tail = new Position(head.x(), head.y() + 1);
                    }
                }
                set.add(tail);
            }
        }
        System.out.println(head);
        System.out.println(tail);
        System.out.println(set.size());
    }
}

record Position(int x, int y) {

    public Position move(String dir) {
        var newX = x;
        var newY = y;
        if (dir.equals("R")) {
            newX++;
        } else if (dir.equals("L")) {
            newX--;
        } else if (dir.equals("U")) {
            newY++;
        } else if (dir.equals("D")) {
            newY--;
        }
        return new Position(newX, newY);
    }
}

record Move(String direction, int step) {
}