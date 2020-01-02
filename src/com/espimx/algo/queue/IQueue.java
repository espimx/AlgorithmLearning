package com.espimx.algo.queue;

import java.util.NoSuchElementException;

/**
 * 循环队列
 */
public class IQueue<E> {
    private E[] data;
    private int head = 0;
    private int tail = 0;

    public IQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    /**
     * 出队
     */
    public E dequeue() {
        if (head == tail) {
            throw new NoSuchElementException("空队列");
        }
        E result = data[head];
        head = (head + 1) % data.length;
        return result;
    }

    /**
     * 入队
     */
    public void enqueue(E element) {
        //当(尾节点+1)%队列容量==头节点时，说明队列已满，无法继续入队
        if ((tail + 1) % data.length == head) {
            throw new IndexOutOfBoundsException();
        }
        data[tail] = element;
        tail = (tail + 1) % data.length;
    }

    /**
     * 打印队列
     */
    public void printQueue() {
        for (int i = head; i != tail; i = (i + 1) % data.length) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }
}
