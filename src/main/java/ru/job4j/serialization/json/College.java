package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class College {
    public static void main(String[] args) {
        final Student student = new Student("Roman Nikolaev",
                true,
                20,
                new String[] {"Math", "Phisic"},
                new Pet("Druzhok"));
        Gson gson = new GsonBuilder().create();
        String gsonStudent = gson.toJson(student);
        Student fromGsonStudent = gson.fromJson(gsonStudent, Student.class);
        System.out.println(student);
        System.out.println(gsonStudent);
        System.out.println(fromGsonStudent);
    }
}
