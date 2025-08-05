package com.hm.base.interview.android.knapsack;

/**
 * Created by p_dmweidu on 2025/7/8
 * Desc: 背包问题.md
 */
public class Knapsack {

    public static void main(String[] args) {
//        int[] w = {2, 1, 3}; // 物品重量
//        int[] v = {3, 2, 4}; // 物品价值
//        int W = 4; // 背包容量
//        System.out.println("最大价值: " + knapsack(w, v, W)); // 输出: 6

        //test2();
        test1();
    }

    public static void test1() {
        int[] values = {60, 100, 120};  // 物品价值
        int[] weights = {10, 20, 30};   // 物品重量
        int capacity = 50;              // 背包容量
        int n = values.length;          // 物品数量

        // 测试二维DP
        int maxValue = knapsack2D(weights, values, capacity, n);
        System.out.println("最大价值（二维DP）: " + maxValue);

        System.out.println("最大价值（一维DP）: " + knapsack1D2(weights, values, capacity));

    }

    /**
     * 二维DP实现
     *
     * @param weights  物品重量
     * @param values   物品价值
     * @param capacity 背包容量
     * @param n        物品数量
     * @return
     */
    public static int knapsack2D(int[] weights, int[] values, int capacity, int n) {
        // dp[i][j]表示前i个物品、容量j的最大价值
        int[][] dp = new int[n + 1][capacity + 1];

        // 填充DP表
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 不能包含物品i-1
                dp[i][j] = dp[i - 1][j];
                // 放第 i 个物品（如果容量足够）
                if (j >= weights[i - 1]) {
                    // 选择：包含或不包含物品i-1
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }
        return dp[n][capacity];
    }

    /**
     * 一维数组优化版本，后面再研究
     *
     * @param weights
     * @param values
     * @param capacity
     * @return
     */
    public static int knapsack1D(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // dp[j] 表示容量 j 下的最大价值
        int[] dp = new int[capacity + 1];

        // 遍历每个物品
        for (int i = 0; i < n; i++) {
            // 从后向前遍历，避免覆盖未更新的值
            //第i个物品重量
            int weight = weights[i];
            for (int j = capacity; j >= weight; j--) {
                //第i个物品价值
                int value = values[i];
                //剩余重量
                int leftWeight = j - weight;
                //容量为j的总价值
                dp[j] = Math.max(dp[j], dp[leftWeight] + value);
            }
        }
        return dp[capacity];
    }

    // 一维动态规划实现（空间优化）
    public static int knapsack1D2(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // dp[j] 表示容量 j 下的最大价值
        int[] dp = new int[capacity + 1];

        // 动态规划，从右到左更新
        for (int i = 0; i < n; i++) {
            for (int j = capacity; j >= weights[i]; j--) {
                dp[j] = Math.max(dp[j], dp[j - weights[i]] + values[i]);
            }
        }

        return dp[capacity];
    }

}