package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/2
 * Desc: 找出字符串中第一个匹配项的下标
 */
class LeetCode28 {

    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }

        if (haystack.length() < needle.length()) {
            return -1;
        }

        for (int i = 0; i < haystack.length() - needle.length() + 1; i++) {
            boolean match = true;
            for (int j = 0; j < needle.length(); j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i; // 匹配成功，返回起始位置
            }
        }

        return -1;
    }
}