package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/12
 * <p>
 * Desc:输入一个矩阵，按照从外向里以顺时针的顺序依次扫印出每一个数字
 *
 * 参看根目录下的test29.png
 *
 * 测试用例：
 * 1 多行多列
 * 2 只有一行
 * 3 只有一列
 * 4 只有一行一列
 *
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46691013
 */
public class Test29 {

    public static void printMatrixClockWisely(int[][]numbers){

        if (numbers==null){
            return;
        }

        //记录一圈的开始位置的行
        int x=0;
        //记录一圈的开始位置的列
        int y=0;
        /**
         * 对每一圈（环）进行处理，
         * 行号最大是(numbers.length-1)/2
         * 列号最大是(numbers[0].length-1)/2
         */
        while (x*2<numbers.length&&y*2<numbers[0].length){
            printMatrixInCircle(numbers,x,y);
            x++;
            y++;
        }
    }

    /**
     *
     * @param numbers
     * @param startRow 行开始的位置
     * @param startColumn 列开始的位置
     */
    private static void printMatrixInCircle(int[][] numbers, int startRow, int startColumn) {
        int rows=numbers.length;
        int columns=numbers[0].length;

        /**
         * 比如打印一个6*6的矩阵的最外面一圈
         * startRow=0,startColumn=0;
         * endRow=5,endColumn=5;
         */
        int endRow=rows-startRow-1;
        int endColumn=columns-startColumn-1;

        //从左到右打印一行
        for (int i = startColumn; i <=endColumn ; i++) {
            System.out.print(numbers[startRow][i]+" ");
        }
        //如果结束行号大于起始行号，从上到下打印一列
        if (endRow>startRow){
            for (int i = startRow+1; i <= endRow; i++) {
                System.out.print(numbers[i][endColumn]+" ");
            }
        }
        //如果结束列号大于起始列号&&结束行号大于起始行号从右到左打印一行
        if (endColumn>startColumn&&endRow>startRow){
            for (int i = endColumn-1; i >=startColumn ; i--) {
                System.out.print(numbers[endRow][i]+" ");
            }
        }

        //终止列号比起始列号大&&终止行号减去起始行号大于等于2
        if (endColumn>startColumn&&endRow>startRow+1){
            for (int i = endRow-1; i >startRow ; i--) {
                System.out.print(numbers[i][startColumn]+" ");
            }
        }

    }

    public static void main(String[] args) {
        int[][] numbers = {
                {1, 2, 3, 4, 5},
                {16, 17, 18, 19, 6},
                {15, 24, 25, 20, 7},
                {14, 23, 22, 21, 8},
                {13, 12, 11, 10, 9},
        };
        printMatrixClockWisely(numbers);
        System.out.println();


        int[][] numbers2 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {22, 23, 24, 25, 26, 27, 28, 9},
                {21, 36, 37, 38, 39, 40, 29, 10},
                {20, 35, 34, 33, 32, 31, 30, 11},
                {19, 18, 17, 16, 15, 14, 13, 12},

        };
        printMatrixClockWisely(numbers2);
        System.out.println();


        int[][] numbers3 = {
                {1, 2, 3, 4, 5, 6, 7, 8}
        };
        printMatrixClockWisely(numbers3);
        System.out.println();

        int[][] numbers4 = {
                {1, 2, 3, 4, 5, 6, 7, 8},
                {16, 15, 14, 13, 12, 11, 10, 9}
        };
        printMatrixClockWisely(numbers4);
        System.out.println();


        int[][] numbers5 = {
                {1},
                {2},
                {3},
                {4},
                {5},
                {6},
                {7},
                {8}
        };
        printMatrixClockWisely(numbers5);
        System.out.println();

        int[][] numbers6 = {
                {0, 1},
                {15, 2},
                {14, 3},
                {13, 4},
                {12, 5},
                {11, 6},
                {10, 7},
                {9, 8}
        };
        printMatrixClockWisely(numbers6);
        System.out.println();


        int[][] numbers7 = {
                {1, 2},
                {4, 3}
        };
        printMatrixClockWisely(numbers7);
        System.out.println();

        int[][] numbers8 = {
                {1}
        };
        printMatrixClockWisely(numbers8);
        System.out.println();

        // 0个元素的数组
        printMatrixClockWisely(new int[][]{{}});
        // 空数组
        printMatrixClockWisely(null);
    }



}
