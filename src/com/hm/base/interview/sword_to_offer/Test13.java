package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/29
 * <p>
 * Desc:地上有个m行n列的方格。一个机器人从坐标(0,0)的格子开始移动，它每一次可以向左、右、上、下移动一格，
 * 但不能进入行坐标和列坐标的数位之和大于k的格子。
 * <p>
 * 解题思路：机器人从坐标(0,0)开始移动。当它准备进入坐标为(i,j)的格子时，通过检查坐标的数位和来判断机器人是否能够进入。
 * 如果机器人能够进入坐标为(i,j)的格子，我们接着再判断它能否进入四个相邻的格子(i,j-1)、(i-1,j),(i,j+1)和(i+1,j)。
 * <p>
 * 参考链接：https://blog.csdn.net/DERRANTCM/article/details/46887811
 */
public class Test13 {

    public static void main(String[] args) {
        Test13 test13 = new Test13();
        System.out.println(test13.movingCount(10, 10, 5));
        System.out.println(test13.movingCount(2, 3, 1));
        System.out.println(test13.movingCount(20, 20, 15));
    }


    public int movingCount(int rows, int columns, int threshold) {
        //记录格子是否进入过
        if (rows < 1 || columns < 1 || threshold < 0) {
            return 0;
        }
        boolean[][] visited = new boolean[rows][columns];
        return movingCountCore2(threshold, rows, columns, 0, 0, visited);
    }

    /**
     * @param threshold 约束值
     * @param rows      行数
     * @param cols      列数
     * @param row       当前处理的行
     * @param col       当前处理的列
     * @param visited   访问标记数组
     * @return 最多可走的方格
     */
    public int movingCountCore2(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        int count = 0;
        if (canIn(threshold, rows, cols, row, col, visited)) {
            visited[row][col] = true;
            count = 1;
            count = count + movingCountCore2(threshold, rows, cols, row - 1, col, visited);
            count = count + movingCountCore2(threshold, rows, cols, row, col - 1, visited);
            count = count + movingCountCore2(threshold, rows, cols, row + 1, col, visited);
            count = count + movingCountCore2(threshold, rows, cols, row, col + 1, visited);
        }
        return count;
    }

    /**
     * 判断机器人能否进入坐标为（row,col）的方格
     *
     * @param threshold
     * @param rows
     * @param cols
     * @param row
     * @param col
     * @param visited
     * @return 是否可以进入
     */
    public boolean canIn(int threshold, int rows, int cols, int row, int col, boolean[][] visited) {
        return row >= 0 && row < rows
                && col >= 0 && col < cols
                && !visited[row][col]
                && (getDigitSum(row) + getDigitSum(col) <= threshold);
    }

    /**
     * 一个数字的数位之和
     *
     * @param number
     * @return 数字的数位之和
     */
    public static int getDigitSum(int number) {
        int result = 0;
        while (number > 0) {
            result += number % 10;
            number = number / 10;
        }
        return result;
    }


}
