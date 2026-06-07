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

        test1(1);

        test1(11);
        test1(12);

        test1(121);
        test1(1221);
        test1(12321);
        test1(123321);
        test1(123421);

    }

    private static void test1(int number) {
        System.out.println(isPalindrome(number));
        System.out.println(isPalindrome2(number));
        System.out.println();
    }

    public static boolean isPalindrome(int x) {
        // 负数不是回文数
        if (x < 0) {
            return false;
        }

        // 将整数转换为字符串
        String s = String.valueOf(x);

        // 双指针检查回文
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }

        return true;
    }

    /**
     * 不把数字转化成字符串的写法
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
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
