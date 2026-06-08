package com.hm.leetcode.code67;

/**
 * Create by dumingwei on 2026-06-09
 * Desc: LeetCode 67. 二进制求和
 * <p>
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 * 示例 1：
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * <p>
 * 示例 2：
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 * <p>
 * 提示：
 * 1 <= a.length, b.length <= 10^4
 * a 和 b 仅由字符 '0' 或 '1' 组成
 * 字符串如果不是 "0" ，就不含前导零
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/add-binary/
 */
class LeetCode67 {

    public static void main(String[] args) {
        LeetCode67 solution = new LeetCode67();
        System.out.println(solution.addBinary("1010", "1011"));
        System.out.println(solution.addBinary("11", "1"));
    }

    public String addBinary(String a, String b) {

        int aIndex = a.length() - 1;
        int bIndex = b.length() - 1;

        int carry = 0;

        StringBuilder sb = new StringBuilder();

        while (aIndex >= 0 || bIndex >= 0) {
            int num1 = aIndex >= 0 ? a.charAt(aIndex) - '0' : 0;
            int num2 = bIndex >= 0 ? b.charAt(bIndex) - '0' : 0;
            int sum = num1 + num2 + carry;
            carry = sum / 2;
            sb.append(sum % 2);
            aIndex--;
            bIndex--;
        }

        if (carry == 1) {
            sb.append(1);
        }

        return sb.reverse().toString();
    }
}
