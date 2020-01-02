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
        stack.printStack();
        stack.pop();
        stack.printStack();
    }
}
