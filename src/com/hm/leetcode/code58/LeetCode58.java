package com.hm.leetcode.code58;

/**
 * Create by dumingwei on 2026-06-09
 * Desc: LeetCode 58. 最后一个单词的长度
 * <p>
 * 给你一个字符串 s，由若干单词组成，单词前后用一些空格字符隔开。
 * 返回字符串中最后一个单词的长度。
 * <p>
 * 单词是指仅由字母组成、不包含任何空格字符的最大子字符串。
 * <p>
 * 示例 1：
 * 输入：s = "Hello World"
 * 输出：5
 * 解释：最后一个单词是 "World"，长度为 5。
 * <p>
 * 示例 2：
 * 输入：s = "   fly me   to   the moon  "
 * 输出：4
 * 解释：最后一个单词是 "moon"，长度为 4。
 * <p>
 * 示例 3：
 * 输入：s = "luffy is still joyboy"
 * 输出：6
 * 解释：最后一个单词是 "joyboy"，长度为 6。
 * <p>
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅有英文字母和空格 ' ' 组成
 * s 中至少存在一个单词
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/length-of-last-word/
 */
public class LeetCode58 {

    public static void main(String[] args) {
        LeetCode58 solution = new LeetCode58();

        // 标准示例
        test(solution, "Hello World", 5);
        test(solution, "   fly me   to   the moon  ", 4);
        test(solution, "luffy is still joyboy", 6);

        // 边界情况
        test(solution, "a", 1);
        test(solution, "a ", 1);
        test(solution, " a", 1);
        test(solution, "   a   ", 1);
        test(solution, "hello", 5);                    // 无空格
        test(solution, "   ", 0);                      // 全空格（虽题目说至少一个单词，这里健壮性处理）
        test(solution, "word   with   trailing", 8);   // trailing spaces
        test(solution, "multiple   spaces   between", 7);
    }

    private static void test(LeetCode58 solution, String s, int expected) {
        int r1 = solution.lengthOfLastWord(s);               // 推荐解法（从后往前）
        int r2 = solution.lengthOfLastWordBySplit(s);        // split 解法（原实现风格）

        boolean ok = (r1 == expected && r2 == expected);
        System.out.printf("s=\"%-28s\"  从后=%-2d  split=%-2d  期望=%-2d  %s%n",
                s, r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 推荐解法：从字符串末尾开始遍历（双指针思想）
     * 先跳过所有结尾空格，再统计连续的非空格字符个数。
     * 时间 O(n)，空间 O(1)，无额外对象分配。
     */
    public int lengthOfLastWord(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int i = s.length() - 1;

        // 1. 跳过末尾的所有空格
        while (i >= 0 && s.charAt(i) == ' ') {
            i--;
        }

        // 2. 统计最后一个单词的长度
        int len = 0;
        while (i >= 0 && s.charAt(i) != ' ') {
            len++;
            i--;
        }

        return len;
    }

    /**
     * 解法二：使用 trim + split（原代码风格，适合快速实现）
     * 先去除首尾空格，再按空白分割，取最后一个子串的长度。
     * 注意：使用 "\\s+" 而非 "\\s" 更稳健，可正确处理多个连续空格。
     */
    public int lengthOfLastWordBySplit(String s) {
        if (s == null) return 0;
        s = s.trim();
        if (s.isEmpty()) {
            return 0;
        }
        // 使用 \\s+ 匹配一个或多个空白字符，避免产生空字符串
        String[] arr = s.split("\\s+");
        return arr[arr.length - 1].length();
    }
}
