package com.hm.leetcode.code22;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by dumingwei on 2026/6/8
 *
 * Desc:
 */
public class GenerateParentheses {

    public static void main(String[] args) {
        // 测试用例
        //int[] testCases = {3, 1, 0};
        int[] testCases = {3};

        GenerateParentheses solution = new GenerateParentheses();

        for (int n : testCases) {
            System.out.println("Test Case: n = " + n);
            List<String> result = solution.generateParenthesis(n);
            System.out.println("Result(回溯): " + result);
            List<String> bfsResult = solution.generateParenthesisBFS(n);
            System.out.println("Result(BFS):  " + bfsResult);
            System.out.println();
        }
    }


    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        backtrack(new StringBuilder(), n, n, result);
        return result;
    }

    /**
     * @param current 当前已构建的括号串
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

    /**
     * BFS / 队列法：用队列保存中间状态，逐层扩展，直到字符串长度达到 2n。
     * 与回溯法剪枝约束相同：open < n 时可加 '('，close < open 时可加 ')'。
     */
    public List<String> generateParenthesisBFS(int n) {
        List<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        Queue<Node> queue = new LinkedList<>();
        // 初始状态：空串，已用左括号 0，已用右括号 0
        queue.offer(new Node("", 0, 0));

        while (!queue.isEmpty()) {
            Node node = queue.poll();
            // 长度达到 2n，是一个完整有效组合
            if (node.str.length() == 2 * n) {
                result.add(node.str);
                continue;
            }
            // 还能放左括号
            if (node.open < n) {
                queue.offer(new Node(node.str + '(', node.open + 1, node.close));
            }
            // 还能放右括号（右不能多于左）
            if (node.close < node.open) {
                queue.offer(new Node(node.str + ')', node.open, node.close + 1));
            }
        }
        return result;
    }

    /**
     * BFS 中间状态：当前括号串 + 已用左括号数 + 已用右括号数
     */
    private static class Node {
        String str;
        int open;
        int close;

        Node(String str, int open, int close) {
            this.str = str;
            this.open = open;
            this.close = close;
        }
    }

}