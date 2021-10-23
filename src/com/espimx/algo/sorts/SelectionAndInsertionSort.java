package com.espimx.algo.sorts;

import java.util.Arrays;

public class SelectionAndInsertionSort {

    public static void main(String[] args) {
        int[] data = {2, 1, 7, 3, 6, 8, 9, 0, 5};
        selectionSort(data);
        int[] array = {2, 1, 7, 3, 6, 8, 9, 0, 5, 12, 10, 16, 15};
        insertionSort(array);
        System.out.println(Arrays.toString(data));
        System.out.println(Arrays.toString(array));
    }

    public static void selectionSort(int[] data) {
        int length = data.length;
        for (int i = 0; i < length; i++) {
            int index = i;
            for (int j = i; j < length; j++) {
                if (data[j] < data[index]) {
                    index = j;
                }
            }
            int temp = data[index];
            data[index] = data[i];
            data[i] = temp;
        }
    }

    public static void insertionSort(int[] data) {
        int length = data.length;
        for (int i = 0; i < length - 1; i++) {
            int next = data[i + 1];
            int curIndex = i;
            while (curIndex >= 0 && next < data[curIndex]) {
                data[curIndex + 1] = data[curIndex];
                curIndex--;
            }
            data[curIndex + 1] = next;
        }
    }
}
