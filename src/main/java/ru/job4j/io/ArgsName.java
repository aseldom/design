package ru.job4j.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class ArgsName {
    private final Map<String, String> values = new HashMap<>();

    public String get(String key) {
        /* TODO add the necessary checks. */
        if (!values.containsKey(key)) {
            throw new IllegalArgumentException(String.format(
                    "This key: '%s' is missing", key));
        }
        return values.get(key);
    }

    private void parse(String[] args) {
        /* TODO parse args to values. */
        for (String arg : args) {
            String[] keyValue = ArgsName.check(arg);
            values.put(keyValue[0].substring(1), keyValue[1]);
        }
    }

    public void pathValidate(Path path) {
        if (!Files.isDirectory(path)) {
            throw new IllegalArgumentException(String.format("The directory - '%s' does not exist.", path));
        }
    }

    public static ArgsName of(String[] args) {
        /* TODO add the necessary checks. */
        if (args.length == 0) {
            throw new IllegalArgumentException("Arguments not passed to program");
        }
        ArgsName names = new ArgsName();
        names.parse(args);
        return names;
    }

    private static String[] check(String arg) {
        String[] ret = arg.split("=", 2);
        if (!arg.contains("=")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain an equal sign", arg));
        }
        if (!ret[0].startsWith("-")) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not start with a '-' character", arg));
        }
        if (ret[0].length() < 2) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain a key", arg));
        }
        if (ret[1].length() == 0) {
            throw new IllegalArgumentException(String.format(
                    "Error: This argument '%s' does not contain a value", arg));
        }
        return ret;
    }

    public static void main(String[] args) {
        ArgsName jvm = ArgsName.of(new String[] {"-Xmx=512", "-encoding=UTF-8"});
        System.out.println(jvm.get("Xmx"));

        ArgsName zip = ArgsName.of(new String[] {"-out=project.zip", "-encoding=UTF-8"});
        System.out.println(zip.get("out"));
    }
}