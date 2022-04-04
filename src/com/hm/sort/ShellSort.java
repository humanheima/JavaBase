package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 希尔排序
 * 希尔排序(Shell Sort)是插入排序的一种，它是针对直接插入排序算法的改进。
 * <p>
 * 希尔排序又称缩小增量排序，因 DL.Shell 于 1959 年提出而得名。
 * <p>
 * 它通过比较相距一定间隔的元素来进行，各趟比较所用的距离随着算法的进行而减小，直到只比较相邻元素的最后一趟排序为止。
 */
public class ShellSort {

    private static int a[] = {4, 8, 9, 1, 10, 6, 2, 3};

    public static void main(String args[]) {
        sort(a);
    }

    private static void sort(int[] a) {
        int h = 1;
        int j, temp;
        int n = a.length;
        while (h < n / 3)
            h = 3 * h + 1;
        while (h >= 1) {
            for (int i = h; i < n; i++) {
                temp = a[i];
                j = i - h;
                while (j >= 0 && a[j] > temp) {
                    a[j + h] = a[j];
                    j -= h;
                }
                a[j + h] = temp;
            }
            h = h / 3;
        }

        for (int i : a) {
            System.out.print(i + ",");
        }
    }
}
