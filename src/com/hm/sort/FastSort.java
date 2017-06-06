package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 快速排序
 */
public class FastSort {
    private static int a[] = {4, 8, 9, 1, 10, 4};

    public static void main(String args[]) {
        quickSort(a, 0, a.length - 1);
        for (int i = 0; i < a.length; i++) {
            System.out.print(a[i] + ",");
        }
    }

    private static void quickSort(int[] a, int left, int right) {
        System.out.println("left="+left+",right="+right);
        if (left < right) {
            int i = left;
            int j = right;
            int pivot = a[left];
            while (i < j) {
                while (i < j && a[j] >= pivot) {
                    j--;
                }
                if (i < j) {
                    a[i] = a[j];
                    i++;
                }
                while (i < j && a[i] < pivot) {
                    i++;
                }
                if (i < j) {
                    a[j] = a[i];
                    j--;
                }
            }
            a[i] = pivot;
            quickSort(a, left, i - 1);
            quickSort(a, i + 1, right);
        }
    }

    private static void sort(int[] a, int left, int right) {
        int dp;
        if (left < right) {
            dp = partition(a, left, right);
            sort(a, left, dp - 1);
            sort(a, dp + 1, right);
        }
    }

    private static int partition(int[] a, int left, int right) {
        int pivot = a[left];
        while (left < right) {
            while (left < right && a[right] >= pivot) {
                right--;
            }
            if (left < right) {
                a[left] = a[right];
                left++;
            }
            while (left < right && a[left] <= pivot) {
                left++;
            }
            if (left < right) {
                a[right] = a[left];
                right--;
            }
        }
        a[left] = pivot;
        return left;
    }
}
