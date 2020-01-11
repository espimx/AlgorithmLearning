package com.espimx.algo.test;

import com.espimx.algo.stack.IStack;

public class StackTest {
    public static void main(String[] args) {
        IStack<Integer> stack = new IStack<>();
        stack.push(3);
        stack.push(2);
        stack.push(8);
        stack.push(7);
        stack.push(1);
        System.out.println("容量: " + stack.size());
        System.out.println("栈顶元素: " + stack.peek());
        System.out.println("移出栈顶元素: " + stack.pop());
        System.out.println("容量: " + stack.size());
        System.out.println("是否为空: " + stack.isEmpty());
    }
}
