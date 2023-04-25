package ru.job4j.io;

import java.io.*;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader in = new BufferedReader(new FileReader(source));
                PrintWriter out = new PrintWriter(new FileWriter(target))) {
            String s;
            boolean find = false;
            while ((s = in.readLine()) != null) {
                String[] sar = s.split(" ");
                if (("400".equals(sar[0]) || "500".equals(sar[0])) == !find) {
                    find = !find;
                    out.printf("%s;", sar[1]).printf(find ? "" : "%n");
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
    }
}