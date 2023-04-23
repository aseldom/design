package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream printStream = new PrintStream(new FileOutputStream("data/print.txt"))) {
            printStream.println("From PrintStream to OutputStream");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
