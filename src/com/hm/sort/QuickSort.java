package com.hm.sort;

import java.util.Arrays;

/**
 * Crete by dumingwei on 2026/06/15
 * 快速排序（Quick Sort）
 * <p>
 * Desc:
 * 快速排序使用分治法（Divide and Conquer）策略：
 * 1. 从数列中挑选一个元素作为「基准」（pivot）。
 * 2. 分区（partition）：把所有比基准小的元素放到基准左边，比基准大的放到右边，
 * 分区结束后基准就处于最终的正确位置。
 * 3. 递归地对基准左、右两个子数组重复以上过程。
 * <p>
 * 解题思路：
 * 这里给出两种常见的分区写法：
 * - 挖坑法（Hoare 思想的填坑变体）：以最左元素为坑，左右指针向中间夹逼，
 * 不断把元素填到坑里，最后把基准放回坑位。
 * - Lomuto 分区：以最右元素为基准，用一个慢指针标记小于基准的边界。
 * 另外提供「随机化基准」以规避近乎有序数组退化为 O(n^2) 的最坏情况。
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] a1 = {8, 4, 9, 1, 10, 6};
        quickSort(a1, 0, a1.length - 1);
        System.out.println("挖坑法:        " + Arrays.toString(a1));

//        int[] a2 = {8, 4, 9, 1, 10, 6};
//        quickSortLomuto(a2, 0, a2.length - 1);
//        System.out.println("Lomuto 分区:   " + Arrays.toString(a2));
//
//        int[] a3 = {8, 4, 9, 1, 10, 6};
//        quickSortRandom(a3, 0, a3.length - 1);
//        System.out.println("随机化基准:    " + Arrays.toString(a3));
//
//        // 边界用例：空数组、单元素、含重复元素、已逆序
//        int[] a4 = {5, 5, 3, 8, 3, 1};
//        quickSort(a4, 0, a4.length - 1);
//        System.out.println("含重复元素:    " + Arrays.toString(a4));
//
//        int[] a5 = {5, 4, 3, 2, 1};
//        quickSort(a5, 0, a5.length - 1);
//        System.out.println("逆序数组:      " + Arrays.toString(a5));
    }

    /* ===================== 解法一：挖坑法 ===================== */

    /**
     * 快速排序入口（挖坑法）。
     *
     * @param arr  待排序数组
     * @param low  起始下标（首次调用传 0）
     * @param high 结束下标（首次调用传 arr.length - 1）
     */
    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort(arr, low, pivotIndex - 1);
            quickSort(arr, pivotIndex + 1, high);
        }
    }


    public static void quickSort2(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partition(arr, low, high);
            quickSort2(arr, low, pivotIndex - 1);
            quickSort2(arr, pivotIndex + 1, high);
        }
    }

    /**
     * 挖坑法分区：以 arr[low] 为基准（第一个坑），左右指针向中间夹逼，
     * 把小的填到左坑、大的填到右坑，最终把基准放回，返回其最终下标。
     */
    private static int partition(int[] arr, int low, int high) {
        int pivot = arr[low]; // 基准值，arr[low] 此刻视为空坑
        while (low < high) {
            // 从右向左找第一个小于 pivot 的元素，填到左坑
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            arr[low] = arr[high]; // high 处元素填入左坑，high 变为新坑

            // 从左向右找第一个大于 pivot 的元素，填到右坑
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low]; // low 处元素填入右坑，low 变为新坑
        }
        arr[low] = pivot; // low == high，基准归位
        return low;
    }

    private static int partition2(int[] arr, int low, int high) {
        int pivot = arr[low];
        while (low < high) {
            //从右向左找比pivot小的值，放在左边
            while (low < high && arr[high] >= pivot) {
                high--;
            }
            //找到
            arr[low] = arr[high];

            //从左往右找，比pivot大的，放右边
            while (low < high && arr[low] <= pivot) {
                low++;
            }
            arr[high] = arr[low];
        }

        arr[low] = pivot;
        return low;
    }

    /* ===================== 解法二：Lomuto 分区 ===================== */

    public static void quickSortLomuto(int[] arr, int low, int high) {
        if (low < high) {
            int pivotIndex = partitionLomuto(arr, low, high);
            quickSortLomuto(arr, low, pivotIndex - 1);
            quickSortLomuto(arr, pivotIndex + 1, high);
        }
    }

    /**
     * Lomuto 分区：以 arr[high] 为基准，i 标记「小于基准区」的右边界。
     * 遍历过程中把小于基准的元素换到左侧，最后把基准换到 i+1。
     */
    private static int partitionLomuto(int[] arr, int low, int high) {
        int pivot = arr[high];
        int i = low - 1; // 小于基准区的最后一个位置
        for (int j = low; j < high; j++) {
            if (arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }
        swap(arr, i + 1, high); // 基准归位
        return i + 1;
    }

    /* ===================== 解法三：随机化基准 ===================== */

    public static void quickSortRandom(int[] arr, int low, int high) {
        if (low < high) {
            // 在 [low, high] 内随机选一个下标，与 low 交换后复用挖坑法
            int randomIndex = low + (int) (Math.random() * (high - low + 1));
            swap(arr, low, randomIndex);
            int pivotIndex = partition(arr, low, high);
            quickSortRandom(arr, low, pivotIndex - 1);
            quickSortRandom(arr, pivotIndex + 1, high);
        }
    }

    private static void swap(int[] arr, int i, int j) {
        if (i == j) {
            return;
        }
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
