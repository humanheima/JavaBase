package com.hm.leetcode.code28;

/**
 * Create by dumingwei on 2026-06-09
 * Desc: LeetCode 28. 找出字符串中第一个匹配项的下标
 * <p>
 * 给你两个字符串 haystack 和 needle ，请你在 haystack 字符串中找出 needle 字符串的第一个匹配项的下标（下标从 0 开始）。
 * 如果 needle 不是 haystack 的一部分，则返回 -1 。
 * <p>
 * 示例 1：
 * 输入：haystack = "sadbutsad", needle = "sad"
 * 输出：0
 * 解释："sad" 在下标 0 和 6 处匹配，但是第一个匹配项是下标 0 。
 * <p>
 * 示例 2：
 * 输入：haystack = "leetcode", needle = "leeto"
 * 输出：-1
 * 解释："leeto" 没有在 "leetcode" 中出现，所以返回 -1 。
 * <p>
 * 提示：
 * 1 <= haystack.length, needle.length <= 10^4
 * haystack 和 needle 仅由小写英文字符组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 */
public class Code28 {

    public static void main(String[] args) {
        Code28 solution = new Code28();

        // 基础测试
        test(solution, "sadbutsad", "sad", 0);
        test(solution, "leetcode", "leeto", -1);
        test(solution, "hello", "ll", 2);
        test(solution, "aaaaa", "bba", -1);
        test(solution, "a", "a", 0);

        // 边界与特殊
        test(solution, "abc", "abcd", -1);           // needle 更长
        test(solution, "mississippi", "issip", 4);   // 中间匹配
        test(solution, "abcabcabc", "abc", 0);       // 多个匹配，取第一个
        test(solution, "abcabcabc", "cab", 2);
        test(solution, "abababab", "babab", 1);      // 重叠情况
    }

    private static void test(Code28 solution, String haystack, String needle, int expected) {
        int r1 = solution.strStr(haystack, needle);           // 暴力解法
        int r2 = solution.strStrKMP(haystack, needle);        // KMP 解法

        boolean ok = (r1 == expected && r2 == expected);
        System.out.printf("haystack=\"%-12s\" needle=\"%-6s\"  暴力=%-3d  KMP=%-3d  期望=%-3d  %s%n",
                haystack, needle, r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法一：暴力匹配（Brute Force）
     * 从 haystack 的每个可能起始位置 i 开始，逐字符与 needle 比较。
     * 找到第一个完全匹配的位置即返回。
     */
    public int strStr(String haystack, String needle) {
        if (haystack == null || needle == null) {
            return -1;
        }
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) {
            return 0;                 // 空 needle 按惯例返回 0
        }
        if (n < m) {
            return -1;
        }

        for (int i = 0; i <= n - m; i++) {
            boolean match = true;
            for (int j = 0; j < m; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    match = false;
                    break;
                }
            }
            if (match) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 解法二：KMP 算法（Knuth-Morris-Pratt）
     * 预处理 needle 得到 next（前缀表），匹配失败时利用 next 回退 j 而非 i，
     * 避免重复比较已匹配的前缀，实现 O(n + m) 线性时间。
     */
    public int strStrKMP(String haystack, String needle) {
        if (haystack == null || needle == null) return -1;
        int n = haystack.length();
        int m = needle.length();
        if (m == 0) return 0;
        if (n < m) return -1;

        int[] next = buildNext(needle);
        int i = 0; // haystack 指针
        int j = 0; // needle 指针

        while (i < n) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
                if (j == m) {
                    return i - m; // 匹配成功，返回起始下标
                }
            } else {
                if (j != 0) {
                    j = next[j - 1]; // 利用前缀表回退 j
                } else {
                    i++;             // j 已在 0，无法回退，只能前进 i
                }
            }
        }
        return -1;
    }

    /**
     * 构建 KMP 的 next（前缀函数 / LPS 数组）。
     * next[j] 表示 needle[0..j-1] 的最长真前缀与真后缀的匹配长度。
     * 时间 O(m)，空间 O(m)。
     */
    private int[] buildNext(String pat) {
        int m = pat.length();
        int[] next = new int[m];
        int len = 0;  // 当前最长匹配前缀长度
        int i = 1;

        while (i < m) {
            if (pat.charAt(i) == pat.charAt(len)) {
                len++;
                next[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = next[len - 1];
                } else {
                    next[i] = 0;
                    i++;
                }
            }
        }
        return next;
    }
}
