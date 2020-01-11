package com.espimx.algo.test;

import com.espimx.algo.queue.IQueueByArray;
import com.espimx.algo.queue.IQueueByStack;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
    public static void main(String[] args) {
        IQueueByArray<Integer> queue = new IQueueByArray<>(10);
        queue.enqueue(2);
        queue.enqueue(3);
        queue.enqueue(1);
        queue.enqueue(6);
        queue.enqueue(7);
        System.out.println("出队: " + queue.dequeue());
        System.out.println("出队: " + queue.dequeue());
        System.out.println("队列中元素个数: " + queue.size());
        System.out.println("出队: " + queue.dequeue());
        System.out.println("队列是否为空: " + queue.isEmpty());
        System.out.println("出队: " + queue.dequeue());
        System.out.println("队列是否为空: " + queue.isEmpty());
        System.out.println("--------------");

        IQueueByStack<Integer> queue2 = new IQueueByStack<>();
        queue2.enqueue(2);
        queue2.enqueue(5);
        queue2.enqueue(7);
        queue2.enqueue(3);
        queue2.enqueue(0);
        queue2.enqueue(1);
        System.out.println("出队: " + queue2.dequeue());
        System.out.println("出队: " + queue2.dequeue());
        System.out.println("出队: " + queue2.dequeue());
        System.out.println("出队: " + queue2.dequeue());
        System.out.println("队列中元素个数: " + queue2.size());
        System.out.println("出队: " + queue2.dequeue());
        System.out.println("队列是否为空: " + queue2.isEmpty());
        System.out.println("出队: " + queue2.dequeue());
        System.out.println("队列是否为空: " + queue2.isEmpty());
    }
}
