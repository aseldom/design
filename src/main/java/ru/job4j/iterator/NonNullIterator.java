package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class NonNullIterator implements Iterator<Integer> {

    private final Integer[] data;
    private int index = 0;
    private boolean get = false;

    public NonNullIterator(Integer[] data) {
        this.data = data;
    }

    @Override
    public boolean hasNext() {
        if (!get) {
            for (; index < data.length; index++) {
                if (data[index] != null) {
                    get = true;
                    break;
                }
            }
        }
        return get;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        get = false;
        return data[index++];
    }

}