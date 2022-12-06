package day6;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String[] args) throws IOException {
        StringBuilder used = new StringBuilder();
        String line = Files.readString(Path.of("src/day6/input"));

        for (int i = 14; i < line.length(); i++) {
            String prev = line.substring(i-14, i);
            if (isUnique(prev)) {
                System.out.println(i);
                break;
            }
        }
    }
    public static boolean isUnique(String array) {
        boolean found = false;
        for (int i = 0; i < array.toCharArray().length; i++) {
            for (int i1 = 0; i1 < array.toCharArray().length; i1++) {
                if (i != i1 && array.charAt(i) == array.charAt(i1)) {
                    found = true;
                }
            }
        }
        return !found;
    }
}
