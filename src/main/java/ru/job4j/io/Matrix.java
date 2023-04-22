package ru.job4j.io;

import java.io.FileOutputStream;

public class Matrix {
    public static void main(String[] args) {
        int size = 9;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                int res = (i + 1) * (j + 1);
                stringBuilder.append(res).append(System.lineSeparator());
                System.out.println(res);
            }
        }
        try (FileOutputStream out = new FileOutputStream("data/multiple.txt")) {
            out.write(stringBuilder.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
