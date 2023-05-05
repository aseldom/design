package ru.job4j.io.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.*;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Arrays;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class Student {

    @XmlAttribute
    private String name;
    private boolean male;
    private int age;
    private String[] theme;
    private Pet pet;

    public Student() { }

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

    public static void main(String[] args) throws JAXBException {
        final Student student = new Student("Roman Nikolaev",
                true,
                20,
                new String[] {"Math", "Phisic"},
                new Pet("Druzhok"));
        JAXBContext context = JAXBContext.newInstance(Student.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String result = "";
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(student, writer);
            result = writer.getBuffer().toString();
            System.out.println(result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        /* Для десериализации нам нужно создать десериализатор */
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(result)) {
            /* десериализуем */
            Student res = (Student) unmarshaller.unmarshal(reader);
            System.out.println(res);
        }
    }
}
