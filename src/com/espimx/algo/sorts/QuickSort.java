package com.espimx.algo.sorts;

import java.util.Arrays;

public class QuickSort {
    public static void main(String[] args) {
        int[] data = {2, 1, 7, 3, 6, 8, 9, 0, 5};
        quickSort(data);
        System.out.println(Arrays.toString(data));
    }

    public static void quickSort(int[] data) {
        quickSort(data, 0, data.length - 1);
    }

    private static void quickSort(int[] data, int start, int end) {
        if (start >= end) {
            return;
        }

        int index = partition(data, start, end);

        quickSort(data, start, index - 1);
        quickSort(data, index + 1, end);
    }

    /**
     * 切分函数(这里是双向切分)，返回切分点
     */
    private static int partition(int[] data, int start, int end) {
        int element = data[start];  //选取第一个元素作为基准元素
        int left = start;
        int right = end;

        while (left != right) {
            //从右往左遍历数组，如果大于等于基准元素，则右指针往左移一位
            while (left < right && data[right] > element) {
                right--;
            }
            //从左往右遍历数组，如果小于等于基准元素，则左指针往右移一位
            while (left < right && data[left] <= element) {
                left++;
            }
            if (left < right) {
                int temp = data[left];
                data[left] = data[right];
                data[right] = temp;
            }
        }

        //将基准元素与指针重合点的元素交换
        data[start] = data[left];
        data[left] = element;

        return left;
    }
}
