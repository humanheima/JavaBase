package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/11
 * Desc: 岛屿最大面积问题.md
 */
public class IslandsAreaSolution {

    public int maxAreaOfIsland(char[][] grid) {
        // 边界检查
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        int maxArea = 0;

        // 遍历网格
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    // 计算当前岛屿面积并更新最大面积
                    int area = dfs(grid, i, j);
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        return maxArea;
    }

    // DFS 计算岛屿面积
    private int dfs(char[][] grid, int i, int j) {
        // 检查越界或非陆地格子
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return 0;
        }

        // 标记当前格子为已访问
        grid[i][j] = '0';

        // 当前格子计入面积，并递归探索四个方向
        int area = 1; // 当前格子
        area += dfs(grid, i - 1, j); // 上
        area += dfs(grid, i + 1, j); // 下
        area += dfs(grid, i, j - 1); // 左
        area += dfs(grid, i, j + 1); // 右

        return area;
    }

    // 测试代码
    public static void main(String[] args) {
        IslandsAreaSolution solution = new IslandsAreaSolution();

        // 测试用例 1：题目提供的网格
        char[][] grid1 = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };
        int result1 = solution.maxAreaOfIsland(grid1);
        int expected1 = 4; // 左上岛屿面积为 4
        System.out.println("测试用例 1 - 网格:");
        printGrid(grid1); // 打印修改后的网格（验证 DFS 标记）
        System.out.println("最大岛屿面积: " + result1);
        System.out.println("预期输出: " + expected1 + ", 测试" + (result1 == expected1 ? "通过" : "失败"));

        // 测试用例 2：全为水
        char[][] grid2 = {
                {'0', '0', '0'},
                {'0', '0', '0'}
        };
        int result2 = solution.maxAreaOfIsland(grid2);
        int expected2 = 0;
        System.out.println("\n测试用例 2 - 全水网格:");
        printGrid(grid2);
        System.out.println("最大岛屿面积: " + result2);
        System.out.println("预期输出: " + expected2 + ", 测试" + (result2 == expected2 ? "通过" : "失败"));

        // 测试用例 3：单个格子岛屿
        char[][] grid3 = {
                {'1'}
        };
        int result3 = solution.maxAreaOfIsland(grid3);
        int expected3 = 1;
        System.out.println("\n测试用例 3 - 单格岛屿:");
        printGrid(grid3);
        System.out.println("最大岛屿面积: " + result3);
        System.out.println("预期输出: " + expected3 + ", 测试" + (result3 == expected3 ? "通过" : "失败"));

        // 测试用例 4：多个岛屿，验证最大面积
        char[][] grid4 = {
                {'1', '1', '1'},
                {'0', '1', '0'},
                {'1', '1', '0'}
        };
        int result4 = solution.maxAreaOfIsland(grid4);
        int expected4 = 6; // 左上和右下连通，面积为 6吧
        System.out.println("\n测试用例 4 - 复杂岛屿:");
        printGrid(grid4);
        System.out.println("最大岛屿面积: " + result4);
        System.out.println("预期输出: " + expected4 + ", 测试" + (result4 == expected4 ? "通过" : "失败"));
    }

    // 辅助方法：打印网格（用于调试）
    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }
}