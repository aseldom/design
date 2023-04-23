package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class LogFilter {
    public List<String> filter(String file) {
        List<String> out = new ArrayList<>();
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            out = in.lines().map(a -> a.split(" "))
                    .filter(a -> a[a.length - 2].equals("404"))
                    .map(a -> String.join(" ", a))
                    .toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter();
        List<String> log = logFilter.filter("data/log.txt");
        /* Здесь сделайте построчный вывод содержимого списка log на консоль*/
        log.forEach(System.out::println);
    }
}