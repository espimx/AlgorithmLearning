package com.espimx.algo.list;

public abstract class IAbstractList<E> implements IList<E> {
    protected int size = 0;

    protected IAbstractList() {

    }

    protected IAbstractList(E[] objects) {
        for (E object : objects) {
            add(object);
        }
    }

    @Override
    public void add(E e) {
        add(size, e);
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public boolean remove(E e) {
        int index = indexOf(e);
        if (index >= 0) {
            remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 检验索引是否合法
     */
    protected void checkIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }
}
