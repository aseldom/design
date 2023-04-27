package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {
     private final Map<FileProperty, List<String>> store = new HashMap<>();

     public void print() {
         for (FileProperty k : store.keySet()) {
             if (store.get(k).size() > 1) {
                 System.out.println(k.getName() + " - " + k.getSize());
                 store.get(k).forEach(System.out::println);
             }
         }
     }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        if (Files.isRegularFile(file)) {
            FileProperty newFile = new FileProperty(file.toFile().length(), file.getFileName().toString());
            List<String> l = store.putIfAbsent(newFile,
                    new ArrayList<>(Arrays.asList(file.toAbsolutePath().toString())));
            if (l != null) {
                l.add(file.toAbsolutePath().toString());
            }
        }
        return super.visitFile(file, attrs);
    }
}