package com.hm.leetcode;

/**
 * Crete by dumingwei on 2020-02-12
 * Desc: 回文数
 */
public class MainTest {

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
     * @param var
     * @return
     */
    public static boolean isPalindrome(int var) {
        String number = String.valueOf(var);
        int length = number.length();
        if (length == 1) {
            return true;
        }
        boolean result = true;

        int middle = length / 2;

        for (int i = 0; i < middle; i++) {
            char left = number.charAt(i);
            char right = number.charAt(length - 1 - i);
            if (left != right) {
                result = false;
                break;
            }

        }
        return result;

    }

}
