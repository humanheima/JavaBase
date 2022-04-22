package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/11/29
 * <p>
 * Desc:回溯法。题目：请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中任意一格开始，
 * 每一步可以在矩阵中间向左、右、上、下移动一格。如果一条路径经过了矩阵的某一格，那么该路径不能再次进入该格子。
 */
public class Test12 {

    public static void main(String[] args) {
        char[][] board = new char[3][4];
        board[0] = "ABCE".toCharArray();
        board[1] = "SFCS".toCharArray();
        board[2] = "ADEE".toCharArray();
        Test12 test12 = new Test12();

        System.out.println(test12.exist(board, "CES"));
        System.out.println(test12.exist(board, "ABCCED"));
        System.out.println(test12.exist(board, "AXBCCED"));
        System.out.println(test12.exist(board, "ABXCCED"));
    }


    public boolean exist(char[][] board, String word) {
        if (board == null || word == null || word.isEmpty()) {
            return false;
        }
        int rows = board.length;
        int columns = board[0].length;
        boolean[][] visited = new boolean[rows][columns];
        //注释1处，已经找到的字符在word中的下标，这里用对象传递，直接用int值的话，每次传递的都是0。
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (hasPathCore2(board, rows, columns, word, visited, i, j, 0)) {
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
    public boolean hasPathCore2(char[][] board, int rows, int cols, String word, boolean[][] visited, int currentRow, int currentColumn, int foundCharIndexOfWord) {
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
            foundCharIndexOfWord = foundCharIndexOfWord + 1;

            //按左右上下回溯
            hasPath = hasPathCore2(board, rows, cols, word, visited, currentRow, currentColumn - 1, foundCharIndexOfWord)//减小一列，向左搜索
                    || hasPathCore2(board, rows, cols, word, visited, currentRow, currentColumn + 1, foundCharIndexOfWord)//增加一列，向右搜索
                    || hasPathCore2(board, rows, cols, word, visited, currentRow - 1, currentColumn, foundCharIndexOfWord)//减小行，向上搜索
                    || hasPathCore2(board, rows, cols, word, visited, currentRow + 1, currentColumn, foundCharIndexOfWord);//增加一行，向下搜索
            if (!hasPath) {
                //如果从当前节点出发，没有找到匹配的字符串，那么这个节点还是可以进入的。
                visited[currentRow][currentColumn] = false;
            }
        }
        return hasPath;
    }

}
