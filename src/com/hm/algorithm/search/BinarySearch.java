package com.hm.algorithm.search;

/**
 * Created by dumingwei on 2017/5/25.
 * 二分查找是一种在有序数组中查找某一特定元素的搜索算法。
 * 搜索过程从数组的中间元素开始，如果中间元素正好是要查找的元素，则搜索过程结束；
 * 如果某一特定元素大于或者小于中间元素，则在数组大于或小于中间元素的那一半中查找，而且跟开始一样从中间元素开始比较。
 * 如果在某一步骤数组为空，则代表找不到。这种搜索算法每一次比较都使搜索范围缩小一半。
 */
public class BinarySearch {

    public static void main(String args[]) {
        int[] arr = new int[]{1, 2, 5, 7, 8, 9,22};
        int result=search(arr, 0, arr.length - 1, 22);
        System.out.println(result);
    }

    private static int search(int arr[], int start, int end, int key) {
        int mid;
        while (start <= end) {
            mid = start + (end - start) / 2;//直接平均可能會溢位，所以用此算法
            if (arr[mid] < key) {
                start = mid + 1;
            } else if (arr[mid] > key) {
                end = mid - 1;
            } else return mid;
        }
        return -1;
    }
}
