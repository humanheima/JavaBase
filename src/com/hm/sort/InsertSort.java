package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 插入排序(Insertion Sort)的基本思想是：
 * 每次将一个待排序的记录，按其关键字大小插入到前面已经排好序的子文件中的适当位置，
 * 直到全部记录插入完成为止。
 */
public class InsertSort {

    private static int a[] = {8, 1};

    public static void main(String args[]) {
        sort(a);
    }

    private static void sort(int[] a) {
        int j, temp;
        int n = a.length;
        for (int i = 1; i < n; i++) {
            temp = a[i];
            j = i - 1;
            while (j >= 0 && a[j] > temp) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = temp;
        }

        for (int i : a) {
            System.out.print(i + ",");
        }
    }
}
