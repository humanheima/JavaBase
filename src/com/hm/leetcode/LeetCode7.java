package com.hm.leetcode;
/**
 * Created by p_dmweidu on 2025/3/30
 * Desc: 整数翻转/反转
 */
class LeetCode7 {


    public static void main(String[] args) {
// 测试用例
        int[] testCases = {123, -123, 120, 0, 1534236469, Integer.MAX_VALUE, Integer.MIN_VALUE, 5};
        int[] expected = {321, -321, 21, 0, 0, 0, 0, 5};

        LeetCode7 solution = new LeetCode7();
        for (int i = 0; i < testCases.length; i++) {
            int result = solution.reverse(testCases[i]);
            System.out.printf("Input: %d, Output: %d, Expected: %d, %s%n",
                    testCases[i], result, expected[i],
                    result == expected[i] ? "Pass" : "Fail");
        }

    }
    public int reverse(int x) {
        // 记录符号
        int sign = (x >= 0) ? 1 : -1;
        // 转换为正数处理
        long num = Math.abs((long)x);  // 用 long 避免溢出
        long reversed = 0;
        
        // 逐位反转
        while (num > 0) {
            reversed = reversed * 10 + num % 10;
            num /= 10;
            
            // 检查溢出
            if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
                return 0;
            }
        }
        
        // 返回结果，带上原始符号
        return sign * (int)reversed;
    }
}