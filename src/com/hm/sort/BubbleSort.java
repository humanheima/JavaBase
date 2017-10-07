package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 冒泡排序
 * 1.比较相邻的元素。如果第一个比第二个大，就交换他们两个。
 * 2.对每一对相邻元素作同样的工作，从开始第一对到结尾的最后一对。这步做完后，最后的元素会是最大的数。
 * 3.针对所有的元素重复以上的步骤，除了最后一个。
 * 4.持续每次对越来越少的元素重复上面的步骤，直到没有任何一对数字需要比较。
 * 最坏时间复杂度O(n方)
 * 最优时间复杂度O(n)
 * 平均时间复杂度O(n方)
 */
public class BubbleSort {

    private static int[] array = new int[]{1, 2, 3, 4};

    public static void main(String args[]) {
        sort(array);
    }

    private static void sort(int[] arr) {

        int len = arr.length;
        int temp;
        for (int i = 0; i < len - 1; i++) {
            System.out.println("i==" + i);
            boolean isOver = true;
            for (int j = 0; j < len - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isOver = false;
                }
            }
            if (isOver)
                break;
        }
        for (int i : arr) {
            System.out.print(i + ",");
        }

    }

    /*private static void sort(int[] array) {
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
    }*/
}
