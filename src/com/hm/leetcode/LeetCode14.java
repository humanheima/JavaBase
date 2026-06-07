package com.hm.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by p_dmweidu on 2025/4/2
 * Desc: 最长公共前缀
 */
class LeetCode14 {

    public static void main(String[] args) {

        LeetCode14 solution = new LeetCode14();
        String[] strs = {"flower", "flow", "flight"};
        String[] strs2 = {"dog", "racecar", "car"};
        //String[] strs = {"ab", "a"};
        System.out.println(solution.longestCommonPrefix(strs));

    }

    public String longestCommonPrefix(String[] strs) {
        if (strs.length == 0) {
            return "";
        }
        if (strs.length == 1) {
            return strs[0];
        }

        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        int minLen = strs[0].length();

        if (minLen == 0) {
            return "";
        }

        int i = 0;
        for (; i < minLen; i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }


        return strs[0].substring(0, i);
    }


    /**
     * 横向比较法，使用这种方法。比较字符串
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefixGrok(String[] strs) {
        // 边界条件：空数组返回空字符串
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 只有一个字符串，返回该字符串
        if (strs.length == 1) {
            return strs[0];
        }

        // 以第一个字符串为初始前缀
        String prefix = strs[0];

        // 遍历后续字符串，逐步更新前缀
        for (int i = 1; i < strs.length; i++) {
            // 当前字符串为空，直接返回空
            if (strs[i].isEmpty()) {
                return "";
            }
            // 比较前缀与当前字符串，更新前缀
            prefix = getCommonPrefix(prefix, strs[i]);
            // 如果前缀已为空，直接返回
            if (prefix.isEmpty()) {
                return "";
            }
        }

        return prefix;
    }

    // 辅助方法：找出两个字符串的公共前缀
    private String getCommonPrefix(String s1, String s2) {
        // 取较短字符串的长度作为比较上限
        int len = Math.min(s1.length(), s2.length());
        int i = 0;
        // 逐字符比较，找到公共部分
        while (i < len && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }
        // 返回公共前缀
        return s1.substring(0, i);
    }


    /**
     * 纵向比较法
     * 逐字符比较所有字符串的同一位置，适合短字符串数组。
     *
     * @param strs
     * @return
     */
    public String longestCommonPrefixVertical(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        for (int i = 0; i < strs[0].length(); i++) {
            char c = strs[0].charAt(i);
            for (int j = 1; j < strs.length; j++) {
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        return strs[0];
    }

    private void print(String[] strs) {
        for (String str : strs) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

}