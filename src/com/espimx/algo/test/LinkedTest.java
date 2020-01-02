package com.espimx.algo.test;

import com.espimx.algo.list.ILinkedList;

import java.util.ArrayList;

public class LinkedTest {
    public static void main(String[] args) {
        ILinkedList list = new ILinkedList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(2);
        System.out.println(list.size());
        System.out.println(list.lastIndexOf(2));
        ArrayList list1 = new ArrayList();
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(2);
        System.out.println(list1.size());
        System.out.println(list1.lastIndexOf(2));
    }
}
