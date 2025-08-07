package com.hm.codes;

/**
 * Created by p_dmweidu on 2025/3/30
 * Desc:
 * LeetCode8.md
 */
public class LeetCode8 {

    public static void main(String[] args) {
        LeetCode8 solution = new LeetCode8();
        System.out.println(solution.myAtoi("42"));          // 输出: 42
        System.out.println(solution.myAtoi("   -42"));     // 输出: -42
        System.out.println(solution.myAtoi("4193 with"));  // 输出: 4193
        System.out.println(solution.myAtoi("words 987"));  // 输出: 0
        System.out.println(solution.myAtoi("91283472332")); // 输出: 2147483647
    }

    /**
     * 将字符串转换为整数 AISCII to Integer
     * @param s
     * @return
     */
    public int myAtoi(String s) {

        if (s == null || s.isEmpty()) {
            return 0;
        }

        int i = 0;          // 字符串索引
        int sign = 1;       // 正负号，1为正，-1为负
        long result = 0;    // 使用long避免溢出时丢失精度
        int n = s.length();

        // 1. 跳过前导空格
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 如果已经到字符串末尾，返回0
        if (i >= n) return 0;

        // 2. 处理正负号
        if (s.charAt(i) == '+' || s.charAt(i) == '-') {
            sign = (s.charAt(i) == '+') ? 1 : -1;
            i++;
        }

        // 3. 处理数字字符
        while (i < n && Character.isDigit(s.charAt(i))) {
            // 获取当前数字，注意要减去 '0'
            int digit = s.charAt(i) - '0';

            // 4. 检查溢出
            result = result * 10 + digit;
            if (result > Integer.MAX_VALUE) {
                return (sign == 1) ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            i++;
        }

        // 5. 应用正负号并返回
        result = sign * result;
        return (int) result;
    }
}