package ru.exercise.k;

import ru.job4j.io.Buffered;
import ru.job4j.io.PrintFiles;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.AclFileAttributeView;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import java.util.regex.MatchResult;

public class TestStream {
    public static void main(String[] args) throws IOException {
        Console console = System.console();
        FileOutputStream fo = new FileOutputStream("temp.txt");
        System.out.println(console);
        ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("sadf"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Path.of("asdfas", "asdf", "asdf");
        bw.write("Asdasd");
        bw.flush();
        Scanner sc = new Scanner(System.in);
        System.out.println(sc.findInLine("\\d+"));
        if (sc.hasNextBigDecimal()) {
            System.out.println(sc.nextBigDecimal());
        }
        String s = "sdaasdfdaf";
        System.out.println(s.matches("sdaa\\w*"));
        Files.walk(Path.of("d:/music"))
                .filter(f -> !f.getFileName().toString().matches(".*\\.((mp3)?|(MP3)?|(flac)?)$") && !f.toFile().isDirectory()
        ).forEach(System.out::println);
//        File f = new File("sadf");
//        Scanner sc = new Scanner(new FileInputStream("sadf"));
//        sc = new Scanner(f);
        BufferedInputStream b = new BufferedInputStream(new FileInputStream("sadf"));
        Path path = Paths.get("./pom.xml");
//        System.out.println(Arrays.toString(path.toFile().listFiles()));
        Files.walkFileTree(path, new PrintFiles());
        Path file = Path.of("pom.zip");
        BasicFileAttributeView attrView = Files.getFileAttributeView(Path.of("saf"), BasicFileAttributeView.class);
        BasicFileAttributes attributes = attrView.readAttributes();
        attributes.creationTime();
        BasicFileAttributes attr = Files.readAttributes(Path.of("sadf"), BasicFileAttributes.class);
        System.out.println(attributes.creationTime());
//        System.out.println(new File("./aa/aa").mkdirs());
        String str = "123456789";
        byte[] bytes1 = str.getBytes();
        ByteArrayInputStream byteStream2 = new ByteArrayInputStream(bytes1, 3, 4);
        int data1;
        byteStream2.readAllBytes();
        while ((data1 = byteStream2.read()) != -1) {
            System.out.print((char) data1);
        }
    }
}
