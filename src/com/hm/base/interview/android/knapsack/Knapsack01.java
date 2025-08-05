package com.hm.base.interview.android.knapsack;

/**
 * Created by p_dmweidu on 2025/8/5
 * Desc: 二维数组
 */
public class Knapsack01 {

    public int knapsack2D(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        // dp[i][j] 表示前 i 个物品在容量 j 下的最大价值
        int[][] dp = new int[n + 1][capacity + 1];

        // 动态规划
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= capacity; j++) {
                // 不放第 i 个物品
                dp[i][j] = dp[i - 1][j];
                // 放第 i 个物品（如果容量足够）
                if (j >= weights[i - 1]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - weights[i - 1]] + values[i - 1]);
                }
            }
        }

        // 打印 dp 数组
        System.out.println("DP Array (max value for each state):");
        System.out.print("    ");
        for (int j = 0; j <= capacity; j++) {
            System.out.printf("%4d", j);
        }
        System.out.println();
        for (int i = 0; i <= n; i++) {
            System.out.printf("i=%-2d", i);
            for (int j = 0; j <= capacity; j++) {
                System.out.printf("%4d", dp[i][j]);
            }
            System.out.println();
        }

        return dp[n][capacity];
    }

    // 测试代码
    public static void main(String[] args) {
        Knapsack01 solution = new Knapsack01();

        // 测试用例
        int[] weights = {2, 3, 4, 5};
        int[] values = {3, 4, 5, 6};
        int capacity = 8;

        // 运行二维 DP 并打印结果
        int result = solution.knapsack2D(weights, values, capacity);
        System.out.println("\nMax value: " + result); // 预期: 9
    }
}