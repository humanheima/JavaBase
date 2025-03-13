package com.hm.base.interview.android;

/**
 * 以后就用这种写法，来写快速排序
 * 参考 快速排序.md
 */
public class QuickSort {
    // 主方法，调用快速排序

    // 测试代码
    public static void main(String[] args) {
        int[] arr = {5, 3, 8, 6, 2, 7, 1, 4};
        System.out.println("原始数组：");
        printArray(arr);

        quickSort(arr, 0, arr.length - 1);

        System.out.println("排序后数组：");
        printArray(arr);
    }

    public static void quickSort(int[] arr, int low, int high) {
        if (low < high) {
            // 获取分区后的基准位置
            int pivotIndex = partition(arr, low, high);
            // 递归排序左半部分
            quickSort(arr, low, pivotIndex - 1);
            // 递归排序右半部分
            quickSort(arr, pivotIndex + 1, high);
        }
    }

    /**
     * 返回的是基准所在的位置
     *
     * @param arr
     * @param low
     * @param high
     * @return
     */
    // 分区方法
    private static int partition(int[] arr, int low, int high) {
        // 选择最后一个元素作为基准
        int pivot = arr[high];
        // i 表示小于基准的区域边界
        int i = low - 1;

        // 注意这个解释：遍历数组，将小于基准的元素放到左边
        for (int j = low; j < high; j++) {
            if (arr[j] <= pivot) {
                i++;
                // 交换元素
                swap(arr, i, j);
            }
        }
        // 将基准放到正确位置
        swap(arr, i + 1, high);
        return i + 1; // 注意，返回基准位置是 i + 1
    }

    // 交换数组中两个元素
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // 打印数组
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}