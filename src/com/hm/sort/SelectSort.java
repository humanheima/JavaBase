package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 选择排序
 */
public class SelectSort {

    private static int[] array = new int[]{5, 3, 2, 4, 1};

    public static void main(String args[]) {
        sort(array);
    }

    private static void sort(int[] array) {
        int n = array.length;
        int temp;
        int min;
        for (int i = 0; i < n-1; i++) {
            min = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[min]) {
                    min = j;
                }
            }
            temp = array[i];
            array[i] = array[min];
            array[min] = temp;
        }
        for (int i : array) {
            System.out.print(i + ",");
        }
    }
}
