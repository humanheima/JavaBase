package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2022/4/17.
 * <p>
 * Desc:
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/shun-shi-zhen-da-yin-ju-zhen-by-leetcode-solution/
 * 来源：力扣（LeetCode）
 */
public class Test29LeetCode {

    public int[] spiralOrder(int[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return new int[0];
        }
        int rows = matrix.length;
        int columns = matrix[0].length;
        int[] order = new int[rows * columns];

        int index = 0;
        int left = 0;
        int right = columns - 1;
        int top = 0;
        int bottom = rows - 1;

        while (left <= right && top <= bottom) {
            //从左到右
            for (int column = left; column <= right; column++) {
                order[index++] = matrix[top][column];
            }
            //从上到下
            for (int row = top + 1; row <= bottom; row++) {
                order[index++] = matrix[row][right];
            }
            if (left < right && top < bottom) {
                //从右到左
                for (int column = right - 1; column > left; column--) {
                    order[index++] = matrix[bottom][column];
                }
                //从下到上
                for (int row = bottom; row > top; row--) {
                    order[index++] = matrix[row][left];
                }
            }
            left++;
            right--;
            top++;
            bottom--;
        }
        return order;
    }


}
