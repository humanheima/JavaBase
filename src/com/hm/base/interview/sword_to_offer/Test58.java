package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2022/4/13.
 * <p>
 * Desc:
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/fan-zhuan-dan-ci-shun-xu-lcof/solution/fan-zhuan-dan-ci-shun-xu-by-leetcode-sol-vnwj/
 * 来源：力扣（LeetCode）
 * <p>
 * 刷5道中等难度的，5道简单的。
 */
public class Test58 {

    public static void main(String[] args) {
        Test58 test58 = new Test58();
        System.out.println(test58.reverseWords("i want eat an apple"));

    }

    public String reverseWords(String s) {
        StringBuilder sb = trimSpaces(s);

        // 翻转整个字符串
        reverse(sb, 0, sb.length() - 1);

        // 翻转每个单词
        reverseEachWord(sb);

        return sb.toString();
    }

    public StringBuilder trimSpaces(String s) {
        int left = 0;
        int right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            left++;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            right--;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);

            if (c != ' ') {
                sb.append(c);
            } else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }

            left++;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left, sb.charAt(right));
            left++;
            sb.setCharAt(right, tmp);
            right--;
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;

        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                end++;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            end = end + 1;
        }
    }


}
