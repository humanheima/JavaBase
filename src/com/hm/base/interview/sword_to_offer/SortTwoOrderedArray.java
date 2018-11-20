package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/19
 * <p>
 * Desc:有两个排序的数组A1和A2，内存在A1的末尾有足够多的空余空间容纳A2。请实现一个函数，把A2中的所有数字插入到A1中并且所有数字是排序的。
 * <p>
 * 注意一点：
 * 1. 从后向前开始比较。
 * 2. 如果有一个数组已经到头了，可以直接把另外一个数组剩余部分直接放到合适的位置上。
 */
public class SortTwoOrderedArray {

    public static void main(String[] args) {

        int[] arrA = {1, 3, 4, 6, 7, 10};
        int[] arrB = {1, 4, 5, 8};
        sort(arrA, arrB);

    }

    public static void sort(int[] arrA, int[] arrB) {

        int lengthA = arrA.length;
        int lengthB = arrB.length;
        int totalLength = lengthA + lengthB;
        int[] arrC = new int[totalLength];

        int i = lengthA - 1;
        int j = lengthB - 1;
        int k = totalLength - 1;
        while (i >= 0 && j >= 0) {
            if (arrA[i] > arrB[j]) {
                arrC[k--] = arrA[i--];
            } else {
                arrC[k--] = arrB[j--];
            }
        }
        /**
         * 如果其中一个数组已经到头了，就把另一个数组剩下的部分就不需要比较了，直接放置到合适的位置。
         */
        while (i >= 0) {
            arrC[k--] = arrA[i--];
        }
        while (j >= 0) {
            arrC[k--] = arrB[j--];
        }
        arrA = arrC;

        for (int i1 : arrA) {
            System.out.print(i1 + " ");
        }
    }

}
