package com.hm.leetcode.code33;

/**
 * Created by p_dmweidu on 2025/8/3
 * Desc:这道题目要求我们在**旋转排序数组**中查找目标值 `target` 的下标，
 * 时间复杂度必须为 **O(log n)**，
 * 这提示我们需要使用**二分查找**来解决问题。
 * 旋转排序数组是指一个原本升序的数组在某个点 `k` 处被旋转，
 * 例如 `[0,1,2,4,5,6,7]` 旋转后可能变成 `[4,5,6,7,0,1,2]`。
 * 我们的目标是找到 `target` 在旋转后的数组中的下标，如果不存在则返回 `-1`。
 */
public class Solution {


    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        int target = 5;
        Solution solution = new Solution();
        int result = solution.search(nums, target); // 返回 4
        System.out.println(result);
    }
    public int search(int[] nums, int target) {

        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] == target) {
                return mid;
            }

            // 判断左半部分是否有序
            if (nums[mid] >= nums[left]) {
                // 左半部分有序，检查 target 是否在 [left, mid] 范围内
                if (target >= nums[left] && target < nums[mid]) {
                    right = mid - 1; // 在左半部分搜索
                } else {
                    left = mid + 1; // 在右半部分搜索
                }
            } else {
                // 右半部分有序，检查 target 是否在 [mid, right] 范围内
                if (target > nums[mid] && target <= nums[right]) {
                    left = mid + 1; // 在右半部分搜索
                } else {
                    right = mid - 1; // 在左半部分搜索
                }
            }
        }

        return -1; // 未找到目标值
    }

}