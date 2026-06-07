package com.hm.leetcode.code14;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 14. 最长公共前缀
 * <p>
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 * 如果不存在公共前缀，返回空字符串 ""。
 * <p>
 * 示例 1：
 * 输入：strs = ["flower","flow","flight"]
 * 输出："fl"
 * <p>
 * 示例 2：
 * 输入：strs = ["dog","racecar","car"]
 * 输出：""
 * 解释：输入不存在公共前缀。
 * <p>
 * 提示：
 * 1 <= strs.length <= 200
 * 0 <= strs[i].length <= 200
 * strs[i] 仅由小写英文字母组成
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/longest-common-prefix/
 */
public class LongestCommonPrefixTest {

    public static void main(String[] args) {
        test(new String[]{"flower", "flow", "flight"}, "fl");
        test(new String[]{"dog", "racecar", "car"}, "");
        test(new String[]{"ab", "a"}, "a");
        test(new String[]{"abc"}, "abc");
        test(new String[]{"", "abc"}, "");
        test(new String[]{"abc", "abc", "abc"}, "abc");
        test(new String[]{"reflower", "flow", "flight"}, "");
    }

    private static void test(String[] strs, String expected) {
        String r1 = longestCommonPrefixVertical(strs);
        String r2 = longestCommonPrefixHorizontal(strs);
        boolean ok = r1.equals(expected) && r2.equals(expected);
        System.out.printf("%-35s => 纵向:\"%s\" 横向:\"%s\"  期望:\"%s\"  %s%n",
                java.util.Arrays.toString(strs), r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法一：纵向扫描
     * 以第一个字符串为基准，逐列（第 i 个字符）比较所有字符串。
     * 一旦某个字符串在该列不匹配（或已到末尾），前缀就到 i 为止。
     */
    public static String longestCommonPrefixVertical(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String first = strs[0];
        for (int i = 0; i < first.length(); i++) {
            char c = first.charAt(i);
            for (int j = 1; j < strs.length; j++) {
                // 当前字符串到末尾了，或该位字符不同 -> 前缀截止到 i
                if (i >= strs[j].length() || strs[j].charAt(i) != c) {
                    return first.substring(0, i);
                }
            }
        }
        return first;
    }

    /**
     * 解法二：横向扫描
     * 先把第一个字符串当作前缀，依次和后面的字符串求公共前缀，逐步缩短。
     * 一旦前缀变空即可提前返回。
     */
    public static String longestCommonPrefixHorizontal(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        String prefix = strs[0];
        for (int i = 1; i < strs.length; i++) {
            prefix = commonPrefix(prefix, strs[i]);
            if (prefix.isEmpty()) {
                return "";
            }
        }
        return prefix;
    }

    // 求两个字符串的最长公共前缀
    private static String commonPrefix(String s1, String s2) {
        int len = Math.min(s1.length(), s2.length());
        int i = 0;
        while (i < len && s1.charAt(i) == s2.charAt(i)) {
            i++;
        }
        return s1.substring(0, i);
    }
}
