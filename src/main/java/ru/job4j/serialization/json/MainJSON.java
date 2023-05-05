package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainJSON {

    public static void main(String[] args) {

        /* JSONObject из json-строки строки */
        JSONObject jsonPet = new JSONObject("{\"name\":\"Polkan\"}");

        /* JSONArray из ArrayList */
        List<String> list = new ArrayList<>();
        list.add("Math");
        list.add("Physic");
        JSONArray jsonStatuses = new JSONArray(list);

        /* JSONObject напрямую методом put */
        final Student student = new Student("Boris", true, 20,
                new String[]{"Philosophy", "Psychology"}, new Pet("Druzhok"));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", student.getName());
        jsonObject.put("age", student.getAge());
        jsonObject.put("male", student.isMale());
        jsonObject.put("pet", jsonPet);
        jsonObject.put("theme", jsonStatuses);

        /* Выведем результат в консоль */
        System.out.println(jsonObject.toString());

        /* Преобразуем объект person в json-строку */
        System.out.println(new JSONObject(student).toString());
    }

}
