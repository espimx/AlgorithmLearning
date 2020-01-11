package com.espimx.algo.list;

public interface IList<E> extends Iterable<E> {
    /**
     * 在线性表的末尾添加一个元素
     */
    void add(E e);

    /**
     * 在线性表的指定位置添加一个元素
     */
    void add(int index, E e);

    /**
     * 清空整个线性表
     */
    void clear();

    /**
     * 判断线性表中是否包含某个元素
     */
    boolean contains(E e);

    /**
     * 获取指定位置的元素
     */
    E get(int index);

    /**
     * 某个元素所在的位置
     * 如果没有该元素则返回-1
     */
    int indexOf(E e);

    /**
     * 判断线性表是否为空
     */
    boolean isEmpty();

    /**
     * 获取该元素在线性表中的最后一次出现的位置
     * 如果没有该元素则返回-1
     */
    int lastIndexOf(E e);

    /**
     * 移除指定的元素
     */
    boolean remove(E e);

    /**
     * 移出指定位置的元素
     */
    E remove(int index);

    /**
     * 替换指定位置的元素，返回替换之前的元素
     */
    E set(int index, E e);

    /**
     * 获取线性表中元素的个数
     */
    int size();
}
