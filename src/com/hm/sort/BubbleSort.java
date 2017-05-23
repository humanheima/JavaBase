package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 冒泡排序
 */
public class BubbleSort {

    private static int[] array = new int[]{1, 2, 3, 5, 4};

    public static void main(String args[]) {
        sort(array);
    }

    private static void sort(int[] array) {
        int n = array.length;
        int temp;
        for (int i = 0; i < n - 1; i++) {
            boolean isOver = true;
            for (int j = n - 1 - i; j > i; j--) {
                if (array[j] < array[j - 1]) {
                    temp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = temp;
                    isOver = false;
                }
            }
            if (isOver)
                break;
        }
        for (int i : array) {
            System.out.print(i + ",");
        }
    }
}
