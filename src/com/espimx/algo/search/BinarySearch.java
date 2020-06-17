package com.espimx.algo.search;

public class BinarySearch {
    /**
     * 以循环的方式实现二分搜索
     */
    public int binarySearchWithCycle(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length - 1;

        while (left <= right) {
            //int middle = (left + right) / 2;          //加法的方式可能会出现溢出，所以应该使用减法的方式
            int middle = left + ((right - left) >> 1);  //这里要注意操作符的优先级！
            if (array[middle] < target) {
                left = middle + 1;
            } else if (array[middle] > target) {
                right = middle - 1;
            } else {
                return middle;
            }
        }
        return -1;
    }

    /**
     * 以递归的方式实现二分搜索
     */
    public int binarySearchWithRecursive(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        return search(array, target, 0, array.length - 1);
    }

    /**
     * 二分搜索查找最左侧边界
     * 比如数组{1, 2, 3, 3, 3, 5, 7, 12}，返回2
     */
    public int binarySearchTargetLeftIndex(int[] array, int target) {
        if (array.length == 0) {
            return -1;
        }
        int left = 0;
        int right = array.length;
        while(left < right) {
            int middle = left + ((right - left) >> 1);
            if (array[middle] < target) {
                left = middle + 1;
            } else if (array[middle] > target) {
                right = middle;
            } else {
                right = middle;
            }
        }
        if (left == array.length) {
            return -1;
        }
        return array[left] == target ? left : -1;
    }

    private int search(int[] array, int target, int left, int right) {
        if (left > right) {
            return -1;
        }
        int middle = left + ((right - left) >> 1);
        if (array[middle] < target) {
            return search(array, target, middle + 1, right);
        } else if (array[middle] > target) {
            return search(array, target, left, middle - 1);
        } else {
            return middle;
        }
    }
}
