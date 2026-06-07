package com.hm.leetcode.code8;

/**
 * Crete by dumingwei on 2026-06-07
 * Desc: 字符串转换整数 (atoi)
 * <p>
 * 问题描述
 * 请你来实现一个 myAtoi(String s) 函数，使其能将字符串转换成一个 32 位有符号整数。
 * <p>
 * 函数的算法如下：
 * 1. 读入字符串并丢弃无用的前导空格；
 * 2. 检查下一个字符（假设还未到字符末尾）为正号还是负号，读取该字符（如果有）。
 * 确定最终结果是负数还是正数。如果两者都不存在，则假定结果为正；
 * 3. 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略；
 * 4. 将前面步骤读入的这些数字转换为整数（即 "123" -> 123，"0032" -> 32）。
 * 如果没有读入数字，则整数为 0。必要时更改符号（从步骤 2 开始）；
 * 5. 如果整数数超过 32 位有符号整数范围 [-2^31, 2^31 - 1]，需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 -2^31 的整数应该被固定为 -2^31，大于 2^31 - 1 的整数应该被固定为 2^31 - 1。
 * 6. 返回整数作为最终结果。
 * <p>
 * 注意：
 * - 本题中的空白字符只包括空格字符 ' '；
 * - 除前导空格或数字后的其余字符串外，请勿忽略任何其他字符。
 * <p>
 * 示例 1：
 * 输入：s = "42"
 * 输出：42
 * <p>
 * 示例 2：
 * 输入：s = "   -42"
 * 输出：-42
 * 解释：第 1 步："   -42"（读入前导空格，但忽视掉），第 2 步："   -42"（读入 '-' 字符，结果应该是负数）。
 * <p>
 * 示例 3：
 * 输入：s = "4193 with words"
 * 输出：4193
 * 解释：转换截止于数字 '3'，因为它的下一个字符不为数字。
 * <p>
 * 示例 4：
 * 输入：s = "words and 987"
 * 输出：0
 * 解释：第一个非空字符是 'w'，但它不是数字或正负号。因此无法执行有效转换。
 * <p>
 * 示例 5：
 * 输入：s = "-91283472332"
 * 输出：-2147483648
 * 解释：数字 "-91283472332" 超过 32 位有符号整数范围，故被截断为 -2^31 = -2147483648。
 * <p>
 * 解题思路：
 * <p>
 * 按题目描述的 4 个阶段顺序扫描字符串，用一个下标 i 从左到右推进：
 * 1. 跳过前导空格；
 * 2. 读取可选的正负号，记录 sign；
 * 3. 逐个读取数字字符，边读边累加 result = result * 10 + digit；
 * 4. 累加过程中提前判断溢出，越界则按符号返回 MAX_VALUE 或 MIN_VALUE。
 * <p>
 * 溢出判断在 int 范围内事前完成（与整数反转同理）：
 * 在 result = result * 10 + digit 之前，若 result > MAX/10，
 * 或 result == MAX/10 且 digit > 7，则正向越界，按符号截断。
 * <p>
 * 时间复杂度 O(n)，空间复杂度 O(1)。
 * <p>
 * https://leetcode.cn/problems/string-to-integer-atoi/
 */
public class LeetCode8 {

    // 测试
    public static void main(String[] args) {
        LeetCode8 solution = new LeetCode8();
        String[] testCases = {
                "42", "   -42", "4193 with words", "words and 987",
                "-91283472332", "+1", "  +0 123", "2147483648",
                "-2147483649", "   ", "+-12", "00000-42a1234"
        };
        int[] expected = {
                42, -42, 4193, 0,
                -2147483648, 1, 0, 2147483647,
                -2147483648, 0, 0, 0
        };

        for (int i = 0; i < testCases.length; i++) {
            int result = solution.myAtoi(testCases[i]);
            System.out.printf("Input: %-18s Output: %-12d Expected: %-12d %s%n",
                    "\"" + testCases[i] + "\"", result, expected[i],
                    result == expected[i] ? "Pass" : "Fail");
        }
    }

    public int myAtoi(String s) {
        int n = s.length();
        int i = 0;

        // 第 1 步：跳过前导空格
        while (i < n && s.charAt(i) == ' ') {
            i++;
        }

        // 第 2 步：读取可选的正负号
        int sign = 1;
        if (i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')) {
            sign = (s.charAt(i) == '-') ? -1 : 1;
            i++;
        }

        // 第 3、4 步：逐位读取数字并累加，同时判断溢出
        int result = 0;
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0';

            // 推入前判断溢出：result * 10 + digit 是否超出 int 范围
            // 正向：超出 MAX 返回 MAX_VALUE；负向：超出 MIN 返回 MIN_VALUE
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
            }

            result = result * 10 + digit;
            i++;
        }

        return sign * result;
    }

}
