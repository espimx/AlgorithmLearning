package com.espimx.algo.queue;

public abstract class IAbstractQueue<E> implements IQueue<E> {
    protected int size = 0;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }
}
