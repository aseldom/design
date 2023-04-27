package ru.job4j.io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        valid(args);
        String f = args[0];
        String extension = args[1];
        Path start = Paths.get(f);
        search(start, p -> p.toFile().getName().endsWith(extension)).forEach(System.out::println);
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }

    public static void valid(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Arguments length not equal 2");
        }
        if (!Files.isDirectory(Paths.get(args[0]))) {
            throw new IllegalArgumentException("First argument is not Path");
        }
        if (!(args[1].startsWith(".") && args[1].length() > 1)) {
            throw new IllegalArgumentException("Second argument is not extension");
        }
    }
}