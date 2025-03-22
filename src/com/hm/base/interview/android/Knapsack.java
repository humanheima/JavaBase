package com.hm.base.interview.android;

/**
 * 背包问题.md
 */
public class Knapsack {

    public static void main(String[] args) {
        int[] w = {2, 1, 3}; // 物品重量
        int[] v = {3, 2, 4}; // 物品价值
        int W = 4; // 背包容量
        System.out.println("最大价值: " + knapsack(w, v, W)); // 输出: 6
    }

    /**
     * 3.1 二维DP实现
     * @param w
     * @param v
     * @param W
     * @return
     */
    public static int knapsack(int[] w, int[] v, int W) {
        int N = w.length; // 物品数量
        //
        int[][] dp = new int[N + 1][W + 1]; // DP表格,多一行一列便于初始化，这里默认初始化 dp[0][0] = 0，没有物品，没有容量

        // 填充DP表格
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j <= W; j++) {
                if (j < w[i - 1]) { // 容量不够，放不进第i个物品
                    dp[i][j] = dp[i - 1][j];
                    //dp[1][0] = dp[0][0];
                    //dp[1][1] = dp[0][1];
                    //dp[1][2] = dp[0][2];
                    //dp[1][3] = dp[0][3];

                } else { // 可以选择放或不放

                    /**
                     * 如果不放第 i 个物品：dp[i][j] = dp[i-1][j]。
                     * 如果放第 i 个物品：dp[i][j] = dp[i-1][j-w[i]] + v[i]（前提是 j >= w[i]）。
                     * 取两者中的最大值：dp[i][j] = max(dp[i-1][j], dp[i-1][j-w[i]] + v[i])。
                     */
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
                }
            }
        }
        return dp[N][W]; // 返回最大价值
    }

}