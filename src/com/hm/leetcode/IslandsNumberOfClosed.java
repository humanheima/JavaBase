package com.hm.leetcode;


/**
 * Created by p_dmweidu on 2025/4/11
 * Desc: 封闭岛屿数量问题.md
 */

public class IslandsNumberOfClosed {


    // 测试代码
    public static void main(String[] args) {

//        char[][] grid = {
//                {'1', '1', '0', '0', '0'},
//                {'1', '0', '0', '0', '0'},
//                {'0', '0', '0', '1', '0'},
//                {'0', '0', '0', '0', '0'},
//                {'0', '0', '1', '1', '0'}
//        };

        char[][] grid = {
                {'1', '1', '1', '1', '1', '1', '1', '0'},
                {'1', '0', '0', '0', '0', '1', '1', '0'},
                {'1', '0', '1', '0', '1', '1', '1', '0'},
                {'1', '0', '0', '0', '0', '1', '0', '1'},
                {'1', '1', '1', '1', '1', '1', '1', '0'}
        };

        IslandsNumberOfClosed solution = new IslandsNumberOfClosed();
        System.out.println("封闭岛屿数量: " + solution.closedIsland(grid)); // 输出: 1
    }


    public int closedIsland(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;

        // 第一步：消除边界上的岛屿
        // 遍历第一行和最后一行
        for (int j = 0; j < cols; j++) {
            //第一行
            if (grid[0][j] == '1') {
                dfs(grid, 0, j);
            }
            //最后一行
            if (grid[rows - 1][j] == '1') {
                dfs(grid, rows - 1, j);
            }
        }
        // 遍历第一列和最后一列（避免重复检查角落）
        for (int i = 1; i < rows - 1; i++) {
            //第一列
            if (grid[i][0] == '1') {
                dfs(grid, i, 0);
            }
            //最后一列
            if (grid[i][cols - 1] == '1') {
                dfs(grid, i, cols - 1);
            }
        }

        // 第二步：统计内部的封闭岛屿
        int closedIslandCount = 0;
        for (int i = 1; i < rows - 1; i++) {
            for (int j = 1; j < cols - 1; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    closedIslandCount++;
                }
            }
        }

        return closedIslandCount;
    }

    private void dfs(char[][] grid, int i, int j) {
        // 检查边界条件和是否为陆地
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') {
            return;
        }

        // 标记当前单元格为已访问（改为 '0'）
        grid[i][j] = '0';

        // 递归探索四个方向（上、下、左、右）
        dfs(grid, i - 1, j); // 上
        dfs(grid, i + 1, j); // 下
        dfs(grid, i, j - 1); // 左
        dfs(grid, i, j + 1); // 右
    }

}