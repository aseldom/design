package ru.job4j.serialization.json;

import java.util.Arrays;

public class Student {
    final private String name;
    final private boolean male;
    final private int age;
    final private String[] theme;
    final private Pet pet;

    public Student(String name, boolean male, int age, String[] theme, Pet pet) {
        this.name = name;
        this.male = male;
        this.age = age;
        this.theme = theme;
        this.pet = pet;
    }

    @Override
    public String toString() {
        return "Student{"
                + "name='" + name + '\''
                + ", male=" + male
                + ", age=" + age
                + ", theme=" + Arrays.toString(theme)
                + ", pet=" + pet
                + '}';
    }
}
