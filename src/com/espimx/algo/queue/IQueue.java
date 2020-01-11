package com.espimx.algo.queue;

public interface IQueue<E> {
    /**
     * 出队
     */
    E dequeue();

    /**
     * 入队
     */
    void enqueue(E element);

    /**
     * 队列是否为空
     */
    boolean isEmpty();

    /**
     * 队列中元素个数
     */
    int size();
}
