package com.hm.sort;

import java.util.Arrays;

/**
 * Created by dumingwei on 2017/5/23.
 * 是创建在归并操作上的一种有效的排序算法，效率为O(n log n)。1945年由约翰·冯·诺伊曼首次提出。
 * 该算法是采用分治法（Divide and Conquer）的一个非常典型的应用，且各层分治递归可以同时进行。
 */
public class MergeSort {


    public void mergeSort(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        mergeSortRecursive(arr, result, 0, len - 1);
    }

    private void mergeSortRecursive(int[] arr, int[] result, int start, int end) {
        if (start >= end)
            return;
        int len = end - start;
        int mid = (len >> 1) + start;
        int start1 = start, end1 = mid;
        int start2 = mid + 1, end2 = end;
        mergeSortRecursive(arr, result, start1, end1);
        mergeSortRecursive(arr, result, start2, end2);
        int k = start;
        while (start1 <= end1 && start2 <= end2) {
            if (arr[start1] < arr[start2]) {
                result[k] = arr[start1];
                k++;
                start1++;
            } else {
                result[k] = arr[start2];
                k++;
                start2++;
            }
        }
        while (start1 <= end1) {
            result[k] = arr[start1];
            k++;
            start1++;
        }
        while (start2 <= end2) {
            result[k] = arr[start2];
            k++;
            start2++;
        }
        for (k = start; k <= end; k++) {
            arr[k] = result[k];
        }
    }

    public void mergeSortIterate(int[] arr) {
        int len = arr.length;
        int[] result = new int[len];
        int block;
        int start;
        for (block = 1; block < len * 2; block *= 2) {
            for (start = 0; start < len; start += 2 * block) {
                int low = start;
                int mid = (start + block) < len ? (start + block) : len;
                int high = (start + 2 * block) < len ? (start + 2 * block) : len;
                //两个块的起始下标及结束下标
                int start1 = low, end1 = mid;
                int start2 = mid, end2 = high;
                //开始对两个block进行归并排序
                while (start1 < end1 && start2 < end2) {
                    if (arr[start1] < arr[start2]) {
                        result[low] = arr[start1];
                        start1++;
                    } else {
                        result[low] = arr[start2];
                        start2++;
                    }
                    low++;
                }
                while (start1 < end1) {
                    result[low] = arr[start1];
                    low++;
                    start1++;
                }
                while (start2 < end2) {
                    result[low] = arr[start2];
                    low++;
                    start2++;
                }
            }
            int[] temp = arr;
            arr = result;
            result = temp;
        }
    }

    public static void main(String args[]) {
        int[] arr = new int[]{30, 50, 9, 12};
        new MergeSort().mergeSortIterate(arr);
        System.out.println(Arrays.toString(arr));
    }
}
