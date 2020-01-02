package com.espimx.algo.list;

import java.util.Arrays;
import java.util.Iterator;

/**
 * 使用数组实现的线性表
 */
public class IArrayList<E> extends IAbstractList<E> {
    private static final int INITIAL_CAPACITY = 10;
    private Object[] data;

    public IArrayList() {
        this(INITIAL_CAPACITY);
    }

    public IArrayList(int capacity) {
        data = new Object[capacity];
    }

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException();
        }
        ensureCapacity();
        for (int i = size - 1; i >= index; i--) {
            data[i + 1] = data[i];
        }
        data[index] = e;
        size++;
    }

    @Override
    public void clear() {
        Arrays.fill(data, null);    //清空数组，让JVM可以进行GC
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) >= 0;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        return (E) data[index];
    }

    @Override
    public int indexOf(E e) {
        for (int i = 0; i < size; i++) {
            if (e.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        for (int i = size; i >= 0; i--) {
            if (e.equals(data[i])) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Object e = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        data[size - 1] = null;
        size--;
        trimToSize();
        return (E) e;
    }

    @Override
    public Object set(int index, E e) {
        checkIndex(index);
        Object old = data[index];
        data[index] = e;
        return old;
    }



    /**
     * 扩容
     */
    private void ensureCapacity() {
        if (size >= data.length) {
            Object[] temp = new Object[size * 2];
            for (int i = 0; i < data.length; i++) {
                temp[i] = data[i];
            }
            data = temp;
        }
    }

    /**
     * 当实际使用的大小小于数组容量的1/4时，进行缩容
     */
    private void trimToSize() {
        if (size == data.length / 4) {
            Object[] temp = new Object[data.length / 2];
            /*
            for (int i = 0; i < size; i++) {
                temp[i] = data[i];
            }
            */
            System.arraycopy(data, 0, temp, 0, size);
            data = temp;
        }
    }

    @Override
    public String toString() {
        StringBuilder listString = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            listString.append(data[i]);
            if (i < size - 1) {
                listString.append(", ");
            }
        }
        listString.append("]");
        return listString.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new IArrayListIterator();
    }

    /**
     * 实现迭代器
     */
    private class IArrayListIterator implements Iterator<E> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public E next() {
            return (E) data[current++];
        }

        @Override
        public void remove() {
            IArrayList.this.remove(current);
        }
    }
}
