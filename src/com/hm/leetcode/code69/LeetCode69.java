package com.hm.leetcode.code69;

/**
 * Crete by dumingwei on 2026-06-14
 * 69. x 的平方根
 * Desc: 给你一个非负整数 x，计算并返回 x 的算术平方根。
 * 由于返回类型是整数，结果只保留整数部分，小数部分将被舍去（向下取整）。
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5。
 *
 * 解题思路：
 * 本质是求满足 k * k <= x 的最大整数 k。
 * 1. 暴力枚举：从 0 开始累加，直到 k * k 超过 x，O(√x)。
 * 2. 二分查找：答案落在 [0, x/2] 区间内且单调，二分逼近，O(log x)。
 *    比较时用 (long) mid * mid 防止 int 溢出。
 * 3. 牛顿迭代：用切线不断逼近 √x，收敛极快，O(log x)。
 *
 * 链接：https://leetcode.cn/problems/sqrtx/
 */
public class LeetCode69 {

    public static void main(String[] args) {
        int[] cases = {0, 1, 2, 4, 8, 16, 2147395599, Integer.MAX_VALUE};
        for (int x : cases) {
            System.out.printf("x=%-12d 暴力=%-6d 二分=%-6d 牛顿=%-6d%n",
                    x, mySqrtBruteForce(x), mySqrt(x), mySqrtNewton(x));
        }
    }

    /**
     * 解法一：暴力枚举
     * 从 0 开始递增，找到第一个平方超过 x 的数，它减一即为答案。
     * 时间复杂度：O(√x)
     * 空间复杂度：O(1)
     */
    public static int mySqrtBruteForce(int x) {
        long i = 0;
        // i 用 long，避免 i * i 在接近 Integer.MAX_VALUE 时溢出
        while (i * i <= x) {
            i++;
        }
        return (int) (i - 1);
    }

    /**
     * 解法二：二分查找（推荐）
     * 在 [0, x/2] 上二分（x>=2 时平方根不会超过 x/2），
     * 找满足 mid * mid <= x 的最大 mid。
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     */
    public static int mySqrt(int x) {
        if (x < 2) {
            return x;
        }
        //走到这里，最小就是1了。2的平方根1.4取整是1
        int left = 1, right = x / 2, answer = 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if ((long) mid * mid <= x) {
                // mid 可能是答案，继续往右找更大的
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return answer;
    }

    /**
     * 解法三：牛顿迭代法
     * 求 f(t) = t² - x = 0 的正根，迭代公式 t = (t + x / t) / 2，
     * 当相邻两次迭代差值足够小时收敛，取整数部分。
     * 时间复杂度：O(log x)
     * 空间复杂度：O(1)
     */
    public static int mySqrtNewton(int x) {
        if (x == 0) {
            return 0;
        }
        double t = x;
        while (t * t > x) {
            t = (t + x / t) / 2;
        }
        return (int) t;
    }

}
