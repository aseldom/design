package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> out = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            out = in.lines().map(a -> a.split(" "))
                    .filter(a -> "404".equals(a[a.length - 2]))
                    .map(a -> String.join(" ", a))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void save(List<String> log, String file) {
        try (PrintWriter out = new PrintWriter(
                new BufferedOutputStream(
                        new FileOutputStream(file)))) {
            out.println(log.stream().collect(Collectors.joining(System.lineSeparator())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        /* Здесь сделайте построчный вывод содержимого списка log на консоль*/
        log.forEach(System.out::println);
        save(log, "data/404.txt");
    }
}