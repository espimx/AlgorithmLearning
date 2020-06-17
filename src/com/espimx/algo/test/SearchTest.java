package com.espimx.algo.test;

import com.espimx.algo.search.BinarySearch;

public class SearchTest {
    public static void main(String[] args) {
        int[] array = {1, 23, 53, 76, 101, 102, 102, 121, 210, 220, 357, 890};
        BinarySearch search = new BinarySearch();
        int i = search.binarySearchWithCycle(array, 102);
        System.out.println(i);
        int n = search.binarySearchWithRecursive(array, 102);
        System.out.println(n);
        System.out.println(search.binarySearchTargetLeftIndex(array, 102));
    }
}
