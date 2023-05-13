package ru.job4j.search;

import ru.job4j.io.ArgsName;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchFiles extends SimpleFileVisitor<Path> {
    private final Path in;
    private final Path out;
    private final String type;
    private final List<String> list = new ArrayList<>();
    PathMatcher matcher;
    Pattern pattern;

    public List<String> getList() {
        return list;
    }

    public Path getOut() {
        return out;
    }

    public Path getIn() {
        return in;
    }

    SearchFiles(String in, String out, String mask, String type) {
        this.in = Path.of(in);
        this.out = Path.of(out);
        this.type = type;
        if ("regex".equals(type)) {
            pattern = Pattern.compile(mask);
        } else {
            matcher = FileSystems.getDefault().getPathMatcher("glob:" + mask);
        }
    }

    @Override
    public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
        if ("regex".equals(type)) {
            Matcher matcher = pattern.matcher(file.getFileName().toString());
            if (matcher.find()) {
                list.add(file.toAbsolutePath().toString());
            }
        } else {
            if (matcher.matches(file.getFileName())) {
                list.add(file.toAbsolutePath().toString());
            }
        }
        return FileVisitResult.CONTINUE;
    }

    public static void main(String[] args) {
        ArgsName arg = ArgsName.of(args);
        Validate.valid(arg, args.length);
        SearchFiles searchFiles = new SearchFiles(arg.get("d"), arg.get("o"), arg.get("n"), arg.get("t"));
        try {
            Files.walkFileTree(searchFiles.getIn(), searchFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (PrintWriter writer = new PrintWriter(searchFiles.getOut().toFile())) {
            searchFiles.getList().forEach(writer::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
