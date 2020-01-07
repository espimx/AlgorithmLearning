package com.espimx.algo.sorts;

import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {
    public static void main(String[] args) {
        int[] data = {2, 1, 7, 3, 6, 8, 9, 0, 5};
        bubbleSort_03(data);
        System.out.println(Arrays.toString(data));
    }

    public static void bubbleSort_01(int[] data) {
        int length = data.length;
        if (length <= 1) {
            return;
        }
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    /**
     * 冒泡排序优化(1)
     * 添加一个标记位，如果一轮循环中没有进行元素交换，说明已经排好序了
     */
    public static void bubbleSort_02(int[] data) {
        int length = data.length;
        if (length <= 1) {
            return;
        }
        for (int i = 0; i < length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < length - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }

    /**
     * 冒泡排序优化(2)
     * 除了加一个标记位外，还可以添加边界，如果在后面没有进行交换，
     * 说明后面这一部分已经是排好序了的，不需要再判断了
     */
    public static void bubbleSort_03(int[] data) {
        int length = data.length;
        if (length <= 1) {
            return;
        }

        int lastExchangeIndex = 0;
        int boundary = length - 1;      //边界
        for (int i = 0; i < length; i++) {
            boolean isSorted = true;
            for (int j = 0; j < boundary; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                    isSorted = false;
                    lastExchangeIndex = j;
                }
            }
            boundary = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
    }
}
