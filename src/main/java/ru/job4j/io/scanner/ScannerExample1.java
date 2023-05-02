package ru.job4j.io.scanner;

import java.io.CharArrayReader;
import java.util.Scanner;

public class ScannerExample1 {
    public static void main(String[] args) {
        var ls = System.lineSeparator();
        var data = String.join(ls,
                "1 a 2",
                "233,09 b 3",
                "4 c 5");
        System.out.println(data);

        Scanner scanner = new Scanner(new CharArrayReader(data.toCharArray()));

        while (scanner.hasNext()) {
            if (scanner.hasNextInt()) {
                System.out.println(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
    }
}