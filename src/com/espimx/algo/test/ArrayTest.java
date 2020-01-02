package com.espimx.algo.test;

import com.espimx.algo.list.IArrayList;

import java.util.ArrayList;

public class ArrayTest {
    public static void main(String[] args) {
        IArrayList<Integer> list1 = new IArrayList<>(3);
        list1.add(1);
        list1.add(2);
        list1.add(3);
        list1.add(4);
        list1.add(5);
        list1.add(3, 6);
        list1.remove(4);
        list1.set(3, 4);
        System.out.println(list1.size());
        System.out.println(list1.toString());
    }
}
