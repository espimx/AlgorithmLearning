package com.espimx.algo.stack;

import java.util.NoSuchElementException;

/**
 * 栈
 */
public class IStack<E> {
    private Node<E> top = null;
    private int size = 0;

    /**
     * 压栈
     */
    public void push(E e) {
        if (top == null) {
            //如果栈为空，直接new
            top = new Node<>(e);
        } else {
            //如果栈不为空，则在链表头部添加节点
            //并将top节点指向头节点
            Node<E> temp = new Node<>(e);
            temp.next = top;
            top = temp;
        }
    }

    /**
     * 入栈
     */
    public E pop() {
        if (top == null) {
            throw new NoSuchElementException("栈为空");
        }
        E e = top.element;
        top = top.next;
        return e;
    }

    public void printStack() {
        Node<E> temp = top;
        while (temp != null) {
            System.out.print(temp.element + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    private static class Node<E> {
        E element;
        Node next;

        Node(E element) {
            this.element = element;
        }
    }
}
