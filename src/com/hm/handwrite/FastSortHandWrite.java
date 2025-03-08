package com.hm.handwrite;

/**
 * Created by dumingwei on 2020/3/25
 * <p>
 * Desc:手写快速排序
 */
public class FastSortHandWrite {

    private static int a[] = {8, 4, 9, 1, 10, 6};


    public static void main(String[] args) {

        sort(a, 0, a.length - 1);
        for (int i : a) {
            System.out.print(i + ", ");
        }
    }


    public static void sort(int[] a, int left, int right) {
        if (left < right) {

            int i = left;
            int j = right;
            //基准值
            int pivot = a[i];
            while (i < j) {

                while (i < j && a[j] >= pivot) {
                    j--;
                }
                //这一轮，a[j]小于pivot，将a[i]赋值为更小的值。将i+1，向右走
                if (i < j) {
                    a[i] = a[j];
                    i++;
                }

                while (i < j && a[i] <= pivot) {
                    i++;
                }
                //这一轮，a[i]大于pivot，将a[j]赋值为大于pivot的a[i]。将j-1，向左走
                if (i < j) {
                    a[j] = a[i];
                    j--;
                }
            }
            a[i] = pivot;
            sort(a, left, i - 1);
            sort(a, i + 1, right);
        }
    }
}
