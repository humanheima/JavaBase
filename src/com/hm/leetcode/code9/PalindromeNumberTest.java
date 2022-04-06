package com.hm.leetcode.code9;

/**
 * Crete by dumingwei on 2020-02-12
 * Desc: LeetCode 9. 回文数
 * <p>
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * <p>
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 例如，121 是回文，而 123 不是。
 * <p>
 * 示例 1：
 * <p>
 * 输入：x = 121
 * 输出：true
 * <p>
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3：
 * <p>
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 */
public class PalindromeNumberTest {

    public static void main(String[] args) {

        System.out.println(isPalindrome(1));
        System.out.println(isPalindrome(12));
        System.out.println(isPalindrome(11));
        System.out.println(isPalindrome(12321));
        System.out.println(isPalindrome(123321));
        System.out.println(isPalindrome(123421));
    }


    /**
     * 一个回文数，把数字转化成字符串的话，从中间分开，左边从现向后遍历，
     * 右边从后向前遍历？
     * <p>
     * <p>
     * 12321  2 12
     * 123321 3 123 543
     *
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        if (x == 0) {
            return true;
        }
        String numberStr = String.valueOf(x);
        int length = numberStr.length();
        if (length == 1) {
            return true;
        }
        boolean result = true;

        int middle = length / 2;

        for (int i = 0; i < middle; i++) {
            char left = numberStr.charAt(i);
            char right = numberStr.charAt(length - 1 - i);
            if (left != right) {
                result = false;
                break;
            }
        }
        return result;
    }

}
