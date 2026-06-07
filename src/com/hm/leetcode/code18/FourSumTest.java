package com.hm.leetcode.code18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 18. 四数之和
 * <p>
 * 给你一个由 n 个整数组成的数组 nums，和一个目标值 target。请你找出并返回满足下述全部条件
 * 且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]]（若两个四元组元素一一对应，则认为
 * 两个四元组重复）：
 * 0 <= a, b, c, d < n；a、b、c 和 d 互不相同；nums[a] + nums[b] + nums[c] + nums[d] == target。
 * 你可以按任意顺序返回答案。
 * <p>
 * 示例 1：
 * 输入：nums = [1,0,-1,0,-2,2], target = 0
 * 输出：[[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
 * <p>
 * 示例 2：
 * 输入：nums = [2,2,2,2,2], target = 8
 * 输出：[[2,2,2,2]]
 * <p>
 * 提示：
 * 1 <= nums.length <= 200
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/4sum/
 */
public class FourSumTest {

    public static void main(String[] args) {
        test(new int[]{1, 0, -1, 0, -2, 2}, 0, "[[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]");
        test(new int[]{2, 2, 2, 2, 2}, 8, "[[2, 2, 2, 2]]");
        test(new int[]{}, 0, "[]");
        test(new int[]{0, 0, 0, 0}, 0, "[[0, 0, 0, 0]]");
        test(new int[]{1, 2, 3}, 0, "[]");
        // 溢出用例：四个 10^9 相加 = 4×10^9，超过 int 最大值 2.1×10^9，必须用 long
        test(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296,
                "[]");
    }

    /**
     * 跑「带剪枝双指针 / 基础双指针 / 暴力」三种解法并交叉验证。
     */
    private static void test(int[] nums, int target, String expected) {
        Code18 solution = new Code18();
        String r1 = solution.fourSum(nums.clone(), target).toString();
        String r2 = solution.fourSum2(nums.clone(), target).toString();
        String r3 = bruteForce(nums.clone(), target).toString();
        boolean ok = r1.equals(expected) && r2.equals(expected) && r3.equals(expected);
        System.out.printf("%-32s target=%-12d => %s%n        剪枝:%s 基础:%s 暴力:%s%n",
                Arrays.toString(nums), target, ok ? "✓" : "✗ FAIL", r1, r2, r3);
    }

    /**
     * 暴力四重循环 + 去重（仅用于对照验证）
     * 先排序保证四元组内部有序，再用 Set 去重；用 long 防溢出。
     * 时间 O(n^4)，空间 O(去重集合)。
     */
    private static List<List<Integer>> bruteForce(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        int n = nums.length;
        if (n < 4) {
            return result;
        }
        Arrays.sort(nums);
        Set<List<Integer>> seen = new HashSet<>();
        for (int a = 0; a < n - 3; a++) {
            for (int b = a + 1; b < n - 2; b++) {
                for (int c = b + 1; c < n - 1; c++) {
                    for (int d = c + 1; d < n; d++) {
                        long sum = (long) nums[a] + nums[b] + nums[c] + nums[d];
                        if (sum == target) {
                            List<Integer> quad = Arrays.asList(nums[a], nums[b], nums[c], nums[d]);
                            if (seen.add(quad)) {
                                result.add(quad);
                            }
                        }
                    }
                }
            }
        }
        return result;
    }
}
