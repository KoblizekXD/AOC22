package day3;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        String priorities = "abcdefghijklmnopqrstuvwxyz" + "abcdefghijklmnopqrstuvwxyz".toUpperCase();
        List<String> lines = Files.readAllLines(Path.of("src/day3/input"));
        int sum1 = 0;

        for (int i = 0; i < lines.size(); i += 3) {
            for (char c : lines.get(i).toCharArray()) {
                if (lines.get(i+1).contains(String.valueOf(c)) && lines.get(i+2).contains(String.valueOf(c))) {
                    System.out.println(c);
                    sum1 = sum1 + (priorities.indexOf(c)+1);
                    break;
                }
            }
        }
        System.out.println(sum1);
        System.out.println("--------------------------------------------");
        int sum = 0;
        for (String line : lines) {
            String[] compartments = new String[2];
            compartments[0] = line.substring(0, (line.length()/2));
            compartments[1] = line.substring(line.length()/2);
            List<Character> used = new ArrayList<>();
            for (char c : compartments[0].toCharArray()) {
                if (compartments[1].contains(String.valueOf(c)) && !used.contains(c)) {
                    sum = sum + (priorities.indexOf(c)+1);
                    System.out.println("[CHAR]: " + c + ", " + (priorities.indexOf(c)+1));
                    used.add(c);
                }
            }
        }
        System.out.println(sum);
    }
    public static String listToString(List<String> strings) {
        StringBuilder str = new StringBuilder();

        for (String string : strings) {
            str.append(string);
        }
        return str.toString();
    }
}
