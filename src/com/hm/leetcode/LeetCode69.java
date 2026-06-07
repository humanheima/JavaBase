package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/4
 * Desc: x的平方根，使用二分查找类似
 * https://leetcode.cn/problems/sqrtx/
 * <p>
 * 给你一个非负整数 x ，计算并返回 x 的 算术平方根 。
 * <p>
 * 由于返回类型是整数，结果只保留 整数部分 ，小数部分将被 舍去 。
 * <p>
 * 注意：不允许使用任何内置指数函数和算符，例如 pow(x, 0.5) 或者 x ** 0.5 。
 */
class LeetCode69 {


    /**
     * 用这个吧，可以精确找到平方根
     *
     * @param x
     * @return
     */
    public int mySqrtGrok(int x) {
        if (x == 0) return 0;

        // 定义查找范围
        int left = 1, right = x;
        int result = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 避免溢出
            // 使用 long 防止 mid * mid 溢出
            long square = (long) mid * mid;

            if (square == x) {
                return mid; // 找到精确平方根
            } else if (square < x) {
                result = mid; // 记录当前可能的答案
                left = mid + 1; // 尝试更大的值
            } else {
                right = mid - 1; // 平方太大，尝试更小的值
            }
        }

        return result;
    }

    /**
     * 这个没法精确找到平方根，要整个循环结束
     *
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        if (x == 0) return 0;
        if (x == 1) return 1;

        int left = 1;
        int right = x;
        int ans = 0;

        while (left <= right) {
            int mid = left + (right - left) / 2;// 避免溢出
            if (mid <= x / mid) {
                ans = mid; // 记录当前合法答案
                left = mid + 1; // 尝试更大的值
            } else {
                right = mid - 1; // mid 太大，缩小范围
            }
        }

        return ans;
    }

    /**
     * 暴力遍历
     *
     * @param x
     * @return
     */
    public int mySqrt1(int x) {
        if (x <= 0) {
            return 0;
        }

        for (int i = 1; i <= x; i++) {
            if (i * i == x) {
                return i;
            } else if (i * i > x) {
                return i - 1;
            }
        }
        return 0;
    }
}