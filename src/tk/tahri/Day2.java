package tk.tahri;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day2 {

    public static final Path PATH = Path.of("data/day2.txt");

    public static void main(String[] args) throws IOException {

        var data = Files.readAllLines(PATH)
                .stream()
                .map(str -> str.split(" "))
                .map(strs -> new Round(letterToShape(strs[0]), letterToShape(strs[1])))
                .mapToInt(Round::score2)
                .sum();

        System.out.println(data);
    }

    private static Shape letterToShape(String lettre) {
        return switch (lettre) {
            case "A", "X" -> Shape.ROCK;
            case "B", "Y" -> Shape.PAPER;
            case "C", "Z" -> Shape.SCISSORS;
            default -> null;
        };
    }
}

enum Shape {
    ROCK("X", 1),
    PAPER("Y", 2),
    SCISSORS("Z", 3);

    final String input;
    final int score;

    Shape(String input, int score) {
        this.input = input;
        this.score = score;
    }
}

record Round(Shape opponent, Shape player) {
    public int score() {
        var score = player.score;
        if (opponent.equals(player)) {
            return score + 3;
        }
        if (opponent.equals(Shape.ROCK) && player.equals(Shape.PAPER)
                || opponent.equals(Shape.PAPER) && player.equals(Shape.SCISSORS)
                || opponent.equals(Shape.SCISSORS) && player.equals(Shape.ROCK)) {
            return score + 6;
        }
        return score;
    }

    public int score2() {
        int score = (player().score - 1) * 3;
        if (player.input.equals("X")) {
            return score + switch (opponent) {
                case ROCK -> Shape.SCISSORS.score;
                case PAPER -> Shape.ROCK.score;
                case SCISSORS -> Shape.PAPER.score;
            };
        }
        if (player.input.equals("Y")) {
            return score + opponent.score;
        }
        return score + switch (opponent) {
            case ROCK -> Shape.PAPER.score;
            case PAPER -> Shape.SCISSORS.score;
            case SCISSORS -> Shape.ROCK.score;
        };
    }
}