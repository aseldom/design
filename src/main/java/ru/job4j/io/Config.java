package ru.job4j.io;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

public class Config {
    private final String path;
    private final Map<String, String> values = new HashMap<String, String>();

    public Config(final String path) {
        this.path = path;
    }

    public void load() {
        try (BufferedReader in = new BufferedReader(new FileReader(this.path))) {
            for (String s : in.lines().toList()) {
                String[] sp = s.split("=", 2);
                if (s.length() > 0
                && !s.startsWith("#")
                && sp.length > 1 && sp[0].length() > 0 && !sp[0].endsWith(" ")
                && sp[1].length() > 0 && !sp[1].startsWith(" ")) {
                    values.put(sp[0], sp[1]);
                } else if (!(s.length() == 0
                            || s.startsWith("#"))) {
                    throw new IllegalArgumentException();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String value(String key) {
        return values.getOrDefault(key, null);
    }

    @Override
    public String toString() {
        StringJoiner out = new StringJoiner(System.lineSeparator());
        try (BufferedReader read = new BufferedReader(new FileReader(this.path))) {
            read.lines().forEach(out::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    public static void main(String[] args) {
        System.out.println(new Config("data/app.properties"));
        Config conf = new Config("data/pair_with_comment.properties");
        conf.load();
        System.out.println(conf.values);
    }
}