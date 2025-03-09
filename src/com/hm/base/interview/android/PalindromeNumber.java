package com.hm.base.interview.android;

/**
 * 回文数，不把数子转化成字符串
 */
public class PalindromeNumber {

    public static void main(String[] args) {
        // 测试用例
        System.out.println("12321 是回文数吗？ " + isPalindrome(12321)); // true
//        System.out.println("121 是回文数吗？ " + isPalindrome(121));    // true
//        System.out.println("12345 是回文数吗？ " + isPalindrome(12345)); // false
//        System.out.println("-121 是回文数吗？ " + isPalindrome(-121));   // false
//        System.out.println("10 是回文数吗？ " + isPalindrome(10));      // false
    }


    public static boolean isPalindrome(int x) {
        // 特殊情况处理
        if (x < 0) {
            return false; // 负数不是回文数
        }
        if (x == 0) {
            return true; // 0 是回文数
        }
        if (x % 10 == 0) {
            return false; // 末尾为 0（非 0 本身）不是回文数
        }

        // 反转后半部分
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10; // 提取最后一位并加入 reversed
            x = x / 10;                       // 去掉最后一位
        }

        // 比较前半部分和后半部分
        // x == reversed（偶数位） 或 x == reversed / 10（奇数位）
        return x == reversed || x == reversed / 10;
    }

}