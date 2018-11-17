package com.hm.base.interview.sword_to_offer;

/**
 * Created by dmw on 2018/11/17.
 * Desc: 在二维数组中查找一个数字是否存在
 * <p>
 * 参考链接：{@see <a href="https://blog.csdn.net/derrantcm/article/details/45330789">在二维数组中查找一个数字是否存在</a>}
 * <p>
 * 规律：首先选取数组中右上角的数字。如果该数字等于要查找的数字，查找过程结束：
 * 如果该数字大于要查找的数字，剔除这个数字所在的列：如果该数字小于要查找的数字，剔除这个数字所在的行。
 * 也就是说如果要查找的数字不在数组的右上角，则每－次都在数组的查找范围中剔除）行或者一列，这样每一步都可以缩小
 * 查找的范围，直到找到要查找的数字，或者查找范围为空。
 */
public class FindNumberInTwoDimensionalArray {

    public static void main(String[] args) {
        int[][] matrix = {
                {1, 2, 8, 9},
                {2, 4, 9, 12},
                {4, 7, 10, 13},
                {6, 8, 11, 15}
        };
        System.out.println(find(matrix, 7));    // 要查找的数在数组中
        System.out.println(find(matrix, 5));    // 要查找的数不在数组中
        System.out.println(find(matrix, 1));    // 要查找的数是数组中最小的数字
        System.out.println(find(matrix, 15));   // 要查找的数是数组中最大的数字
        System.out.println(find(matrix, 0));    // 要查找的数比数组中最小的数字还小
        System.out.println(find(matrix, 16));   // 要查找的数比数组中最大的数字还大
        System.out.println(find(null, 16));     // 健壮性测试，输入空指针

    }

    /**
     * @param matrix 二维数组
     * @param number 要查找的数字
     */
    /**
     * @param matrix 二维数组
     * @param number 要查找的数字
     * @return 找到返回true, 找不到返回false。
     */
    private static boolean find(int[][] matrix, int number) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) {
            return false;
        }
        int rows = matrix.length;
        int columns = matrix[0].length;

        int row = 0;//起始行号
        int column = columns - 1;//起始列号

        while (row < rows && column >= 0) {
            if (matrix[row][column] == number) {
                return true;
            } else if (matrix[row][column] > number) {
                //当前数字比要找的数字大，说明要找的数字在当前数字的左边
                column--;//列数减一，向左移动
            } else {
                //当前数字比要找的数字小，说明要找的数字在当前数字的下边
                row++;
            }
        }

        return false;

    }

}
