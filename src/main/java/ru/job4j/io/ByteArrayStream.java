package ru.job4j.io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

public class ByteArrayStream {
    public static void main(String[] args) {
        byte[] bytes = new byte[] {'c', 's', 'r'};
        byte[] bytes2 = "ha-ha".getBytes();
        ByteArrayInputStream stream = new ByteArrayInputStream(bytes);
        ByteArrayOutputStream stream2 = new ByteArrayOutputStream();
        stream2.writeBytes(bytes2);
        int data;
        while ((data = stream.read()) != -1) {
            System.out.println((char) data);
        }
        System.out.println(stream2);
    }

}
