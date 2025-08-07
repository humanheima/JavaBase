package com.hm.codes;

import java.util.Stack;

/**
 * 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串中的括号是否有效。有效括号需满足：
 * <p>
 * 1. 左括号必须用相同类型的右括号闭合
 * 2. 左括号必须以正确的顺序闭合
 * 3. 空字符串视为有效
 * LeetCode20
 */
public class LeetCode20 {

    /**
     * 使用这种算法就很好
     *
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        // 初始化栈
        Stack<Character> stack = new Stack<>();

        // 遍历字符串的每个字符
        for (char c : s.toCharArray()) {
            // 遇到左括号，压入栈
            if (c == '(' || c == '{' || c == '[') {
                stack.push(c);
            }
            // 遇到右括号
            else if (c == ')' || c == '}' || c == ']') {
                // 栈为空，无法匹配，返回false
                if (stack.isEmpty()) {
                    return false;
                }
                // 弹出栈顶元素
                char top = stack.pop();
                // 检查是否匹配
                if (!isMatchingPair(top, c)) {
                    return false;
                }
            }
        }

        // 检查栈是否为空
        return stack.isEmpty();
    }

    // 辅助方法：检查括号是否匹配
    private boolean isMatchingPair(char left, char right) {
        return (left == '(' && right == ')') ||
                (left == '{' && right == '}') ||
                (left == '[' && right == ']');
    }

    // 测试代码
    public static void main(String[] args) {
        LeetCode20 solution = new LeetCode20();
        String[] tests = {"()", "()[]{}", "(]", "([)]", "{[]}"};
        for (String test : tests) {
            System.out.println("Input: " + test + " -> Output: " + solution.isValid(test));
        }
    }
}