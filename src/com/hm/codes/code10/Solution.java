package com.hm.codes.code10;

/**
 * Created by p_dmweidu on 2025/8/2
 * Desc:困难，先不看
 */
public class Solution {

    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "mississippi";
        String p = "mis*is*p*.";
        boolean result = solution.isMatch(s, p);
        System.out.println("Input: s = \"" + s + "\", p = \"" + p + "\"");
        System.out.println("Output: " + result);
    }

    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        // dp[i][j] 表示 s[0:i) 是否匹配 p[0:j)
        boolean[][] dp = new boolean[m + 1][n + 1];
        
        // 空字符串和空模式匹配
        dp[0][0] = true;
        
        // 初始化第一行：处理 p 以 * 开头的情况
        for (int j = 1; j <= n; j++) {
            if (p.charAt(j - 1) == '*') {
                dp[0][j] = dp[0][j - 2];
            }
        }
        
        // 动态规划填表
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (p.charAt(j - 1) == '*') {
                    // 匹配零个
                    dp[i][j] = dp[i][j - 2];
                    // 匹配一个或多个
                    if (p.charAt(j - 2) == s.charAt(i - 1) || p.charAt(j - 2) == '.') {
                        dp[i][j] |= dp[i - 1][j];
                    }
                } else {
                    // 普通字符或 . 匹配
                    if (p.charAt(j - 1) == s.charAt(i - 1) || p.charAt(j - 1) == '.') {
                        dp[i][j] = dp[i - 1][j - 1];
                    }
                }
            }
        }
        
        return dp[m][n];
    }
}