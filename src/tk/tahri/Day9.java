package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;

public class Day9 {
    public static final Path PATH = Path.of("data/day9.txt");

    public static void main(String[] args) throws IOException {
        var set = new HashSet<Position>();

        var rope = new ArrayList<Position>();
        for (int i = 0; i < 10; i++) {
            rope.add(new Position(0, 0));
        }

        var data = Files.readAllLines(PATH).stream()
                .map(s -> s.split(" "))
                .map(strings -> new Move(strings[0], Integer.parseInt(strings[1])))
                .toList();

        for (var move : data) {

            for (int step = 0; step < move.step(); step++) {
                var realHead = rope.get(0).moveTo(move.direction());
                rope.set(0, realHead);

                for (int j = 1; j < 10; j++) {
                    var head = rope.get(j - 1);
                    var tail = rope.get(j);
                    if (Math.pow(tail.x() - head.x(), 2) + Math.pow(tail.y() - head.y(), 2) > 2) {
                        tail = tail.follow(head);
                    }
                    rope.set(j, tail);
                }
                set.add(rope.get(9));
            }
        }
        System.out.println(set.size());
    }
}

record Position(int x, int y) {

    public Position moveTo(String dir) {
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

    public Position follow(Position position) {
        var dx = 0;
        var dy = 0;

        if (x == position.x) {
            dy = (position.y - y) / 2;
        } else if (y == position.y) {
            dx = (position.x - x) / 2;
        } else if (x < position.x == y < position.y) {
            dx = (position.x - x) / Math.abs(position.x - x);
            dy = (position.y - y) / Math.abs(position.y - y);
        } else if (x < position.x) {
            dx = 1;
            dy = -1;
        } else {
            dx = -1;
            dy = 1;
        }
        return new Position(x + dx, y + dy);
    }
}

record Move(String direction, int step) {
}