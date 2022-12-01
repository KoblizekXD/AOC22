package day1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        List<String> list = Files.readAllLines(Path.of("src/day1/input"));
        int highest = 0;
        int secHighest = 0;
        int thirdHighest = 0;
        int temp = 0;
        int temp1 = 0;
        for (String s : list) {
            if (!s.equals("")) {
                temp = temp + Integer.parseInt(s);
            } else {
                if (temp > highest) {
                    temp1 = highest;
                    highest = temp;
                    int temp2 = secHighest;
                    secHighest = temp1;
                    thirdHighest = temp2;
                } else if (temp > secHighest) {
                    temp1 = secHighest;
                    secHighest = temp;
                    thirdHighest = temp1;
                } else if (temp > thirdHighest) {
                    thirdHighest = temp;
                }
                temp = 0;
            }
        }
        System.out.println("Highest is: " + highest);
        System.out.println(secHighest);
        System.out.println(thirdHighest);
        System.out.println("Soucet: " + (highest + secHighest + thirdHighest));
    }
}
