package com.hm.codes;

/**
 * Created by p_dmweidu on 2025/4/11
 * Desc: 岛屿周长问题.md
 * https://leetcode.cn/problems/island-perimeter/description/
 *
 */
public class IslandsPerimeterSolution {

    public int islandPerimeter(int[][] grid) {

        int perimeter = 0;
        int rows = grid.length; // 行数
        int cols = grid[0].length; // 列数

        // 遍历每个格子
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) { // 找到陆地格子
                    // 检查上边
                    if (i == 0 || grid[i - 1][j] == 0) {
                        perimeter++;
                    }
                    // 检查下边
                    if (i == rows - 1 || grid[i + 1][j] == 0) {
                        perimeter++;
                    }
                    // 检查左边
                    if (j == 0 || grid[i][j - 1] == 0) {
                        perimeter++;
                    }
                    // 检查右边
                    if (j == cols - 1 || grid[i][j + 1] == 0) {
                        perimeter++;
                    }
                }
            }
        }

        return perimeter;
    }

    // 测试代码
    public static void main(String[] args) {
        IslandsPerimeterSolution solution = new IslandsPerimeterSolution();
        int[][] grid = {
            {0, 1, 0, 0},
            {1, 1, 1, 0},
            {0, 1, 0, 0},
            {0, 0, 0, 0}
        };
        System.out.println("岛屿周长: " + solution.islandPerimeter(grid)); // 输出 16
    }
}