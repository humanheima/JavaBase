package com.hm.leetcode.code16;

import java.util.Arrays;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 16. 最接近的三数之和
 * <p>
 * 给你一个长度为 n 的整数数组 nums 和一个目标值 target。
 * 请你从 nums 中选出三个整数，使它们的和与 target 最接近。
 * 返回这三个数的和。
 * 假定每组输入只存在恰好一个解。
 * <p>
 * 示例 1：
 * 输入：nums = [-1,2,1,-4], target = 1
 * 输出：2
 * 解释：与 target 最接近的和是 2 (-1 + 2 + 1 = 2)。
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,0], target = 1
 * 输出：0
 * <p>
 * 提示：
 * 3 <= nums.length <= 1000
 * -1000 <= nums[i] <= 1000
 * -10^4 <= target <= 10^4
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/3sum-closest/
 */
public class ThreeSumClosestTest {

    public static void main(String[] args) {
        test(new int[]{-1, 2, 1, -4}, 1, 2);
        test(new int[]{0, 0, 0}, 1, 0);
        test(new int[]{1, 1, 1, 0}, -100, 2);
        test(new int[]{1, 2, 4, 8, 16, 32, 64, 128}, 82, 82);
        test(new int[]{-3, -2, -5, 3, -4}, -1, -2);
    }

    /**
     * 三种解法跑同一组数据并交叉验证：基础双指针 / 带剪枝双指针 / 暴力。
     */
    private static void test(int[] nums, int target, int expected) {
        int r1 = threeSumClosest(nums.clone(), target);
        int r2 = closestPruned(nums.clone(), target);
        int r3 = bruteForce(nums.clone(), target);
        boolean ok = r1 == expected && r2 == expected && r3 == expected;
        System.out.printf("%-26s target=%-5d => 双指针:%d 剪枝:%d 暴力:%d  期望:%d  %s%n",
                Arrays.toString(nums), target, r1, r2, r3, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法一：排序 + 双指针（基础版，与 {@link LeetCode16#threeSumClosest(int[], int)} 一致）
     * 时间 O(n^2)，空间 O(1)（不含排序）。
     */
    public static int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 谁离 target 更近就更新答案
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                if (sum == target) {
                    return sum; // 完全命中，差值为 0，不可能更优
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }

    /**
     * 解法二：排序 + 双指针 + 剪枝（带边界剪枝与去重）
     * 思路同基础版，额外加入两条剪枝：
     * 1. 固定 i 后，当前能取到的「最小和」已 > target，因后续只会更大，可整体 break；
     * 2. 固定 i 后，当前能取到的「最大和」仍 < target，则这一轮 i 不可能更优，continue。
     * 时间最坏仍 O(n^2)，但常数更小。
     */
    public static int closestPruned(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            // 跳过重复的固定数（对答案无影响，纯加速）
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 剪枝 1：i 配上最小的两个数，和已经 > target，后面只会更大
            int minSum = nums[i] + nums[i + 1] + nums[i + 2];
            if (minSum > target) {
                if (Math.abs(minSum - target) < Math.abs(closestSum - target)) {
                    closestSum = minSum;
                }
                break;
            }
            // 剪枝 2：i 配上最大的两个数，和仍 < target，这一轮 i 不可能更优
            int maxSum = nums[i] + nums[n - 1] + nums[n - 2];
            if (maxSum < target) {
                if (Math.abs(maxSum - target) < Math.abs(closestSum - target)) {
                    closestSum = maxSum;
                }
                continue;
            }
            int left = i + 1, right = n - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                    closestSum = sum;
                }
                if (sum == target) {
                    return sum;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return closestSum;
    }

    /**
     * 解法三：暴力三重循环（仅用于对照验证）
     * 时间 O(n^3)，空间 O(1)。
     */
    public static int bruteForce(int[] nums, int target) {
        int n = nums.length;
        int closestSum = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    if (Math.abs(sum - target) < Math.abs(closestSum - target)) {
                        closestSum = sum;
                    }
                }
            }
        }
        return closestSum;
    }
}
