package com.hm.sort;

import java.util.Arrays;

/**
 * Created by dumingwei on 2017/5/23.
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。
 * 堆是一个近似完全二叉树的结构，并同时满足堆的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 */
public class HeapSort {

    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    /**
     * 堆排序的主要入口方法，共两步。
     */
    public void sort() {
        /**
         * 第一步：将数组堆化
         *  beginIndex = 第一个非叶子节点。
         *  从第一个非叶子节点开始即可。无需从最后一个叶子节点开始。
         *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
         */
        int len = arr.length - 1;
        int beginIndex = (len - 1) >> 1;
        for (int i = beginIndex; i >= 0; i--) {
            maxHeapify(i, len);
        }

        /**
         * 第二步：对堆化数据排序
         * 每次都是移出最顶层的根节点A[0]，与最尾部节点位置调换，同时遍历长度 - 1。
         * 然后从新整理被换到根节点的末尾元素，使其符合堆的特性。
         * 直至未排序的堆长度为 0。
         */
        for (int i = len; i > 0; i--) {
            swap(0, i);
            maxHeapify(0, i - 1);
        }
    }

    private void swap(int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * 调整索引为 index 处的数据，使其符合堆的特性。
     *
     * @param index 需要堆化处理的数据的索引
     * @param len   未排序的堆（数组）的长度
     */
    private void maxHeapify(int index, int len) {
        int li = (index << 1) + 1;//左子节点索引
        int ri = li + 1;//右子节点索引
        int cMax = li;//子节点最大索引值，默认左子节点
        if (li > len) return;//左子节点超出计算范围，直接返回
        if (ri <= len && arr[ri] > arr[li]) {
            //先判断右子节点那个较大
            cMax = ri;
        }
        if (arr[cMax] > arr[index]) {
            swap(cMax, index);//如果父节点被子节点调换
            maxHeapify(cMax, len);//则余姚继续判断换下后的父节点是否符合堆特性
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9,12,17,30,50,20,60,65,4,49};
        new HeapSort(arr).sort();
        System.out.println(Arrays.toString(arr));
    }

}
