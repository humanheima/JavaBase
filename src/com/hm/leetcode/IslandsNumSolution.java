package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/11
 * Desc: 岛屿数量问题
 */
public class IslandsNumSolution {

    // 测试代码
    public static void main(String[] args) {
        // 初始化输入网格
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        // 创建 Solution 实例
        IslandsNumSolution solution = new IslandsNumSolution();

        // 调用 numIslands 方法
        int result = solution.numIslands(grid);

        // 打印结果
        System.out.println("岛屿数量: " + result);

    }

    public int numIslands(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // 遍历网格
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    count++; // 发现新岛屿
                    dfs(grid, i, j); // 淹没整个岛屿
                }
            }
        }

        return count;
    }

    /**
     * DFS 淹没岛屿，意思是标记当前格子为已访问，也就是置为 '0'
     */
    private void dfs(char[][] grid, int i, int j) {
        // 边界检查或非陆地格子
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        // 标记当前格子为已访问
        grid[i][j] = '0';

        // 探索四个方向
        dfs(grid, i - 1, j); // 上
        dfs(grid, i + 1, j); // 下
        dfs(grid, i, j - 1); // 左
        dfs(grid, i, j + 1); // 右
    }
}