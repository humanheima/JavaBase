package com.hm.leetcode.code1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by p_dmweidu on 2025/3/28
 * Desc: 最长无重复子串.md
 */
public class LeetCode3 {

    public static void main(String[] args) {

        LeetCode3 solution = new LeetCode3();


        String s1 = "abcabcbb";
        String s2 = "bbbbb";
        String s3 = "pwwwkew";

        //System.out.println(solution.lengthOfLongestSubstring(s1)); // 输出: 3
        //System.out.println(solution.lengthOfLongestSubstring(s2)); // 输出: 1
        System.out.println(solution.lengthOfLongestSubstring2(s3)); // 输出: 3

    }

    /**
     * 自己的写法
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        if (s.length() == 1) {
            return 1;
        }
        char[] array = s.toCharArray();
        int maxLength = 0;
        for (int i = 0; i < array.length; i++) {
            //s = "abcabcbb"
            List<Character> tempList = new ArrayList<>();
            tempList.add(array[i]);
            for (int j = i + 1; j < array.length; j++) {
                if (!tempList.contains(array[j])) {
                    tempList.add(array[j]);
                } else {
                    break;
                }
            }
            maxLength = Math.max(maxLength, tempList.size());
        }

        return maxLength;


    }


    /**
     * 滑动窗口方法 grok 写法
     *
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        // 哈希表记录字符及其最新出现位置
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLength = 0;  // 最长无重复子串长度
        int left = 0;       // 窗口左边界

        // 遍历字符串，right 是窗口右边界
        for (int right = 0; right < s.length(); right++) {
            char currentChar = s.charAt(right);

            // 如果当前字符已在窗口内出现，更新左边界
            if (map.containsKey(currentChar)) {
                // 左边界跳到重复字符的上次出现位置的下一个位置
                left = Math.max(left, map.get(currentChar) + 1);
            }

            // 更新当前字符的位置
            map.put(currentChar, right);
            // 更新最大长度
            maxLength = Math.max(maxLength, right - left + 1);
        }

        return maxLength;
    }
}
