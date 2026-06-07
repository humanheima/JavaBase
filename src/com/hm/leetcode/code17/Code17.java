package com.hm.leetcode.code17;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by p_dmweidu on 2025/7/26
 * Desc:电话号码的字母组合
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * <p>
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 *
 * 结论：使用迭代法比较好理解。回溯法需要再研究一下。
 */
public class Code17 {


    public static void main(String[] args) {

        String digits = "23";
        Code17 code17 = new Code17();
        System.out.println(code17.letterCombinations(digits));

        System.out.println("-----------------");
        //System.out.println(code17.letterCombinations2(digits));
    }

    // 数字到字母的映射
    private static final String[] MAPPING = {
            "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"
    };

    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        backtrack(digits, 0, new StringBuilder(), result);
        return result;
    }

    private void backtrack(String digits, int index, StringBuilder current, List<String> result) {
        // 如果当前索引等于输入字符串长度，说明一个组合完成
        int length = digits.length();
        if (index == length) {
            result.add(current.toString());
            return;
        }

        // 获取当前数字对应的字母集合
        String letters = MAPPING[digits.charAt(index) - '0'];
        // 遍历每个字母
        for (char c : letters.toCharArray()) {
            // 添加当前字母
            current.append(c);
            // 递归处理下一个数字
            backtrack(digits, index + 1, current, result);
            // 回溯：移除当前字母
            current.deleteCharAt(current.length() - 1);
        }
    }


    /**
     * 使用队列
     *
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        List<String> result = new ArrayList<>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }

        LinkedList<String> queue = new LinkedList<>();
        queue.offer(""); // 初始化空字符串

        for (char digit : digits.toCharArray()) {
            String letters = MAPPING[digit - '0'];
            int size = queue.size(); // 当前队列大小
            // 处理队列中的每个组合
            for (int i = 0; i < size; i++) {
                String current = queue.poll();
                // 为每个字母生成新组合
                for (char c : letters.toCharArray()) {
                    queue.offer(current + c);
                }
            }
        }

        result.addAll(queue);
        return result;
    }


}
