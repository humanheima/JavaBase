package com.hm.sort.handwrite;

/**
 * Created by dumingwei on 2022/4/3.
 * <p>
 * Desc:
 */
public class BubbleSort {

    private static int[] array = new int[]{0, 100, 30, 20, 15, 0, 1, 2, 3, 4};

    public static void main(String[] args) {

        //sort(array);
        sort2(array);
        for (int i : array) {
            System.out.print(i + " ");
        }
    }

    private static void sort2(int[] array) {
        int lastIndex = array.length - 1;
        for (int i = 0; i < lastIndex; i++) {
            for (int j = 0; j < lastIndex - i; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }

}
