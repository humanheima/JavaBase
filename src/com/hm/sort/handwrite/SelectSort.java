package com.hm.sort.handwrite;

/**
 * Created by dumingwei on 2022/4/3.
 * <p>
 * Desc:
 */
public class SelectSort {

    private static int[] array = new int[]{0, 100, 30, 20, 15, 0, 1, 2, 3, 4};

    public static void main(String[] args) {

        sort(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    /**
     * @param array
     */
    private static void sort(int[] array) {
        int lastIndex = array.length - 1;
        int minIndex;
        int temp;
        for (int i = 0; i < lastIndex; i++) {
            minIndex = i;
            for (int j = i + 1; j <= lastIndex; j++) {
                if (array[minIndex] > array[j]) {
                    //这轮过后，min指向最小的数据的index
                    minIndex = j;
                }
            }
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;
        }
    }
}
