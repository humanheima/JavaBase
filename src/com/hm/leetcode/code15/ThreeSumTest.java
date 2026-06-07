package com.hm.leetcode.code15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 15. 三数之和
 * <p>
 * 给你一个整数数组 nums，判断是否存在三元组 [nums[i], nums[j], nums[k]]
 * 满足 i != j、i != k 且 j != k，同时还满足 nums[i] + nums[j] + nums[k] == 0。
 * 请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,0,1,2,-1,-4]
 * 输出：[[-1,-1,2],[-1,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,1]
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：nums = [0,0,0]
 * 输出：[[0,0,0]]
 * <p>
 * 提示：
 * 3 <= nums.length <= 3000
 * -10^5 <= nums[i] <= 10^5
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum/
 */
public class ThreeSumTest {

    public static void main(String[] args) {
        test(new int[]{-1, 0, 1, 2, -1, -4}, "[[-1, -1, 2], [-1, 0, 1]]");
        test(new int[]{0, 1, 1}, "[]");
        test(new int[]{0, 0, 0}, "[[0, 0, 0]]");
        test(new int[]{-4, -4, -1, 0, 1, 2, 2, 2}, "[[-4, 2, 2], [-1, 0, 1]]");
        test(new int[]{1, 2, 3}, "[]");
        test(new int[]{-2, 0, 1, 1, 2}, "[[-2, 0, 2], [-2, 1, 1]]");
    }

    /**
     * 同时跑「双指针」和「暴力法」两种解法，并对比两者结果是否一致、是否符合期望。
     */
    private static void test(int[] nums, String expected) {
        // 排序前先各自拷贝一份，避免两种解法互相影响（双指针会原地排序）
        String r1 = twoPointers(nums.clone()).toString();
        String r2 = bruteForce(nums.clone()).toString();
        boolean ok = r1.equals(expected) && r2.equals(expected);
        System.out.printf("%-28s => 双指针:%s  暴力:%s  期望:%s  %s%n",
                Arrays.toString(nums), r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法一：排序 + 双指针（推荐）
     * 时间 O(n^2)，空间 O(1)（不含排序与结果）。
     * 与 {@link ThreeSum#threeSum(int[])} 实现一致。
     */
    public static List<List<Integer>> twoPointers(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 3) {
            return result;
        }
        Arrays.sort(nums);
        for (int i = 0; i < n - 2; i++) {
            // 最小的数都大于 0，三数之和不可能为 0，提前结束
            if (nums[i] > 0) {
                break;
            }
            // 跳过重复的固定数，避免重复三元组
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 收缩并跳过重复的 left / right
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

    /**
     * 解法二：暴力三重循环 + 去重（仅用于对照验证）
     * 时间 O(n^3)，空间 O(n)（去重集合）。
     * 先排序，保证每个三元组内部有序，再用 Set 去掉重复三元组。
     */
    public static List<List<Integer>> bruteForce(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 3) {
            return result;
        }
        Arrays.sort(nums);
        Set<List<Integer>> seen = new HashSet<>();
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> triple = Arrays.asList(nums[i], nums[j], nums[k]);
                        if (seen.add(triple)) {
                            result.add(triple);
                        }
                    }
                }
            }
        }
        return result;
    }
}
