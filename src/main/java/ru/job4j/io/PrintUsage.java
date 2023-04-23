package ru.job4j.io;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream printStream = new PrintStream(new FileOutputStream("data/print.txt"));
            PrintWriter printWriter = new PrintWriter("data/write.txt")) {
            printStream.println("From PrintStream to OutputStream");
            printStream.write("second string".getBytes());
            printWriter.println("PrintWriter example");
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
