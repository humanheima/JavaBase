package com.hm.leetcode;

class LeetCode7 {
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