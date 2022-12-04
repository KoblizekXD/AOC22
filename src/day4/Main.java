package day4;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of("src/day4/input"));

        List<ElfPair> pairs = new ArrayList<>();
        for (String line : lines) {
            String first = line.split(",")[0];
            int[] fData = { Integer.parseInt(first.split("-")[0]), Integer.parseInt(first.split("-")[1]) };
            String second = line.split(",")[1];
            int[] sData = { Integer.parseInt(second.split("-")[0]), Integer.parseInt(second.split("-")[1]) };
            pairs.add(new ElfPair(fData, sData));
        }

        int i = 0;
        for (ElfPair pair : pairs) {
            if (pair.areOverlapping()) {
                i++;
                System.out.println(pair.toString());
            };
        }

        System.out.println(i);
    }
    public static int[] convert(String[] array) {
        int[] ints = new int[2];

        ints[0] = Integer.parseInt(array[0]);
        ints[1] = Integer.parseInt(array[1]);

        return ints;
    }
}
