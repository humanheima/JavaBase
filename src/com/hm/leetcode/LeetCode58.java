package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/3
 * Desc:最后一个单词的长度
 */
class LeetCode58 {


    public static void main(String[] args) {
        LeetCode58 solution = new LeetCode58();
        System.out.println(solution.lengthOfLastWord("Hello World"));
    }
    public int lengthOfLastWord(String s) {
        s= s.trim();
        if (s.isEmpty()) {
            return 0;
        }
        String[] arr = s.split("\\s");
        return arr[arr.length - 1].length();

    }
}