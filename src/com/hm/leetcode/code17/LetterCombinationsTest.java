package com.hm.leetcode.code17;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 17. 电话号码的字母组合
 * <p>
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按任意顺序返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 2:abc 3:def 4:ghi 5:jkl 6:mno 7:pqrs 8:tuv 9:wxyz
 * <p>
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 * <p>
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 * <p>
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 */
public class LetterCombinationsTest {

    private static final String[] MAPPING = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public static void main(String[] args) {
        test("23", "[ad, ae, af, bd, be, bf, cd, ce, cf]");
        test("", "[]");
        test("2", "[a, b, c]");
        test("7", "[p, q, r, s]");
        test("79", "[pw, px, py, pz, qw, qx, qy, qz, rw, rx, ry, rz, sw, sx, sy, sz]");
    }

    /**
     * 两种解法各跑一次，排序后比较，确保结果集合一致且符合期望。
     * （题目允许任意顺序，故排序后再比较。）
     */
    private static void test(String digits, String expected) {
        List<String> r1 = backtrackSolution(digits);
        List<String> r2 = queueSolution(digits);
        Collections.sort(r1);
        Collections.sort(r2);
        boolean ok = r1.toString().equals(expected) && r2.toString().equals(expected);
        System.out.printf("digits=%-6s => 回溯:%s%n            队列:%s  期望:%s  %s%n",
                "\"" + digits + "\"", r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法一：回溯（DFS）
     * 时间 O(4^n · n)，空间 O(n)（递归栈，不含结果集）。
     */
    public static List<String> backtrackSolution(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private static void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        // 已选满 digits.length() 个字母，得到一个完整组合
        if (index == digits.length()) {
            result.add(current.toString());
            return;
        }
        //获取当前数字对应的所有字母
        String letters = MAPPING[digits.charAt(index) - '0'];
        for (int i = 0; i < letters.length(); i++) {
            current.append(letters.charAt(i));      // 选择
            backtrack(digits, index + 1, current, result); // 递归下一位
            current.deleteCharAt(current.length() - 1);     // 选择完最后1位后，后退，撤销选择（回溯）
        }
    }

    /**
     * 解法二：队列迭代（BFS）
     * 时间 O(4^n · n)，空间 O(4^n · n)（队列存中间组合）。
     */
    public static List<String> queueSolution(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        LinkedList<String> queue = new LinkedList<>();
        queue.offer("");
        for (int d = 0; d < digits.length(); d++) {
            String letters = MAPPING[digits.charAt(d) - '0'];
            int size = queue.size(); // 只处理上一层留下的组合
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                for (int j = 0; j < letters.length(); j++) {
                    queue.offer(current + letters.charAt(j));
                }
            }
        }
        result.addAll(queue);
        return result;
    }
}
