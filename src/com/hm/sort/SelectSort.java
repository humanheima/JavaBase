package com.hm.sort;

/**
 * Created by dumingwei on 2017/5/22.
 * 选择排序 首先在未排序序列中找到最小（大）元素，存放到排序序列的起始位置，然后，
 * 再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕。
 */
public class SelectSort {

    private static int[] array = new int[]{5, 3, 2, 4, 1};

    public static void main(String args[]) {
        sort(array);
    }

    private static void sort(int[] arr) {
        int len = arr.length;
        int min;
        int temp;
        for (int i = 0; i < len - 1; i++) {
            min = i;
            for (int j = i + 1; j < len; j++) {
                if (arr[min] > arr[j]) {
                    //这轮过后，min指向最小的数据的index
                    min = j;
                }
            }
            //第一轮，把最小的数据放到序列第一的位置
            temp = arr[i];
            arr[i] = arr[min];
            arr[min] = temp;
        }
        for (int i : array) {
            System.out.print(i + ",");
        }
    }


  /*  private static void sort(int[] array) {
        int n = array.length;
        int temp;
        int min;
        for (int i = 0; i < n - 1; i++) {
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
    }*/
}
