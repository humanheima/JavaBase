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
     * 使用这种方法
     * @param strs
     * @return
     */
    public String longestCommonPrefix2(String[] strs) {
        // 边界情况：空数组返回空字符串
        if (strs == null || strs.length == 0) {
            return "";
        }
        // 只有一个字符串时，直接返回该字符串
        if (strs.length == 1) {
            return strs[0];
        }

        // 以第一个字符串的长度为基准
        int length = strs[0].length();
        int count = strs.length;

        // 遍历每个字符位置
        for (int i = 0; i < length; i++) {
            char c = strs[0].charAt(i); // 取第一个字符串的当前字符
            // 比较其他字符串的相同位置
            for (int j = 1; j < count; j++) {
                // 如果当前字符串已到末尾或字符不匹配，返回前缀
                if (i == strs[j].length() || strs[j].charAt(i) != c) {
                    return strs[0].substring(0, i);
                }
            }
        }
        // 如果全部匹配，返回第一个字符串
        return strs[0];
    }


    private void print(String[] strs) {
        for (String str : strs) {
            System.out.print(str + " ");
        }
        System.out.println();
    }

}