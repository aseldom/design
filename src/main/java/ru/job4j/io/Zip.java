package ru.job4j.io;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Predicate;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path file : sources) {
                zip.putNextEntry(new ZipEntry(file.toString()));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(file.toString()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void validateArgs(Path source, String extension, String target) {
        if (!Files.isDirectory(source)) {
            throw new IllegalArgumentException(String.format(
                    "The directory - '%s' does not exist.", source));
        }
        if (!extension.startsWith(".") || !(extension.length() > 1)) {
            throw new IllegalArgumentException(String.format(
                    "The extension of file - '%s' does not correct.", extension));
        }
        if (target.length() < 5 || !target.endsWith(".zip")) {
            throw new IllegalArgumentException(String.format(
                    "The name of zip file - '%s' does not correct.", target));
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        ArgsName argsName = ArgsName.of(args);
        Path source = Path.of(argsName.get("d"));
        String extension = argsName.get("e");
        File target = new File(argsName.get("o"));
        zip.validateArgs(source, extension, target.toString());
        Predicate<Path> predicate = p -> !p.toFile().getName().endsWith(extension);
        List<Path> listFiles = Search.search(source, predicate);
        zip.packFiles(listFiles, target);

        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}