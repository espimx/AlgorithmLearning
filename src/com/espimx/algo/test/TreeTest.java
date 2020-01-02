package com.espimx.algo.test;

import com.espimx.algo.tree.IBinarySearchTree;

import java.util.Iterator;
import java.util.List;

public class TreeTest {
    public static void main(String[] args) {
        Integer[] i = new Integer[] {8, 3, 9, 5, 1, 7};
        IBinarySearchTree tree = new IBinarySearchTree(i);
        print(tree);
        tree.delete(5);
        System.out.println();
        print(tree);
    }

    private static void print(IBinarySearchTree tree) {
        System.out.print("前序遍历： ");
        tree.preOrder();
        System.out.print("中序遍历： ");
        tree.inOrder();
        System.out.print("后序遍历： ");
        tree.postOrder();
        System.out.print("层序遍历： ");
        tree.levelTraverse();
        System.out.println();
        System.out.println("size: " + tree.size());
        System.out.println("search: " + tree.search(5));
        System.out.print("search path: ");
        List<IBinarySearchTree.TreeNode<Integer>> list = tree.pathOfSearch(5);
        for (IBinarySearchTree.TreeNode<Integer> integerTreeNode : list) {
            System.out.print(integerTreeNode.getElement() + " ");
        }
        Iterator iterator = tree.iterator();
        System.out.println();
        System.out.print("迭代器遍历：");
        while (iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
    }
}
