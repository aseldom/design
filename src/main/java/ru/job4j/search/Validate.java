package ru.job4j.search;

import ru.job4j.io.ArgsName;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Validate {

    public static void valid(ArgsName args, int length) {
        if (length != 4) {
            throw new IllegalArgumentException("Number of arguments must equal 4");
        }
        if (!Files.isDirectory(Paths.get(args.get("d")))) {
            throw new IllegalArgumentException("First argument is not Path or not exist");
        }
        if (!(Paths.get(args.get("o"))).isAbsolute()) {
            throw new IllegalArgumentException("The path of output is not correct");
        }
        String type = args.get("t");
        if (!"mask".equals(type) && !"name".equals(type) && !"regex".equals(type)) {
            throw new IllegalArgumentException("The type of search is not "
                    + "equal: \"name\", \"mask\", \"regex\"");
        }
    }
}
