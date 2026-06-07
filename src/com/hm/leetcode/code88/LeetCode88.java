package com.hm.leetcode.code88;

/**
 * Created by dumingwei on 2022/4/4.
 * <p>
 * Desc:88. 合并两个有序数组
 * <p>
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * <p>
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * <p>
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-sorted-array
 */
public class LeetCode88 {


    public static void main(String[] args) {
        LeetCode88 leetCode88 = new LeetCode88();
        leetCode88.test1();
        //leetCode88.test2();
        //leetCode88.test3();
    }

    private void test1() {
        int[] nums1 = {1, 2, 3, 0, 0, 0};
        int m = 3;
        int[] nums2 = {2, 5, 6};
        int n = 3;
        merge(nums1, m, nums2, n);

        for (int i : nums1) {
            System.out.print(i + " ");
        }
        System.out.println("\n------------");
    }

    private void test2() {
        int[] nums1 = {1};
        int m = 1;
        int[] nums2 = {};
        int n = 0;
        merge(nums1, m, nums2, n);

        for (int i : nums1) {
            System.out.print(i + " ");
        }
        System.out.println("\n------------");
    }

    private void test3() {
        int[] nums1 = {0};
        int m = 0;
        int[] nums2 = {1};
        int n = 1;
        merge(nums1, m, nums2, n);

        for (int i : nums1) {
            System.out.print(i + " ");
        }
        System.out.println("\n------------");
    }

    /**
     * @param nums1
     * @param m     nums1 中需要排序的元素
     * @param nums2
     * @param n     nums2 中需要排序的元素个数
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // p1 指向 nums1 的有效元素末尾
        int p1 = m - 1;
        // p2 指向 nums2 的末尾
        int p2 = n - 1;
        // p 指向合并后 nums1 的末尾
        int p = m + n - 1;

        // 当 nums1 和 nums2 都有元素时
        while (p1 >= 0 && p2 >= 0) {
            if (nums1[p1] > nums2[p2]) {
                nums1[p] = nums1[p1];
                p1--;
            } else {
                nums1[p] = nums2[p2];
                p2--;
            }
            p--;
        }

        // 如果 nums2 还有剩余元素，复制到 nums1
        while (p2 >= 0) {
            nums1[p] = nums2[p2];
            p2--;
            p--;
        }
    }

}
