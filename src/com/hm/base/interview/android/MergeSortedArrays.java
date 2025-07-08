package com.hm.base.interview.android;

/**
 * 合并两个有序数组.md，看懂了。
 */
public class MergeSortedArrays {

    public static int[] merge(int[] nums1, int[] nums2) {

        int n = nums1.length; // 第一个数组的长度
        int m = nums2.length; // 第二个数组的长度
        int[] result = new int[n + m]; // 结果数组

        int p1 = 0; // 指向 nums1 的指针
        int p2 = 0; // 指向 nums2 的指针
        int p = 0;  // 指向 result 的指针

        // 当两个数组都有元素时，比较并合并
        while (p1 < n && p2 < m) {
            if (nums1[p1] <= nums2[p2]) {
                result[p] = nums1[p1];
                p1++;
            } else {
                result[p] = nums2[p2];
                p2++;
            }
            p++;
        }

        // 如果 nums1 还有剩余元素，追加到结果
        while (p1 < n) {
            result[p] = nums1[p1];
            p1++;
            p++;
        }

        // 如果 nums2 还有剩余元素，追加到结果
        while (p2 < m) {
            result[p] = nums2[p2];
            p2++;
            p++;
        }

        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {1, 3, 5};
        int[] nums2 = {2, 4, 6};
        int[] merged = merge(nums1, nums2);

        System.out.println("合并后的数组:");
        for (int num : merged) {
            System.out.print(num + " ");
        }
    }
}