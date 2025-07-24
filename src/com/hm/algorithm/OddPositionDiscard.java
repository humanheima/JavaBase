package com.hm.algorithm;

/**
 * Created by p_dmweidu on 2025/7/24
 * Desc: 奇数位丢弃
 */
public class OddPositionDiscard {

    public static String discardOddPositions(String s) {
        if (s == null || s.isEmpty()) {
            return "";
        }
        
        // 使用StringBuilder高效拼接字符
        StringBuilder result = new StringBuilder();
        
        // 1-based索引的偶数位对应0-based索引的1, 3, 5...
        for (int i = 1; i < s.length(); i += 2) {
            result.append(s.charAt(i));
        }
        
        return result.toString();
    }

    public static void main(String[] args) {
        // 测试用例
        String[] tests = {"abcdef", "hello", "", "a", "123456"};
        for (String test : tests) {
            System.out.println("输入: " + test + " -> 输出: " + discardOddPositions(test));
        }
    }
}