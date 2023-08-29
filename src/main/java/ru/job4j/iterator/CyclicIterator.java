package ru.job4j.iterator;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class CyclicIterator<T> implements Iterator<T> {

    private final List<T> data;
    private int index = 0;
    private final int dataSize;

    public CyclicIterator(List<T> data) {
        this.data = data;
        dataSize = data.size();
    }

    @Override
    public boolean hasNext() {
        return dataSize > 0;
    }

    @Override
    public T next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        if (index == dataSize) {
            index = 0;
        }
        return data.get(index++);
    }
}