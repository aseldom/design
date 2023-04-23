package ru.job4j.io;

import java.io.*;

public class Buffered {
    public static void main(String[] args) {
        try (BufferedInputStream in = new BufferedInputStream(new FileInputStream("data/multiple.txt"));
             BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream("data/out.txt"))) {
            out.write(in.readAllBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
