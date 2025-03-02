package com.hm.base.interview.android;

import java.util.Stack;

/**
 * 翻转单词顺序，结论 使用 算法1或者2来实现
 * 1. 使用分割和反转
 * 2. 使用栈来实现
 * 3. 双指针算法，参考 {@link com.hm.base.interview.sword_to_offer.Test58}
 */
public class WordOrderReversal {

    /**
     * 使用这种算法来写
     *
     * @param str
     * @return
     */
    // 方法1: 使用分割和反转
    public String reverseWordsWithSplit(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }

        // 分割成单词数组，去掉多余空格，这里就是每个单词
        String[] words = str.trim().split("\\s+");
        StringBuilder result = new StringBuilder();

        // 从后向前拼接
        for (int i = words.length - 1; i >= 0; i--) {

            result.append(words[i]);
            if (i > 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // 方法2: 双指针法（原地翻转）
    public String reverseWordsWithTwoPointers(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }

        // 先转为字符数组
        char[] chars = str.toCharArray();

        // 第一步：整个字符串反转
        reverse(chars, 0, chars.length - 1);

        // 第二步：逐个单词反转
        int start = 0;
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == ' ') {
                reverse(chars, start, i - 1);
                start = i + 1;
            }
        }
        // 处理最后一个单词
        reverse(chars, start, chars.length - 1);

        // 处理多余空格并转为字符串
        return cleanSpaces(chars);
    }

    // 辅助方法：反转字符数组的指定区间
    private void reverse(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    // 辅助方法：清理多余空格
    private String cleanSpaces(char[] chars) {
        StringBuilder result = new StringBuilder();
        int i = 0;
        while (i < chars.length) {
            // 跳过开头的空格
            while (i < chars.length && chars[i] == ' ') {
                i++;
            }
            // 添加单词
            while (i < chars.length && chars[i] != ' ') {
                result.append(chars[i++]);
            }
            // 添加单个空格
            if (i < chars.length) {
                result.append(' ');
            }
        }
        // 去除末尾空格
        if (result.length() > 0 && result.charAt(result.length() - 1) == ' ') {
            result.setLength(result.length() - 1);
        }
        return result.toString();
    }

    /**
     * 或者使用这种算法来写
     *
     * @param str
     * @return
     */
    // 方法3: 使用栈
    public String reverseWordsWithStack(String str) {
        if (str == null || str.trim().isEmpty()) {
            return str;
        }

        Stack<String> stack = new Stack<>();
        String[] words = str.trim().split("\\s+");

        for (String word : words) {
            stack.push(word);
        }

        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.append(stack.pop());
            if (!stack.isEmpty()) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    // 测试所有方法
    public void testAllMethods() {
        String[] testCases = {
                "  hello world  ",
                "  the  sky  is  blue  ",
                "a",
                "",
                null,
                "  "
        };

        System.out.println("Testing all word reversal methods:");
        for (String test : testCases) {
            System.out.println("\nInput: \"" + test + "\"");
            System.out.println("Split: \"" + reverseWordsWithSplit(test) + "\"");
            System.out.println("Two Pointers: \"" + reverseWordsWithTwoPointers(test) + "\"");
            System.out.println("Stack: \"" + reverseWordsWithStack(test) + "\"");
        }
    }

    public static void main(String[] args) {
        WordOrderReversal reversal = new WordOrderReversal();
        reversal.testAllMethods();
    }
}