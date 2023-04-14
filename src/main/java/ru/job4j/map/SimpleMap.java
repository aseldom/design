package ru.job4j.map;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleMap<K, V> implements Map<K, V> {

    private static final float LOAD_FACTOR = 0.75f;
    private int capacity = 8;
    private int count = 0;
    private boolean expand = false;
    private int modCount = 0;
    private MapEntry<K, V>[] table = new MapEntry[capacity];

    @Override
    public boolean put(K key, V value) {
        if ((int) (capacity * LOAD_FACTOR) <= count) {
            expand = true;
            expand();
        }
        boolean res = false;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            table[index] = new MapEntry<>(key, value);
            res = true;
            if (!expand) {
                modCount++;
                count++;
            }

        }
        return res;
    }

    private int hash(int hashCode) {
        return (hashCode == 0) ? 0 : (hashCode) ^ (hashCode >>> 16);
    }

    private int indexFor(int hash) {
        return hash & (capacity - 1);
    }

    private void expand() {
        capacity *= 2;
        MapEntry<K, V>[] newTable = table;
        table = new MapEntry[capacity];
        for (MapEntry<K, V> entry: newTable) {
            if (entry != null) {
                put(entry.key, entry.value);
            }
        }
        expand = false;
    }

    @Override
    public V get(K key) {
        V res = null;
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        if (table[index] == null) {
            res = null;
        } else if (key == null && table[index].key == null) {
            res = table[index].value;
        } else if (key == null || table[index].key == null) {
            res = null;
        } else if (key.hashCode() == table[index].key.hashCode()
                && key.equals(table[index].key)) {
            res = table[index].value;
        }
        return res;
    }

    @Override
    public boolean remove(K key) {
        int index = key == null ? 0 : indexFor(hash(key.hashCode()));
        boolean res = false;
        if (table[index] != null) {
            table[index] = null;
            modCount++;
            count--;
            res = true;
        }
        return res;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            final int expandModCount = modCount;
            int index;

            @Override
            public boolean hasNext() {
                if (modCount != expandModCount) {
                    throw new ConcurrentModificationException();
                }
                while (index < capacity && table[index] == null) {
                    index++;
                }
                return index < capacity;
            }

            @Override
            public K next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return table[index++].key;
            }
        };
    }

    private static class MapEntry<K, V> {
        K key;
        V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            MapEntry<?, ?> mapEntry = (MapEntry<?, ?>) o;
            return Objects.equals(key, mapEntry.key);
        }

        @Override
        public int hashCode() {
            return Objects.hash(key);
        }
    }
}