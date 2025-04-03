package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/3
 * Desc: LeetCode35 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
 * <p>
 * 请必须使用时间复杂度为 O(log n) 的算法。使用2分查找
 */
class LeetCode35 {


    public static void main(String[] args) {
        LeetCode35 solution = new LeetCode35();
        int[] nums = {1, 3, 5, 6};
        int target = 7;
        //int target = 5;
        int result = solution.searchInsert(nums, target);
        System.out.println(result);
    }

    public int searchInsert(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 防止溢出，等价于 (left + right) / 2

            if (nums[mid] == target) {
                return mid; // 找到目标值，返回索引
            } else if (nums[mid] < target) {
                left = mid + 1; // 目标值在右半部分
            } else {
                right = mid - 1; // 目标值在左半部分
            }
        }

        return left; // 没找到，返回插入位置
    }
}