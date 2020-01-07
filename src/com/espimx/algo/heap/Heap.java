package com.espimx.algo.heap;

import com.espimx.algo.list.IArrayList;
import com.espimx.algo.list.IList;

import java.util.Random;

/**
 * 使用链表实现的堆
 * 这里使用的链表是我自己写的，也可以换成java.util.List
 */
public class Heap<E extends Comparable<E>> {
    private IList<E> data = new IArrayList<>();

    /**
     * 添加节点
     */
    public void add(E element) {
        data.add(element);
        up();   //添加新的元素后，需要通过“上浮”重新调整堆
    }

    /**
     * 末尾节点上浮
     */
    private void up() {
        if (data.size() <= 1) {
            return;
        }

        int index = data.size() - 1;
        E temp = data.get(index);

        //当前节点不为0且当前节点的值大于父节点的值
        while (index > 0 && temp.compareTo(getParent(index)) > 0) {
            //不需要交换，单向赋值即可
            data.set(index, getParent(index));
            index = (index - 1) / 2;
        }
        data.set(index, temp);
    }

    /**
     * 获取父节点的值
     */
    private E getParent(int index) {
        return data.get((index - 1) / 2);
    }

    /**
     * 移除堆顶
     */
    public E remove() {
        int index = data.size() - 1;
        data.set(0, data.get(index));
        E result = data.remove(index);
        sink();     //每次删除节点时，都要通过“下沉”来重新调整堆
        return result;
    }

    /**
     * 堆顶节点下沉
     */
    private void sink() {
        if (data.size() <= 1) {
            return;
        }

        int index = 0;
        E temp = data.get(index);
        int childIndex = 2 * index + 1;
        //子节点的索引小于堆的容量
        while (childIndex < data.size()) {
            //获取左右孩子节点中的的较大的一个
            if (childIndex + 1 < data.size()
                    && data.get(childIndex).compareTo(data.get(childIndex + 1)) < 0) {
                childIndex++;
            }
            //父节点大于两个子节点，则退出循环
            if (temp.compareTo(data.get(childIndex)) > 0) {
                break;
            }
            //不需要交换，单项赋值就行
            data.set(index, data.get(childIndex));
            index = childIndex;
            childIndex = 2 * index + 1;
        }
        data.set(index, temp);
    }

    private void print() {
        for (int i = 0; i < data.size(); i++) {
            System.out.print(data.get(i) + " ");
        }
    }

    public static void main(String[] args) {
        Heap<Integer> heap = new Heap<>();
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            heap.add(random.nextInt(50));
        }

        heap.print();
        System.out.println();
        heap.remove();
        heap.print();
        System.out.println();
        heap.remove();
        heap.print();
        System.out.println();
        heap.remove();
        heap.print();
    }
}
