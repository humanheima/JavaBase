package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 希尔排序
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
