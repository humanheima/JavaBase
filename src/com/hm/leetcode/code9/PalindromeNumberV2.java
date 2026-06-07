package com.hm.leetcode.code9;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 9. 回文数 —— 第三种解法（完整反转整个数字）
 * <p>
 * 给你一个整数 x，如果 x 是回文整数返回 true，否则返回 false。
 * <p>
 * 解题思路（完整反转法）：
 * 1. 负数一定不是回文数（因为有负号），直接返回 false。
 * 2. 把整个数字反转得到 reversed，例如 123 -> 321、121 -> 121。
 * 3. 反转过程中可能超出 int 范围，所以用 long 存放 reversed，避免溢出。
 * 4. 反转后与原数比较：相等即回文。
 * <p>
 * 时间复杂度 O(log10(x))（处理位数），空间复杂度 O(1)。
 */
public class PalindromeNumberV2 {

    public static void main(String[] args) {
        // 用例：数字 -> 期望结果
        test(1, true);
        test(11, true);
        test(12, false);
        test(121, true);
        test(1221, true);
        test(12321, true);
        test(123321, true);
        test(123421, false);
        test(0, true);
        test(10, false);
        test(-121, false);
        test(Integer.MAX_VALUE, false); // 2147483647，反转会溢出，long 可正确处理
    }

    private static void test(int number, boolean expected) {
        boolean actual = isPalindrome(number);
        System.out.printf("isPalindrome(%d) = %b  期望=%b  %s%n",
                number, actual, expected, actual == expected ? "✓" : "✗ FAIL");
    }

    /**
     * 完整反转整个数字后与原值比较。
     * 使用 long 保存反转结果，规避 int 反转溢出问题。
     */
    public static boolean isPalindrome(int x) {
        // 负数带负号，一定不是回文
        if (x < 0) {
            return false;
        }

        long reversed = 0;
        int origin = x;
        while (x != 0) {
            reversed = reversed * 10 + x % 10; // 取末位拼到 reversed
            x /= 10;                           // 去掉末位
        }

        return reversed == origin;
    }
}
