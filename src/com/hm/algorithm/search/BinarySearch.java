package com.hm.algorithm.search;

import java.util.Comparator;

/**
 * Created by dumingwei on 2017/5/25.
 * 二分查找是一种在有序数组中查找某一特定元素的搜索算法。
 * 搜索过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；
 * 如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找，而且跟开始一样从中间元素开始比较。
 * 如果在某一步骤数组为空，则代表找不到。这种搜索算法每一次比较都使搜索范围缩小一半。
 */
public class BinarySearch {

    public static void main(String args[]) {
        //Integer[] arr = new Integer[]{1, 2, 5, 7, 8, 9, 22};
        Integer[] arr = new Integer[]{1, 3, 22};
        //int result = search(arr, 0, arr.length - 1, 22);
        int result = binarySearch(arr, 0, arr.length - 1, 22);
        System.out.println(result);
    }

    private static int search(int arr[], int start, int end, int key) {
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;//直接平均可能会溢出，所以用此算法
            if (arr[mid] < key) {
                start = mid + 1;
            } else if (arr[mid] > key) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 循环实现二分查找
     *
     * @param arr
     * @param key
     * @param comparator
     * @param <T>
     * @return
     */
    public static <T> int binarySearch(T[] arr, T key, Comparator<T> comparator) {
        if (arr==null){
            return -1;
        }
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            int cmp = comparator.compare(arr[mid], key);
            if (cmp < 0) {
                low = mid + 1;
            } else if (cmp > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }


    /**
     * 使用递归的方式实现二分查找
     *
     * @param arr
     * @param low
     * @param high
     * @param key
     * @param <T>
     * @return
     */
    public static <T extends Comparable<T>> int binarySearch(T[] arr, int low, int high, T key) {

        if (low <= high) {
            int mid = low + (high - low) / 2;
            if (key.compareTo(arr[mid]) == 0) {
                return mid;
            } else if (key.compareTo(arr[mid]) > 0) {
                return binarySearch(arr, mid + 1, high, key);
            } else if (key.compareTo(arr[mid]) < 0) {
                return binarySearch(arr, low, mid - 1, key);
            }
        }
        return -1;
    }

}
