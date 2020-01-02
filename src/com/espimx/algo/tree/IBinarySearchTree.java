package com.espimx.algo.tree;

import java.util.*;

/**
 * 二叉查找树
 */
public class IBinarySearchTree<E extends Comparable<E>>
        extends IAbstractTree<E> {
    private TreeNode<E> root;
    protected int size = 0;

    public IBinarySearchTree() {

    }

    public IBinarySearchTree(E[] objects) {
        for (int i = 0; i < objects.length; i++) {
            insert(objects[i]);
        }
    }

    @Override
    public boolean insert(E e) {
        if (root == null) {
            root = new TreeNode<>(e);
        } else {
            TreeNode<E> parent = null;
            TreeNode<E> current = root;
            while (current != null) {
                if (e.compareTo(current.element) < 0) {
                    parent = current;
                    current = current.left;
                } else if (e.compareTo(current.element) > 0) {
                    parent = current;
                    current = current.right;
                } else {
                    return false;
                }
            }
            if (e.compareTo(parent.element) < 0) {
                parent.left = new TreeNode<>(e);
            } else {
                parent.right = new TreeNode<>(e);
            }
        }
        size++;
        return true;
    }

    @Override
    public boolean search(E e) {
        TreeNode<E> current = root;
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete(E e) {
        TreeNode<E> parent = null;
        TreeNode<E> current = root;
        //查找该元素在树中的位置
        while (current != null) {
            if (e.compareTo(current.element) < 0) {
                parent = current;
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                parent = current;
                current = current.right;
            } else {
                break;
            }
        }
        //不存在该元素则返回false
        if (current == null) {
            return false;
        }
        /* 被删除的节点没有左子节点 */
        if (current.left == null) {
            //如果parent为空，则说明删除的是根节点，这时根节点的右子节点成为新的根节点
            if (parent == null) {
                root = current.right;
            } else {
                //如果parent不为空，且被删除的节点元素小于parent，则说明删除的是parent的左子节点
                //这时只需要把parent的左子节点指向删除元素的右子节点就可以了
                //反之，删除的是parent的右子节点，然后把parent的右子节点指向删除元素的右子节点
                if (e.compareTo(parent.element) < 0) {
                    parent.left = current.right;
                } else {
                    parent.right = current.right;
                }
            }
        } else {
            /* 被删除的节点有左子节点 */
            TreeNode<E> parentOfRightMost = current;
            TreeNode<E> rightMost = current.left;
            //查找左子树中的最大节点
            while (rightMost.right != null) {
                parentOfRightMost = rightMost;
                rightMost = rightMost.right;
            }
            //将被删除节点的左子树中的最大节点赋值给删除的节点
            current.element = rightMost.element;
            if (parentOfRightMost.right == rightMost) {
                parentOfRightMost.right = rightMost.left;
            } else {
                //特殊情况：被删除节点的左子树的根节点就是左子树中的最大节点（parentOfRightMost = current)
                parentOfRightMost.left = rightMost.left;
            }
        }
        size--;
        return true;
    }

    /**
     * 前序遍历（根 -> 左 -> 右）
     */
    @Override
    public void preOrder() {
        Stack<TreeNode<E>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode<E> temp = stack.pop();
            System.out.print(temp.element + " ");
            if (temp.right != null) {
                stack.push(temp.right);
            }
            if (temp.left != null) {
                stack.push(temp.left);
            }
        }
    }

    /**
     * 中序遍历（左 -> 根 -> 右）
     */
    @Override
    public void inOrder() {
        Stack<TreeNode<E>> stack = new Stack<>();
        TreeNode<E> temp = root;
        while (!stack.isEmpty() || temp != null) {
            if (temp != null) {
                stack.push(temp);
                temp = temp.left;
            } else {
                temp = stack.pop();
                System.out.print(temp.element + " ");
                temp = temp.right;
            }
        }
    }

    /**
     * 后序遍历（左 -> 右 -> 根）
     */
    @Override
    public void postOrder() {
        Stack<TreeNode<E>> outStack = new Stack<>();
        Stack<TreeNode<E>> tempStack = new Stack<>();
        TreeNode<E> temp = root;
        tempStack.push(temp);
        while (!tempStack.isEmpty()) {
            temp = tempStack.pop();
            outStack.push(temp);
            if (temp.left != null) {
                tempStack.push(temp.left);
            }
            if (temp.right != null) {
                tempStack.push(temp.right);
            }
        }
        while (!outStack.isEmpty()) {
            System.out.print(outStack.pop().element + " ");
        }
    }

    /**
     * 层序遍历
     */
    public void levelTraverse() {
        Queue<TreeNode<E>> queue = new LinkedList<>();
        TreeNode<E> temp = root;
        queue.add(temp);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.element + " ");
            if (temp.left != null) {
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
        }
    }

    /**
     * 搜索路径
     */
    public List<TreeNode<E>> pathOfSearch(E e) {
        List<TreeNode<E>> list = new ArrayList<>();
        TreeNode<E> current = root;
        while (current != null) {
            list.add(current);
            if (e.compareTo(current.element) < 0) {
                current = current.left;
            } else if (e.compareTo(current.element) > 0) {
                current = current.right;
            } else {
                break;
            }
        }
        return list;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new InOrderIterator();
    }

    private class InOrderIterator implements Iterator<E> {
        private ArrayList<E> list = new ArrayList<>();
        private int current = 0;

        /**
         * 将树以中序遍历添加到list中
         */
        InOrderIterator() {
            inOrder();
        }

        @Override
        public boolean hasNext() {
            return current < list.size();
        }

        @Override
        public E next() {
            return list.get(current++);
        }


        @Override
        public void remove() {
            delete(list.get(current));
            list.clear();
            inOrder();
        }

        private void inOrder() {
            inOrder(root);
        }

        /**
         * 递归实现树的中序遍历
         */
        private void inOrder(TreeNode<E> root) {
            if (root == null) {
                return;
            }
            inOrder(root.left);
            list.add(root.element);
            inOrder(root.right);
        }
    }

    public static class TreeNode<E extends Comparable<E>> {
        E element;
        TreeNode<E> left;
        TreeNode<E> right;

        TreeNode(E e) {
            element = e;
        }

        public E getElement() {
            return element;
        }
    }
}
