package ru.job4j.question;


import java.util.Set;

public class Analize {

    public static Info diff(Set<User> previous, Set<User> current) {
        int added = 0;
        int changed = 0;
        int deleted = 0;
        int equal = 0;
        deleted = (int) previous.stream().filter(s -> !current.contains(s))
                .count();
        added = current.size() - previous.size() + deleted;

        for (User userPrev : previous) {
            for (User userCur : current) {
                if (userPrev.getId() == userCur.getId()) {
                    if (userPrev.getName().equals(userCur.getName())) {
                        equal++;
                    } else {
                        changed++;
                    }
                }
            }
        }
        deleted = previous.size() - equal - changed;
        return new Info(added, changed, deleted);
    }
}