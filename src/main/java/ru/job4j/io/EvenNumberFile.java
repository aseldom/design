package ru.job4j.io;

import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {

    public static void main(String[] args) {
        StringBuilder text = new StringBuilder();
        try (FileInputStream in = new FileInputStream("data/even.txt")) {
            int read;
            while ((read = in.read()) != -1) {
                text.append((char) read);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        String[] string = text.toString().split(System.lineSeparator());
        for (String str : string) {
            if ((Integer.parseInt(str) % 2 == 0)) {
                System.out.println(str);
            }
        }
    }
}
