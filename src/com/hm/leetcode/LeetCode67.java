package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/3
 * Desc: 二进制求和
 * 给你两个二进制字符串 a 和 b ，以二进制字符串的形式返回它们的和。
 */
class LeetCode67 {


    public static void main(String[] args) {
        LeetCode67 solution = new LeetCode67();
        System.out.println(solution.addBinary("1010", "1011"));
        System.out.println(solution.addBinary("11", "1"));
    }
    public String addBinary(String a, String b) {

        int aIndex = a.length()-1;
        int bIndex = b.length()-1;

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