package ru.job4j.io.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "pet")
public class Pet {
    @XmlAttribute
    private String name;

    public Pet() { }

    public Pet(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Pet{"
                + "name='" + name + '\''
                + '}';
    }
}
