package com.hm.base.interview.android;

/**
 * 背包问题.md
 */
public class Knapsack {

    public static void main(String[] args) {
//        int[] w = {2, 1, 3}; // 物品重量
//        int[] v = {3, 2, 4}; // 物品价值
//        int W = 4; // 背包容量
//        System.out.println("最大价值: " + knapsack(w, v, W)); // 输出: 6

        test2();
    }

    public static void test2() {
        int[] weights = {2, 3, 4, 5};  // 物品重量
        int[] values = {3, 4, 5, 6};   // 物品价值
        int capacity = 10;             // 背包容量
        int maxValue = knapsack(weights, values, capacity);
        //int maxValue = knapsack2(weights, values, capacity);
        System.out.println("最大价值: " + maxValue);  // 输出 14
    }

    /**
     * @param weights  物品重量
     * @param values   物品价值
     * @param capacity 总容量
     * @return
     */
    public static int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // dp[i][j] 表示前 i 个物品在容量 j 下的最大价值
        //dp[0][0] = 0
        int[][] dp = new int[n + 1][capacity + 1];

        // 填充 dp 数组
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 不放第 i 个物品
                dp[i][j] = dp[i - 1][j];
                // 如果能放下第 i 个物品，比较放与不放的最大值
                //第 i个物品，下标是 i-1
                if (j >= weights[i - 1]) {
                    // j - weights[i - 1] 是 总重量减去 第 i 个物品的重量
                    // values[i - 1] ，是第 i 个物品的价值
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
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
    public static int knapsack2(int[] weights, int[] values, int capacity) {
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

}