package day2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day2/input"));

        int score = 0;

        for (String line : lines) {
            char a = line.charAt(0);
            char b = line.charAt(2);

            if (b == 'X') {
                b = setLoose(a);
            } else if (b == 'Y') {
                b = setSamePosition(a);
            } else {
                b = setWin(a);
            }

            if (isWin(a, b)) {
                score = score + figureScoreIfTieOrWin(b, true);
            } else {
                if (isTie(a, b)) {
                    score = score + figureScoreIfTieOrWin(b, false);
                } else {
                    score = score + figureScoreIfLost(b);
                }
            }
        }
        System.out.println("Score is: " + score);
    }

    // A, X = rock
    // B, Y = paper
    // C, Z = scissors

    public static boolean isWin(char a, char b) {
        if (b == 'X' && a == 'C') return true;
        if (b == 'Y' && a == 'A') return true;
        if (b == 'Z' && a == 'B') return true;
        return false;
    }
    public static boolean isTie(char a, char b) {
        if (b == 'X' && a == 'A') return true;
        if (b == 'Y' && a == 'B') return true;
        if (b == 'Z' && a == 'C') return true;
        return false;
    }
    public static int figureScoreIfLost(char c) {
        int score = 0;
        if (c == 'X') score++;
        if (c == 'Y') score = score+2;
        if (c == 'Z') score = score+3;
        return score;
    }
    public static int figureScoreIfTieOrWin(char c, boolean win) {
        int score;
        if (win) {
            score = 6;
        } else {
            score = 3;
        }
        if (c == 'X') score++;
        if (c == 'Y') score = score+2;
        if (c == 'Z') score = score+3;

        return score;
    }
    public static char setSamePosition(char a) {
        if (a == 'A') return 'X';
        if (a == 'B') return 'Y';
        if (a == 'C') return 'Z';
        return '0';
    }
    public static char setLoose(char a) {
        if (a == 'A') return 'Z';
        if (a == 'B') return 'X';
        if (a == 'C') return 'Y';
        return '0';
    }
    public static char setWin(char a) {
        if (a == 'A') return 'Y';
        if (a == 'B') return 'Z';
        if (a == 'C') return 'X';
        return '0';
    }
}
