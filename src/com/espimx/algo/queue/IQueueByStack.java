package com.espimx.algo.queue;

import java.util.NoSuchElementException;
import java.util.Stack;

/**
 * 使用两个栈实现的队列
 * 一个栈（栈①）用于做入队操作，每次入队都添加到这个栈中
 * 另一个栈（栈②）左出队操作，出队时如果栈②不为空，直接移出栈顶元素即可
 * 如果栈②为空，则将栈①中的所有元素出栈到栈②中，然后移出栈②的栈顶元素
 * @param <E>
 */
public class IQueueByStack<E> extends IAbstractQueue<E> {
    private Stack<E> enStack = new Stack<>();       //用于入队操作，栈①
    private Stack<E> deStack = new Stack<>();       //用于出队操作，栈②

    @Override
    public void enqueue(E element) {
        enStack.push(element);
        size++;
    }

    @Override
    public E dequeue() {
        //如果栈两个栈都为空说明队列为空
        if (enStack.isEmpty() && deStack.isEmpty()) {
            throw new NoSuchElementException("队列为空");
        }
        size--;
        //如果用于出队的栈不为空，则出队栈移出栈顶元素
        if (!deStack.isEmpty()) {
            return deStack.pop();
        }
        //如果用于出队的栈不为空，则将入队栈中的元素全部放到出队栈中
        while (!enStack.isEmpty()) {
            deStack.push(enStack.pop());
        }
        return deStack.pop();
    }
}
