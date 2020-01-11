package com.espimx.algo.list;

import java.util.Iterator;

/**
 * 双向链表
 */
public class IDoubleLinkedList<E> extends IAbstractList<E> {
    private Node<E> head;
    private Node<E> tail;

    @Override
    public void add(int index, E e) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("index: " + index + ", size: " + size);
        }
        Node<E> newNode = new Node<>(e);
        if (head == null) {
            //链表为空
            head = newNode;
            tail = head;
        } else if (index == 0) {
            //插入链表首部
            head.prev = newNode;
            newNode.next = head;
            head = head.prev;
        } else if (index == size) {
            //插入链表尾部
            tail.next = newNode;
            newNode.prev = tail;
            tail = tail.next;
        } else {
            //插入链表中间位置
            Node<E> temp = head;
            for (int i = 0; i < index - 1; i++) {
                temp = temp.next;
            }
            newNode.next = temp.next;
            temp.next.prev = newNode;
            newNode.prev = temp;
            temp.next = newNode;
        }
        size++;
    }

    public void addFirst(E e) {
        add(0, e);
    }

    public void addLast(E e) {
        add(size, e);
    }

    @Override
    public void clear() {
        while (head != null) {
            Node<E> next = head.next;
            head.prev = null;
            head.next = null;
            head.element = null;
            head = next;
        }
        size = 0;
    }

    @Override
    public boolean contains(E e) {
        return indexOf(e) != -1;
    }

    @Override
    public E get(int index) {
        Node<E> temp;
        if (index > size - 2) {
            temp = findNodeByTail(index);
        } else {
            temp = findNodeByHead(index);
        }
        return temp.element;
    }

    /**
     * 从链表尾部开始搜索指定索引位置的节点
     */
    private Node<E> findNodeByTail(int index) {
        Node<E> result = tail;
        for (int i = size - 1; i > index; i--) {
            result = result.prev;
        }
        return result;
    }

    /**
     * 从链表头部开始搜索指定索引位置的节点
     */
    private Node<E> findNodeByHead(int index) {
        Node<E> result = head;
        for (int i = 0; i < index; i++) {
            result = result.next;
        }
        return result;
    }


    @Override
    public int indexOf(E e) {
        Node<E> temp = head;
        for (int i = 0; i < size; i++) {
            if (temp.element.equals(e)) {
                return i;
            }
            temp = temp.next;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(E e) {
        Node<E> temp = tail;
        for (int i = size - 1; i >= 0; i--) {
            if (temp.element.equals(e)) {
                return i;
            }
            temp = temp.prev;
        }
        return -1;
    }

    @Override
    public E remove(int index) {
        checkIndex(index);
        if (index == 0) {
            return removeFirst();
        }
        if (index == size - 1) {
            return removeLast();
        }
        Node<E> prevNode;   //需要删除节点的前一个节点
        //如果索引位置大于容量的1/2，则从后往前遍历，否则从前往后遍历
        if (index > size / 2) {
            prevNode = tail;
            for (int i = size - 1; i >= index; i--) {
                prevNode = prevNode.prev;
            }
        } else {
            prevNode = head;
            for (int i = 0; i < index - 1; i++) {
                prevNode = prevNode.next;
            }
        }
        E element = prevNode.next.element;  //获取要删除节点的值
        prevNode.next = prevNode.next.next;
        prevNode.next.prev = prevNode;
        size--;
        return element;
    }

    /**
     * 移除第一个节点
     */
    public E removeFirst() {
        E result = head.element;
        head = head.next;
        if (head == null) {
            tail = null;
        } else {
            head.prev = null;
        }
        size--;
        return result;
    }

    /**
     * 移除最后一个节点
     */
    public E removeLast() {
        E result = tail.element;
        tail = tail.prev;
        tail.next = null;
        size--;
        return result;
    }

    @Override
    public E set(int index, E e) {
        checkIndex(index);
        Node<E> temp;
        //如果索引位置大于容量的1/2，则从后往前遍历，否则从前往后遍历
        if (index > size / 2) {
            temp = findNodeByTail(index);
        } else {
            temp = findNodeByHead(index);
        }
        E result = temp.element;
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
        return new IDoubleLinkedListIterator();
    }

    private static class Node<E> {
        E element;
        Node<E> next;
        Node<E> prev;
        Node(E e) {
            element = e;
        }
    }

    private class IDoubleLinkedListIterator implements Iterator<E> {
        private Node<E> current = head;
        private int index = 0;

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
