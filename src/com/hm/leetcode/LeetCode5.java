package com.hm.leetcode;

/**
 * Crete by dumingwei on 2020-03-04
 * Desc:
 * <p>
 * 问题描述
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 * <p>
 * 示例 1：
 * <p>
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 * <p>
 * 输入: "cbbd"
 * 输出: "bb"
 * <p>
 * 解题思路：
 * <p>
 * 定一个maxLength 记录最长的回文子串
 * <p>
 * 很直观的一个想法就是，这种想法有点复杂了。
 * 1. 我先从第一个字符开始，判断[0,1],[0,2],[0,3]...[0,n-1],找出这中间所有字符串中最长的回文字符串。
 * 2. 然后从第2个字符开始，判断[1,2],[1,3],[1,4]...[1,n-1],找出这中间所有字符串中最长的回文字符串。
 * 2. 然后从第3个字符开始，判断[2,3],[2,4],[2,5]...[2,n-1],找出这中间所有字符串中最长的回文字符串。
 * ...
 * 3. 重复上面的步骤，直到倒数第2个字符。[n-2,n-1]。
 */
public class LeetCode5 {

    // 测试
    public static void main(String[] args) {
        LeetCode5 solution = new LeetCode5();
        String s1 = "babad";
        String s2 = "cbbd";
        //System.out.println(solution.longestPalindrome(s1));  // 输出 "bab" 或 "aba"
        System.out.println(solution.longestPalindrome(s2));  // 输出 "bb"
    }


    public String longestPalindrome(String s) {
        if (s == null || s.isEmpty()) return "";

        int start = 0;  // 记录最长回文串的起始位置
        int maxLength = 0;  // 记录最长回文串的长度

        // 遍历每个字符作为中心
        for (int i = 0; i < s.length(); i++) {
            // 检查奇数长度回文
            int len1 = expandAroundCenter(s, i, i);
            // 检查偶数长度回文
            int len2 = expandAroundCenter(s, i, i + 1);
            // 取两种情况的最大值
            int len = Math.max(len1, len2);

            // 更新最长回文串的信息
            if (len > maxLength) {
                maxLength = len;
                /**
                 * 起始位置计算：i - (len - 1) / 2
                 * 设回文长度为 len，中心为 i。
                 * 左侧扩展了 (len-1)/2 步，右侧也扩展了 (len-1)/2 步。
                 * 起始位置 = 中心位置 - 左侧步数。
                 */
                start = i - (len - 1) / 2;  // 计算起始位置
            }
        }


        return s.substring(start, start + maxLength);
    }

    // 中心扩展函数，返回以left和right为中心的最长回文长度
    private int expandAroundCenter(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }
        return right - left - 1;  // 返回回文长度
    }

}
