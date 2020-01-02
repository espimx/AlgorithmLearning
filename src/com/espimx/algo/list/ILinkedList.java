package com.espimx.algo.list;

import java.util.Iterator;

public class ILinkedList<E> extends IAbstractList<E> {
    private Node<E> head;   //头节点
    private Node<E> tail;   //尾节点

    @Override
    public void add(int index, E e) {
        Node<E> node = new Node<>(e);
        if (size == 0) {
            //链表为空
            head = node;
            tail = node;
        } else if (index == 0) {
            //插入链表的头部
            node.next = head;
            head = node;
        } else if (index >= size) {
            //插入链表的尾部
            tail.next = node;
            tail = tail.next;
        } else {
            //插入链表的中间位置
            Node<E> prevNode = head;
            for (int i = 0; i < index; i++) {
                prevNode = prevNode.next;
            }
            node.next = prevNode.next;
            prevNode.next = node.next;
        }
        size++;
    }

    @Override
    public void clear() {
        head = tail = null;
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.element == e) {
                return true;
            }
        }
        return false;
    }

    @Override
    public E get(int index) {
        checkIndex(index);
        if (index == 0) {
            return head.element;
        }
        if (index == size - 1) {
            return tail.element;
        }
        Node<E> temp = head;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        return temp.element;
    }

    @Override
    public int indexOf(E e) {
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.element == e) {
                return i;
            } else {
                temp = temp.next;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        int result = 0;
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.element == e) {
                result = i;
            }
            temp = temp.next;
        }
        return result;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        Node<E> temp = head;
        if (index == 0) {
            //删除链表头部节点
            head = head.next;
            if (head == null) {
                tail = null;
            }
        } else if (index == size - 1) {
            //删除链表尾部节点
            for (int i = 0; i < size - 2; i++) {
                temp = temp.next;
            }
            temp.next = null;
            tail = temp;
        } else {
            //删除链表中间节点
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            temp.next = temp.next.next;
        }
        size--;
        return temp.element;
    }

    @Override
    public Object set(int index, E e) {
        Node<E> temp = head;
        E result;
        for (int i = 0; i < index; i++) {
            temp = temp.next;
        }
        result = temp.element;
        temp.element = e;
        return result;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        if (size == 0) {
            return null;
        }
        Node<E> temp = head;
        result.append("[");
        for (int i = 0; i < size; i++) {
            result.append(temp.element);
            if (i < size - 1) {
                result.append(", ");
            }
            temp = temp.next;
        }
        result.append("]");
        return result.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new ILinkedListIterator();
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        Node(E element) {
            this.element = element;
        }
    }

    /**
     * 实现迭代器
     */
    private class ILinkedListIterator implements Iterator<E> {
        private Node<E> current = head;
        private int index = 0;      //记录current的索引位置

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public E next() {
            E e = current.element;
            current = current.next;
            index++;
            return e;
        }

        @Override
        public void remove() {
            Node<E> curPrev = head;
            for (int i = 0; i < index - 1; i++) {
                curPrev = curPrev.next;
            }
            curPrev.next = current.next;
            size--;
        }
    }
}
