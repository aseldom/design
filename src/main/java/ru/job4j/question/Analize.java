package ru.job4j.question;


import java.util.HashSet;
import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        int equals = 0;
        Set<User> dc = new HashSet<>(previous);
        Set<User> nc = new HashSet<>(current);
        dc.removeAll(current);
        nc.removeAll(previous);
        equals = previous.size() - dc.size();
        dc.retainAll(nc);
        changed = dc.size();
        added = current.size() - equals - changed;
        deleted = previous.size() - equals - changed;

        return new Info(added, changed, deleted);
    }
}