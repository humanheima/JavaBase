package com.hm.base.interview.android;

import java.util.Stack;

/**
 * 翻转字符串
 * 反转字符串
 */
public class ReverseString {


    public static void main(String[] args) {
        ReverseString reverseString = new ReverseString();
        reverseString.test();

    }

    private void test() {
        String[] tests = {"hello", "", "a", null, "世界"};
        for (String s : tests) {
            System.out.println("Input: " + s + " -> Output: " + reverseString2(s));
        }
    }

    /**
     * 使用内置函数（简单方式）
     *
     * @param str
     * @return
     */
    String reverseString1(String str) {
        return new StringBuilder(str).reverse().toString();
    }

    /**
     * 思路:
     * <p>
     * 将字符串转为字符数组
     * 使用两个指针（左和右），从两端向中间交换字符
     *
     * @param str
     * @return
     */
    String reverseString2(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        char[] chars = str.toCharArray();
        int left = 0;
        int right = chars.length - 1;

        while (left < right) {
            // 交换
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }

        return new String(chars);
    }


    /**
     * 3. 递归法
     * 思路:
     * <p>
     * 将问题分解为子问题
     * 每次取第一个字符放到最后
     *
     * @param str
     * @return
     */
    String reverseString3(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }
        // 递归：反转剩余部分 + 第一个字符
        return reverseString3(str.substring(1)) + str.charAt(0);
    }

    /**
     * 4. 栈的方式
     * 思路:
     * <p>
     * 将字符依次入栈
     * 再依次出栈组成新字符串
     *
     * @param str
     * @return
     */
    String reverseString4(String str) {
        if (str == null || str.length() <= 1) {
            return str;
        }

        Stack<Character> stack = new Stack<>();
        for (char c : str.toCharArray()) {
            stack.push(c);
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }


}
