package com.hm.leetcode.code15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 三数之和.md
 * “三数之和” 是一个经典的算法问题，常见于 LeetCode（题目编号 15）。
 * 问题的描述是：给定一个整数数组 nums，
 * 找出所有满足条件的三元组 [nums[i], nums[j], nums[k]]，
 * 使得 nums[i] + nums[j] + nums[k] = 0，
 * 且三元组中的元素不能重复使用（即 i != j != k）。结果中不能包含重复的三元组。
 * 看懂了，双指针方法挺好理解。
 */
public class ThreeSum {

    // 测试代码
    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        ThreeSum solution = new ThreeSum();
        //int[] nums = {-1, 0, 1, 2, -1, -4, -4};
        //先排序好，简单理解
        int[] nums = {-4, -4, -1, 0, 1, 2, 2, 2};
        List<List<Integer>> result = solution.threeSum(nums);
        System.out.println(result); // 输出: [[-1, -1, 2], [-1, 0, 1]]
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;

        // 如果数组长度小于 3，直接返回空结果
        if (n < 3) {
            return result;
        }

        // 排序数组
        Arrays.sort(nums);

        // 遍历固定第一个数
        for (int i = 0; i < n - 2; i++) {
            // 如果当前数大于 0，后面的数更大，和不可能为 0
            if (nums[i] > 0) {
                break;
            }

            // i为0的时候，不需要判断。大于0，判断 跳过重复的 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 双指针
            int left = i + 1;
            int right = n - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    // 找到一个解，加入结果
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));

                    // 跳过重复的 left
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 跳过重复的 right
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    // 移动指针
                    left++;
                    right--;
                } else if (sum < 0) {
                    // 和太小，left 右移
                    left++;
                } else {
                    // 和太大，right 左移
                    right--;
                }
            }
        }

        return result;
    }

}