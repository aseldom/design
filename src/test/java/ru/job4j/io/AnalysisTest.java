package ru.job4j.io;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.*;

class AnalysisTest {

    @Test
    void fileTest1(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter in = new PrintWriter(source)) {
            in.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "500 10:59:01\n"
                    + "400 11:01:02\n"
                    + "300 11:02:02");
        }
        Analysis analysis = new Analysis();
        analysis.unavailable(source.toString(), target.toString());

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader out = new BufferedReader(new FileReader(target))) {
            out.lines().forEach(stringBuilder::append);
        }
        assertThat("10:57:01;11:02:02;").isEqualTo(stringBuilder.toString());
    }

    @Test
    void fileTest2(@TempDir Path tempDir) throws IOException {
        File source = tempDir.resolve("source.txt").toFile();
        File target = tempDir.resolve("target.csv").toFile();
        try (PrintWriter in = new PrintWriter(source)) {
            in.println("200 10:56:01\n"
                    + "500 10:57:01\n"
                    + "400 10:58:01\n"
                    + "300 10:59:01\n"
                    + "500 11:01:02\n"
                    + "200 11:02:02");
        }
        Analysis analysis = new Analysis();
        analysis.unavailable(source.toString(), target.toString());

        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader out = new BufferedReader(new FileReader(target))) {
            out.lines().forEach(stringBuilder::append);
        }
        assertThat("10:57:01;10:59:01;11:01:02;11:02:02;").isEqualTo(stringBuilder.toString());
    }
}