package com.hm.base.interview.android;

/**
 * 算法解题思路/归并排序.md
 */
public class MergeSort {

    // 测试代码
    public static void main(String[] args) {
        int[] arr = {38, 27, 43, 3, 9, 82, 10};
        System.out.print("原始数组: ");
        printArray(arr);

        sort(arr);

        System.out.print("排序后数组: ");
        printArray(arr);
    }

    // 主函数，用于调用归并排序
    public static void sort(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return;
        }
        mergeSort(arr, 0, arr.length - 1);
    }

    // 递归实现归并排序
    private static void mergeSort(int[] arr, int left, int right) {
        if (left < right) {  // 递归终止条件
            int mid = (left + right) / 2;
            mergeSort(arr, left, mid);      // 排序左半部分
            mergeSort(arr, mid + 1, right); // 排序右半部分
            merge(arr, left, mid, right);   // 合并两部分
        }
    }

    // 合并两个有序子数组
    private static void merge(int[] arr, int left, int mid, int right) {
        // 创建临时数组存储合并结果
        int[] temp = new int[right - left + 1];
        int i = left;      // 左子数组的起始索引
        int j = mid + 1;   // 右子数组的起始索引
        int k = 0;         // 临时数组的索引

        // 比较并合并
        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {  // 使用 <= 保持稳定性
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
            }
        }

        // 处理左子数组剩余元素
        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        // 处理右子数组剩余元素
        while (j <= right) {
            temp[k++] = arr[j++];
        }

        // 将临时数组复制回原数组
//        for (int m = 0; m < temp.length; m++) {
//            arr[left + m] = temp[m];
//        }
        System.arraycopy(temp, 0, arr, left, temp.length);
    }


    // 打印数组的辅助方法
    private static void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}