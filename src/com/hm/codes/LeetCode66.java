package com.hm.codes;

/**
 * Created by p_dmweidu on 2025/4/3
 * Desc: 加1 https://leetcode.cn/problems/plus-one/description/
 */
class LeetCode66 {


    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1; // 初始进位为1，相当于加一

        for (int i = n - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            if (carry == 0) {
                break; // 无进位则提前终止循环
            }
        }

        if (carry == 1) {
            // 需要扩展数组，例如999 -> 1000
            int[] newDigits = new int[n + 1];
            newDigits[0] = 1;
            return newDigits;
        } else {
            return digits;
        }
    }

}