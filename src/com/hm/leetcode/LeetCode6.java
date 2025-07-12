package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/3/30
 * Desc: Z字形变换，感觉叫倒N字形( И )变换更直观
 */
public class LeetCode6 {

    public static void main(String[] args) {

        LeetCode6 solution = new LeetCode6();

        test1(solution);

        //test2(solution);
    }

    private static void test1(LeetCode6 solution) {
        // 测试用例 1
        String s1 = "PAYPALISHIRING";
        int numRows1 = 3;
        String result1 = solution.convert(s1, numRows1);
        System.out.println("Test 1: " + result1 + " (Expected: PAHNAPLSIIGYIR)");
    }

    private static void test2(LeetCode6 solution) {
        // 测试用例 2
        String s2 = "PAYPALISHIRING";
        int numRows2 = 4;
        String result2 = solution.convert(s2, numRows2);
        System.out.println("Test 2: " + result2 + " (Expected: PINALSIGYAHRPI)");
    }

    /**
     * 行数
     * @param s
     * @param numRows
     * @return
     */
    public String convert(String s, int numRows) {
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