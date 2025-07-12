package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/3/31
 * Desc: 整数转罗马数字.md
 */
public class LeetCode12 {
    // 定义罗马数字的值和符号对应数组

    // 测试
    public static void main(String[] args) {
        LeetCode12 solution = new LeetCode12();
        //System.out.println(solution.intToRoman(3));    // III
        //System.out.println(solution.intToRoman(4));    // IV
        //System.out.println(solution.intToRoman(9));    // IX
        //System.out.println(solution.intToRoman(58));   // LVIII
        System.out.println(solution.intToRoman(1994)); // MCMXCIV
    }

    private static final int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();

        // 从大到小遍历
        for (int i = 0; i < values.length && num > 0; i++) {
            // 当前值能匹配多少次
            int value = values[i];
            while (num >= value) {
                roman.append(symbols[i]);
                num -= value;
            }
        }

        return roman.toString();
    }


}