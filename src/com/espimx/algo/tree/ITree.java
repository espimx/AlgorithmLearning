package com.espimx.algo.tree;

public interface ITree<E> extends Iterable<E> {
    /**
     * 插入一个元素
     */
    boolean insert(E e);

    /**
     * 搜索指定的元素
     */
    boolean search(E e);

    /**
     * 删除一个元素
     */
    boolean delete(E e);

    /**
     * 前序遍历
     */
    void postOrder();

    /**
     * 中序遍历
     */
    void inOrder();

    /**
     * 后序遍历
     */
    void preOrder();

    /**
     * 获取树中的节点数
     */
    int size();

    /**
     * 判断树是否为空
     */
    boolean isEmpty();

    /**
     * 删除树中的所有元素
     */
    void clear();
}
