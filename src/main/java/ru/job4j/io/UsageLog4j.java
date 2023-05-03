package ru.job4j.io;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UsageLog4j {

    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        boolean boo = true;
        byte b = 127;
        short s = 32;
        int age = 33;
        long l = 333L;
        float f = 1.2f;
        double d = 23;
        char c = 'a';

        LOG.debug("Primitive type: boolean {}, byte {}, short {},"
                        + "int {}, long {}, float {}, double {}, char {}",
                boo, b, s, age, l, f, d, c);
    }
}