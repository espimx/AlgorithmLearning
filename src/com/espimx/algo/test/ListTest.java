package com.espimx.algo.test;

import com.espimx.algo.list.IArrayList;
import com.espimx.algo.list.IDoubleLinkedList;
import com.espimx.algo.list.ILinkedList;
import com.espimx.algo.list.IList;


public class ListTest {
    public static void main(String[] args) {
        ListTest test = new ListTest();
        IList<Integer> list = new ILinkedList<>();
        IList<Integer> list2 = new IDoubleLinkedList<>();
        IList<Integer> list3 = new IArrayList<>();
        System.out.print("ILinkedList: ");
        test.test(list);
        System.out.println("---------------------");
        System.out.print("IDoubleLinkedList: ");
        test.test(list2);
        System.out.println("---------------------");
        System.out.print("IArrayList: ");
        test.test(list3);

    }

    private void test(IList<Integer> list) {
        for (int i = 0; i < 10; i++) {
            list.add((i + 3) * i + 1);
        }
        list.add(71);
        list.add(22);
        list.add(0, 6);
        System.out.println(list.toString());
        System.out.println();
        System.out.println("容量: " + list.size());
        System.out.println("89所在的位置: " + list.indexOf(89));
        System.out.println("71最后出现的位置: " + list.lastIndexOf(71));
        System.out.println("是否为空: " + list.isEmpty());
        System.out.println("是否存在109: " + list.contains(109));
        System.out.println("索引为1的值为: " + list.get(1));
        System.out.println("移除第一个元素: " + list.remove(0));
        System.out.println("移除索引为2的元素: " + list.remove(2));
        System.out.println("移出最后一个元素: " + list.remove(list.size() - 1));
        System.out.println(list.toString());
        System.out.println("重新设置索引为4的元素: " + list.set(4, 90));
    }
}
