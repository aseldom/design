package ru.job4j.question;

import java.util.*;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        Map<User, User> in = new HashMap<>();
        Map<User, User> out = new HashMap<>();
        current.forEach(s -> out.put(s, s));
        previous.forEach(s -> in.put(s, s));
        User user;
        for (User inKey : in.keySet()) {
            user = out.get(inKey);
            if (user != null) {
                if (user.getName().equals(inKey.getName())) {
                    out.remove(inKey);
                } else {
                    changed++;
                    out.remove(inKey);
                }
            } else {
                deleted++;
            }
        }
        added = out.size();
        return new Info(added, changed, deleted);
    }
}