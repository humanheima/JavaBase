package com.hm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Crete by dumingwei on 2026-06-06
 * Desc:
 * <p>
 * 问题描述
 * 给定一个字符串 s，请你找出其中不含有重复字符的最长子串的长度。
 * <p>
 * 示例 1：
 * <p>
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2：
 * <p>
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3：
 * <p>
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 * 请注意，你的答案必须是子串的长度，"pwke" 是一个子序列，不是子串。
 * <p>
 * 解题思路：滑动窗口
 * <p>
 * 用 left、right 两个指针维护一个窗口 [left, right]，窗口内保证没有重复字符。
 * right 不断向右扩张，每遇到一个字符就用 HashMap 记录它最近一次出现的下标。
 * 当 right 指向的字符在窗口内已经出现过时，把 left 跳到该重复字符上次出现位置的下一格，
 * 从而把重复字符移出窗口。每一步都用 right - left + 1 更新最大长度。
 * <p>
 * 详细推导见同级目录 LeetCode3.md
 * https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 */
public class LeetCode3 {

    // 测试
    public static void main(String[] args) {
        LeetCode3 solution = new LeetCode3();
        //System.out.println(solution.lengthOfLongestSubstring("abcabcbb")); // 3
        //System.out.println(solution.lengthOfLongestSubstring("bbbbb"));    // 1
        //System.out.println(solution.lengthOfLongestSubstring("pwwkew"));   // 3
        //System.out.println(solution.lengthOfLongestSubstring(""));         // 0
        System.out.println(solution.lengthOfLongestSubstring("abba"));     // 2
    }

    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        // 记录每个字符最近一次出现的下标
        Map<Character, Integer> lastIndex = new HashMap<>();

        int maxLength = 0;
        // 窗口左边界
        int left = 0;

        // right 是窗口右边界，逐个字符向右扩张
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);

            // 如果当前字符在窗口内已经出现过，把 left 收缩到重复字符的下一位
            // 注意要取 max，避免 left 因为窗口外的旧记录而往回退（例如 "abba"）
            if (lastIndex.containsKey(c)) {
                left = Math.max(left, lastIndex.get(c) + 1);
            }

            // 更新当前字符的最新下标
            lastIndex.put(c, right);

            // 当前窗口 [left, right] 内无重复，更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }

}
