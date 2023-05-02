package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class CSVReader {
    public static void handle(ArgsName argsName) {
        String delimiter = argsName.get("delimiter");
        List<String> in = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(argsName.get("path")))) {
            while (sc.hasNextLine()) {
                in.add(sc.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<String> header = List.of(in.get(0).split(delimiter));
        List<Integer> indexFilter = Arrays.stream(argsName.get("filter")
                .split(","))
                .map(header::indexOf)
                .toList();
        StringBuilder strOut = new StringBuilder();
        for (String value : in) {
            String[] lineIn = value.split(delimiter);
            strOut.append(indexFilter.stream()
                            .map(s -> lineIn[s])
                            .collect(Collectors.joining(delimiter)))
                    .append(System.lineSeparator());
        }
        if ("stdout".equals(argsName.get("out"))) {
            System.out.print(strOut);
        } else {
            try (PrintWriter out = new PrintWriter(argsName.get("out"))) {
                out.print(strOut);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void validate(ArgsName argsName) {
        if (!argsName.get("path").endsWith(".csv")) {
            throw new IllegalArgumentException(
                    String.format("File name \"%s\" is not correct", argsName.get("path")));
        }
        if (!";".equals(argsName.get("delimiter"))) {
            throw new IllegalArgumentException(
                    "Delimiter is not equal \";\"");
        }
        if (!("stdout".equals(argsName.get("out"))
                && !Paths.get(argsName.get("out")).isAbsolute())) {
            throw new IllegalArgumentException(
                    "The value of \"out\" is not equal \"stdout\" or Path of file");
        }

        if (argsName.get("filter").length() < 1) {
            throw new IllegalArgumentException(
                    "The value of \"filter\" is not correct");
        }
    }

    public static void main(String[] args) {
        CSVReader csvReader = new CSVReader();
        ArgsName argsName = ArgsName.of(args);
        csvReader.validate(argsName);
        handle(argsName);
    }
}
