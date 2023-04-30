package ru.job4j.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        List<String> log = new ArrayList<>();
        List<String> answers  = readPhrases();
        Random random = new Random();
        int size = answers.size();
        boolean run = true;
        boolean stop = false;
        while (run) {
            System.out.println("Введите фразу:");
            String in = scanner.nextLine();
            log.add("Ввод пользователя: " + in);
            if (STOP.equals(in) && !stop) {
                stop = true;
                log.add("Чат приостановлен");
                System.out.println("Чат приостановлен, для продолжения введите слово: \"продолжить\"");
            } else if (CONTINUE.equals(in) && stop) {
                stop = false;
                log.add("Чат продолжен");
                System.out.println("Чат продолжен");
            } else if (OUT.equals(in)) {
                run = false;
                log.add("Чат завершен");
                System.out.println("Чат завершен");
            } else if (!stop) {
                String ans = answers.get(random.nextInt(size));
                log.add("Ответ бота: " + ans);
                System.out.println("Ответ бота: " + ans);
            }
        }
        scanner.close();
        saveLog(log);
    }

    private List<String> readPhrases() {
        List<String> res = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(
                new FileReader(botAnswers, StandardCharsets.UTF_8))) {
            res =  reader.lines().toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter pw = new PrintWriter(new FileWriter(path, StandardCharsets.UTF_8))) {
            log.forEach(pw::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ConsoleChat cc = new ConsoleChat("./data/logBot.txt", "./data/botAnswers.txt");
        cc.run();
    }
}