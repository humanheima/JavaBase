package com.hm.codes;

import java.util.HashMap;

/**
 * Created by p_dmweidu on 2025/3/31
 * Desc: 罗马数字转整数.md
 */
public class LeetCode13 {

    // 测试
    public static void main(String[] args) {
        LeetCode13 solution = new LeetCode13();
        System.out.println(solution.romanToInt("III"));     // 3
        System.out.println(solution.romanToInt("IV"));      // 4
        System.out.println(solution.romanToInt("IX"));      // 9
        System.out.println(solution.romanToInt("LVIII"));   // 58
        System.out.println(solution.romanToInt("MCMXCIV")); // 1994
    }

    public int romanToInt(String s) {
        // 定义罗马字符到整数的映射
        HashMap<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);
        
        int result = 0;
        int prev = 0; // 前一个字符的值
        
        // 从右到左遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = map.get(s.charAt(i));
            // 当前值 >= 前一个值，加法
            if (curr >= prev) {
                result += curr;
            } 
            // 当前值 < 前一个值，减法
            else {
                result -= curr;
            }
            prev = curr; // 更新前一个值
        }
        
        return result;
    }


}