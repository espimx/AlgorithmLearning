package com.espimx.algo.test;

import com.espimx.algo.queue.IQueue;

public class QueueTest {
    public static void main(String[] args) {
        IQueue<Integer> queue = new IQueue<>(10);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(6);
        queue.enqueue(7);
        queue.printQueue();
        queue.dequeue();
        queue.dequeue();
        queue.printQueue();
    }
}
