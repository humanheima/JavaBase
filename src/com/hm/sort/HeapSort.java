package com.hm.sort;

import java.util.Arrays;

/**
 * Created by dumingwei on 2017/5/23.
 * 堆排序（Heapsort）是指利用堆这种数据结构所设计的一种排序算法。
 * 堆是一个近似完全二叉树的结构，并同时满足堆的性质：即子结点的键值或索引总是小于（或者大于）它的父节点。
 * <p>
 * 参考链接：https://www.cnblogs.com/skywang12345/p/3602162.html 但是算法使用递归，不使用循环
 */
public class HeapSort {

    private int[] arr;

    public HeapSort(int[] arr) {
        this.arr = arr;
    }

    public static void main(String[] args) {
        int[] arr = new int[]{9, 12, 17, 30, 50, 20, 60, 65, 4, 49};
        new HeapSort(arr).sort();
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 堆排序的主要入口方法，共两步。
     */
    public void sort() {
        /**
         * 第一步：将数组堆化
         *
         *  叶子节点可以看作已符合堆要求的节点，根节点就是它自己且自己以下值为最大。
         * 例如最后一个节点的index是 lastIndex
         * 父节点index是 (lastIndex-1)/2 =lastIndex/2-0.5，也就是(lastIndex/2-1)
         * 我们把这个节点叫做 beginIndex , 大于beginIndex以后的节点的子节点都是超出数组长度范围的，不用处理。
         *
         */
        int len = arr.length - 1;
        //int beginIndex = (len - 1)/2 = len/2-0.5 =len/2-1;
        int beginIndex = len / 2 - 1;
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
     * 调整索引为 currentIndex 处的数据，使其符合堆的特性。
     *
     * @param currentIndex 需要堆化处理的数据的索引
     * @param len          未排序的堆（数组）的长度
     */
    private void maxHeapify(int currentIndex, int len) {
        int leftIndex = (currentIndex * 2) + 1;//左子节点索引
        int rightIndex = leftIndex + 1;//右子节点索引
        int maxIndex = leftIndex;//子节点最大索引值，默认左子节点
        if (leftIndex > len) return;//左子节点超出计算范围，直接返回
        if (rightIndex <= len && arr[rightIndex] > arr[leftIndex]) {
            //先判断右子节点那个较大
            maxIndex = rightIndex;
        }
        if (arr[maxIndex] > arr[currentIndex]) {
            /**
             * 将maxIndex位置上的value，交换到 currentIndex上。
             * 然后重新检查交换后的 maxIndex位置上的value是否符合堆特性
             */
            swap(maxIndex, currentIndex);//如果父节点被子节点调换
            maxHeapify(maxIndex, len);//则需要继续判断换下后的父节点是否符合堆特性
        }
    }

}
