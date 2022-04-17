package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/29
 * <p>
 * Desc:回溯法。题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中任意一格开始，
 * 每一步可以在矩阵中间向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 */
public class Test12 {

    public static void main(String[] args) {
        //ABCE  //ABCCED
        //SFCS
        //ADEE
        System.out.println(hasPath("ABCESFCSADEE".toCharArray(), 3, 4, "ABCCED".toCharArray()));// true
        System.out.println(hasPath("ABCESFCSADEE".toCharArray(), 3, 4, "AXBCCED".toCharArray()));// false

        System.out.println("----------------");
        char[][] board = new char[3][4];
        board[0] = "ABCE".toCharArray();
        board[1] = "SFCS".toCharArray();
        board[2] = "ADEE".toCharArray();
        Test12 test12 = new Test12();

        System.out.println(test12.exist(board, "ABCCED"));
        System.out.println(test12.exist(board, "AXBCCED"));
    }


    public boolean exist(char[][] board, String word) {
        if (board == null || word == null || word.isEmpty()) {
            return false;
        }
        int rows = board.length;
        int columns = board[0].length;
        boolean[][] visited = new boolean[rows][columns];
        //已经找到的字符在word中的下标，这里用数组传递，直接用int值的话，每次传递的都是0。
        Integer foundCharIndexOfWord = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (hasPathCore2(board, rows, columns, word, visited, i, j, foundCharIndexOfWord)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param board                输入矩阵
     * @param rows                 矩阵行数
     * @param cols                 矩阵列数
     * @param word                 要搜索的字符串
     * @param visited              访问标记数组
     * @param currentRow           当前处理的行号
     * @param currentColumn        当前处理的列号
     * @param foundCharIndexOfWord 已经找到的字符在word中的下标
     * @return 是否找到
     */
    public boolean hasPathCore2(char[][] board, int rows, int cols, String word, boolean[][] visited, int currentRow, int currentColumn, Integer foundCharIndexOfWord) {
        //注释1处，已经找到了字符串中的所有字符，返回true，
        if (foundCharIndexOfWord == word.length()) {
            return true;
        }
        //注释2处，hasPath为false
        boolean hasPath = false;
        //判断currentRow*currentColumn是否是当前字符
        if (currentRow >= 0 && currentRow < rows
                && currentColumn >= 0 && currentColumn < cols
                && board[currentRow][currentColumn] == word.charAt(foundCharIndexOfWord)
                && !visited[currentRow][currentColumn]) {
            //找到一个字符，将当前位置置为已经遍历过了，不可以再进入
            visited[currentRow][currentColumn] = true;
            //将找到的字符个数加1
            foundCharIndexOfWord++;

            //按左右上下回溯
            hasPath = hasPathCore2(board, rows, cols, word, visited, currentRow, currentColumn - 1, foundCharIndexOfWord)//减小一列，向左搜索
                    || hasPathCore2(board, rows, cols, word, visited, currentRow, currentColumn + 1, foundCharIndexOfWord)//增加一列，向右搜索
                    || hasPathCore2(board, rows, cols, word, visited, currentRow - 1, currentColumn, foundCharIndexOfWord)//减小行，向上搜索
                    || hasPathCore2(board, rows, cols, word, visited, currentRow + 1, currentColumn, foundCharIndexOfWord);//增加一行，向下搜索
            if (!hasPath) {
                foundCharIndexOfWord--;
                //如果从当前节点出发，没有找到匹配的字符串，那么这个节点还是可以进入的。
                visited[currentRow][currentColumn] = false;
            }
        }

        return hasPath;
    }


    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length != rows * cols || str == null || str.length < 1) {
            return false;
        }
        boolean[] visited = new boolean[rows * cols];
        //已经找到的字符的个数，这里用数组存储，如果直接用int值的话，那么每次调用hasPathCore方法，传入的都是0
        int[] foundCharCount = {0};
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (hasPathCore(matrix, rows, cols, str, visited, i, j, foundCharCount)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * @param matrix         输入矩阵
     * @param rows           矩阵行数
     * @param cols           矩阵列数
     * @param str            要搜索的字符串
     * @param visited        访问标记数组
     * @param row            当前处理的行号
     * @param col            当前处理的列号
     * @param foundCharCount 已经找到的str中字符个数
     * @return 是否找到
     */
    private static boolean hasPathCore(char[] matrix, int rows, int cols, char[] str, boolean[] visited, int row, int col, int[] foundCharCount) {
        if (foundCharCount[0] == str.length) {
            return true;
        }
        boolean hasPath = false;
        //判断位置是否合法
        if (row >= 0 && row < rows
                && col >= 0 && col < cols
                && matrix[row * cols + col] == str[foundCharCount[0]]
                && !visited[row * cols + col]) {
            visited[row * cols + col] = true;
            foundCharCount[0]++;

            //按左右上下回溯

            hasPath = hasPathCore(matrix, rows, cols, str, visited, row, col - 1, foundCharCount)
                    || hasPathCore(matrix, rows, cols, str, visited, row - 1, col, foundCharCount)
                    || hasPathCore(matrix, rows, cols, str, visited, row, col + 1, foundCharCount)
                    || hasPathCore(matrix, rows, cols, str, visited, row + 1, col, foundCharCount);
            if (!hasPath) {
                foundCharCount[0]--;
                visited[row * cols + col] = false;
            }

        }

        return hasPath;
    }


}
