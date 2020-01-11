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
        size++;
    }

    /**
     * 出栈
     * 获取top节点的值，使top节点指向下一个节点
     */
    public E pop() {
        if (top == null) {
            throw new NoSuchElementException("栈为空");
        }
        E e = top.element;
        top = top.next;
        size--;
        return e;
    }
    
    /**
     * 获取栈顶元素的值，但是不移除栈顶元素
     */
    public E peek() {
        return top == null ? null : top.element;
    }
    
    /**
     * 栈是否为空
     */
    public boolean isEmpty() {
        return top == null;
    }
    
    /**
     * 栈中元素个数
     */
    public int size() {
        return size;
    }

    private static class Node<E> {
        E element;
        Node next;

        Node(E element) {
            this.element = element;
        }
    }
}
