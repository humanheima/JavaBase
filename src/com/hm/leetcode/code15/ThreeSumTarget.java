package com.hm.leetcode.code15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和.md
 * 问题的描述是：给定一个整数数组 nums 和 target，
 * 找出所有满足条件的三元组 [nums[i], nums[j], nums[k]]，
 * 使得 nums[i] + nums[j] + nums[k] = target，
 * 且三元组中的元素不能重复使用（即 i != j != k）。结果中不能包含重复的三元组。
 * 看懂了，双指针方法挺好理解。
 */
public class ThreeSumTarget {

    // 测试代码
    public static void main(String[] args) {
        ThreeSumTarget solution = new ThreeSumTarget();

        // 测试用例 1: 通用 target
        int[] nums1 = {-1, 0, 1, 2, -1, -4};
        int target1 = 0;
        List<List<Integer>> result1 = solution.threeSum(nums1, target1);
        System.out.println("Test 1 (target = 0): " + result1); // 预期: [[-1, -1, 2], [-1, 0, 1]]

        // 测试用例 2: 非零 target
        int[] nums2 = {1, 2, 3, 4, 5, 6};
        int target2 = 12;
        List<List<Integer>> result2 = solution.threeSum(nums2, target2);
        System.out.println("Test 2 (target = 12): " + result2); // 预期: [[1, 2, 9], [1, 3, 8], [2, 3, 7], [2, 4, 6], [3, 4, 5]]

        // 测试用例 3: 无解情况
        int[] nums3 = {1, 2, 3};
        int target3 = 12;
        List<List<Integer>> result3 = solution.threeSum(nums3, target3);
        System.out.println("Test 3 (target = 12): " + result3); // 预期: []
    }

    public List<List<Integer>> threeSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // 边界检查：数组长度小于3
        if (n < 3) {
            return result;
        }

        // 排序数组
        Arrays.sort(nums);

        // 固定第一个元素
        for (int i = 0; i < n - 2; i++) {
            // 跳过重复的 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 双指针
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int currentSum = nums[i] + nums[left] + nums[right];

                if (currentSum == target) {
                    // 找到一个解，加入结果
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复的 nums[left]
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的 nums[right]
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (currentSum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return result;
    }


}