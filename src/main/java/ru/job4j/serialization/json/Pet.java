package ru.job4j.serialization.json;

public class Pet {
    private String name;

    public Pet(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{"
                + "name='" + name + '\''
                + '}';
    }
}
