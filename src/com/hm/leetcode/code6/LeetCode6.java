package com.hm.leetcode.code6;

/**
 * Crete by dumingwei on 2026-06-07
 * Desc: Z 字形变换
 * <p>
 * 问题描述
 * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列。
 * <p>
 * 比如输入字符串为 "PAYPALISHIRING" 行数为 3 时，排列如下：
 * <p>
 * P   A   H   N
 * A P L S I I G
 * Y   I   R
 * <p>
 * 之后，你的输出需要从左往右逐行读取，产生出一个新的字符串，比如："PAHNAPLSIIGYIR"。
 * <p>
 * 示例 1：
 * 输入：s = "PAYPALISHIRING", numRows = 3
 * 输出："PAHNAPLSIIGYIR"
 * <p>
 * 示例 2：
 * 输入：s = "PAYPALISHIRING", numRows = 4
 * 输出："PINALSIGYAHRPI"
 * 解释：
 * P     I    N
 * A   L S  I G
 * Y A   H R
 * P     I
 * <p>
 * 示例 3：
 * 输入：s = "A", numRows = 1
 * 输出："A"
 * <p>
 * 解题思路：
 * <p>
 * 按行访问（模拟字符的走向）。
 * 使用 numRows 个 StringBuilder 分别代表每一行的字符。
 * 用一个指针在行之间来回移动：从第 0 行往下走到第 numRows-1 行，
 * 到达边界后改变方向（向上），如此往复。
 * 把每个字符依次追加到当前所在行，最后按行拼接即可。
 * <p>
 * 时间复杂度 O(n)，空间复杂度 O(n)。
 * <p>
 * https://leetcode.cn/problems/zigzag-conversion/
 */
public class LeetCode6 {

    // 测试
    public static void main(String[] args) {
        LeetCode6 solution = new LeetCode6();
        System.out.println(solution.convert("PAYPALISHIRING", 3)); // PAHNAPLSIIGYIR
        System.out.println(solution.convert("PAYPALISHIRING", 4)); // PINALSIGYAHRPI
        System.out.println(solution.convert("A", 1));              // A
    }

    public String convert(String s, int numRows) {
        // 只有一行时，无需变换，直接返回原字符串
        if (numRows == 1 || s.length() <= numRows) {
            return s;
        }

        // 每一行对应一个 StringBuilder
        StringBuilder[] rows = new StringBuilder[numRows];
        for (int i = 0; i < numRows; i++) {
            rows[i] = new StringBuilder();
        }

        int curRow = 0;       // 当前所在行
        boolean goingDown = false; // 移动方向，true 表示向下

        for (char c : s.toCharArray()) {
            rows[curRow].append(c);
            // 到达第一行或最后一行时改变方向
            if (curRow == 0 || curRow == numRows - 1) {
                goingDown = !goingDown;
            }
            curRow += goingDown ? 1 : -1;
        }

        // 按行拼接得到结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }
        return result.toString();
    }

    /**
     * 行数，老的写法
     * @param s
     * @param numRows
     * @return
     */
    public String convert2(String s, int numRows) {
        // 如果行数为 1，直接返回原字符串
        if (numRows == 1) return s;

        // 使用 StringBuilder 数组来存储每一行的字符
        /**
         * 例如，如果 numRows = 5，但 s.length() = 3，最多只需要 3 行，因为字符数量不足以填满 5 行。
         */
        StringBuilder[] rows = new StringBuilder[Math.min(numRows, s.length())];
        for (int i = 0; i < rows.length; i++) {
            rows[i] = new StringBuilder();
        }

        // 当前行号和移动方向
        int curRow = 0;
        int step = 1; // 1 表示向下，-1 表示向上

        // 遍历字符串
        for (char c : s.toCharArray()) {
            // 这里是关键，将当前字符追加到对应行
            rows[curRow].append(c);

            // 如果到达第一行或最后一行，改变方向
            if (curRow == 0) {
                step = 1; // 开始向下
            } else if (curRow == numRows - 1) {
                step = -1; // 开始向上
            }

            // 更新当前行号
            curRow += step;
        }

        // 合并所有行的结果
        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rows) {
            result.append(row);
        }

        return result.toString();
    }

}
