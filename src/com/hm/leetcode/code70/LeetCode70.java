package com.hm.leetcode.code70;

import java.util.Arrays;

/**
 * Create by dumingwei on 2026-06-09
 * Desc: LeetCode 70. 爬楼梯
 * <p>
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 * <p>
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 * <p>
 * 提示：
 * 1 <= n <= 45
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/climbing-stairs/
 */
public class LeetCode70 {

    public static void main(String[] args) {
        LeetCode70 solution = new LeetCode70();

        // 标准示例 + 边界
        test(solution, 1, 1);
        test(solution, 2, 2);
        test(solution, 3, 3);
        test(solution, 4, 5);
        test(solution, 5, 8);
        test(solution, 10, 89);
        test(solution, 45, 1836311903);  // 题目上限，验证 int 范围
    }

    private static void test(LeetCode70 solution, int n, int expected) {
        int result = solution.climbStairs(n);
        boolean ok = result == expected;
        System.out.printf("n=%-3d  结果=%-12d  期望=%-12d  %s%n",
                n, result, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 动态规划（滚动变量优化，空间 O(1)）
     * 本质是斐波那契数列：f(n) = f(n-1) + f(n-2)
     * 使用三个变量不断滚动更新，避免使用 O(n) 数组。
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int prev2 = 1;   // f(i-2)
        int prev1 = 2;   // f(i-1)
        int curr = 0;    // f(i)

        for (int i = 3; i <= n; i++) {
            curr = prev2 + prev1;
            prev2 = prev1;
            prev1 = curr;
        }
        return curr;
    }
}
