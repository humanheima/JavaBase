package com.hm.leetcode.code22;

import java.util.ArrayList;
import java.util.List;

public class GenerateParentheses {

    public static void main(String[] args) {
        // 测试用例
        //int[] testCases = {3, 1, 0};
        int[] testCases = {2};

        GenerateParentheses solution = new GenerateParentheses();

        for (int n : testCases) {
            System.out.println("Test Case: n = " + n);
            List<String> result = solution.generateParenthesis(n);
            System.out.println("Result: " + result);
            System.out.println();
        }
    }


    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(new StringBuilder(), n, n, result);
        return result;
    }

    /**
     * @param current
     * @param left    左括号剩余数量
     * @param right   右括号剩余数量
     * @param result
     */
    private void backtrack(StringBuilder current, int left, int right, List<String> result) {
        // 终止条件：字符串长度达到 2n
        if (left == 0 && right == 0) {
            result.add(current.toString());
            return;
        }

        // 添加左括号
        if (left > 0) {
            current.append('(');
            backtrack(current, left - 1, right, result);
            current.deleteCharAt(current.length() - 1); // 回溯
        }

        // 添加右括号（需确保右括号不多于左括号）
        if (right > left) {
            current.append(')');
            backtrack(current, left, right - 1, result);
            current.deleteCharAt(current.length() - 1); // 回溯
        }
    }
}