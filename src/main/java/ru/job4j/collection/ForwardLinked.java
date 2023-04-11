package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ForwardLinked<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> head;

    public void add(T value) {
        if (size == 0) {
            head = new Node<>(value, null);
        } else {
            Node<T> c = head;
            int last = size - 1;
            while (last > 0) {
                c = c.next;
                last--;
            }
            c.next = new Node<>(value, null);
        }
        size++;
        modCount++;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        Node<T> c = head;
        while (index > 0) {
            c = c.next;
            index--;
        }
        return c.item;
    }

    public T deleteFirst() {
        Node<T> c = head;
        if (head == null) {
            throw new NoSuchElementException();
        }
        head = head.next;
        size--;
        return c.item;
    }

    public Iterator<T> iterator() {
        return new Iterator<>() {
            final int expectedModCount = modCount;
            int point = 0;
            Node<T> c = head;

            @Override
            public boolean hasNext() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                return point < size;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T i = c.item;
                c = c.next;
                point++;
                return i;
            }
        };
    }

    private static class Node<T> {
        private T item;
        private Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
}