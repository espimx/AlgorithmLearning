package com.espimx.algo.queue;

import java.util.NoSuchElementException;

/**
 * 使用数组实现的循环队列
 */
public class IQueueByArray<E> extends IAbstractQueue<E> {
    private E[] data;
    private int head = 0;
    private int tail = 0;

    public IQueueByArray(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public E dequeue() {
        if (head == tail) {
            throw new NoSuchElementException("空队列");
        }
        E result = data[head];
        head = (head + 1) % data.length;
        size--;
        return result;
    }

    @Override
    public void enqueue(E element) {
        //当(尾节点+1)%队列容量==头节点时，说明队列已满，无法继续入队
        if ((tail + 1) % data.length == head) {
            throw new IndexOutOfBoundsException();
        }
        data[tail] = element;
        tail = (tail + 1) % data.length;
        size++;
    }

    /*@Override
    public boolean isEmpty() {
        return head == tail;
    }*/
}
