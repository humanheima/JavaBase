package com.hm.leetcode.code20;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 20. 有效的括号
 * <p>
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s，判断字符串是否有效。
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合；
 * 2. 左括号必须以正确的顺序闭合；
 * 3. 每个右括号都有一个对应的相同类型的左括号。
 * <p>
 * 示例 1：输入：s = "()"        输出：true
 * 示例 2：输入：s = "()[]{}"    输出：true
 * 示例 3：输入：s = "(]"        输出：false
 * 示例 4：输入：s = "([)]"      输出：false
 * 示例 5：输入：s = "{[]}"      输出：true
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由括号 '()[]{}' 组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/valid-parentheses/
 */
public class ValidParenthesesTest {

    public static void main(String[] args) {
        test("()", true);
        test("()[]{}", true);
        test("(]", false);
        test("([)]", false);
        test("{[]}", true);
        test("(", false);          // 只有左括号，结束时栈非空
        test(")", false);          // 只有右括号，栈空时来了右括号
        test("(([]){})", true);
        test("][", false);         // 右括号先到
    }

    /**
     * 跑「父包已有的栈解法」与「HashMap 映射栈解法」两种实现并交叉验证。
     */
    private static void test(String s, boolean expected) {
        boolean r1 = new LeetCode20().isValid(s);   // 复用 com.hm.leetcode.code20.LeetCode20
        boolean r2 = isValidByMap(s);
        boolean ok = r1 == expected && r2 == expected;
        System.out.printf("%-12s => 栈:%-5s 映射:%-5s 期望:%-5s %s%n",
                "\"" + s + "\"", r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法二：HashMap 右括号→左括号映射 + 栈
     * 遇右括号时，栈顶必须等于它对应的左括号，否则无效。
     * 时间 O(n)，空间 O(n)。
     */
    public static boolean isValidByMap(String s) {
        // 长度为奇数，必然无法两两闭合，直接 false（小剪枝）
        if (s.length() % 2 != 0) {
            return false;
        }
        Map<Character, Character> pairs = new HashMap<>();
        pairs.put(')', '(');
        pairs.put(']', '[');
        pairs.put('}', '{');

        Deque<Character> stack = new ArrayDeque<>();
        for (char c : s.toCharArray()) {
            if (pairs.containsKey(c)) {
                // 右括号：栈空或栈顶不是匹配的左括号 -> 无效
                if (stack.isEmpty() || stack.pop() != pairs.get(c)) {
                    return false;
                }
            } else {
                // 左括号：入栈
                stack.push(c);
            }
        }
        return stack.isEmpty();
    }
}
