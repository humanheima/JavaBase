package com.hm.leetcode.code13;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 13. 罗马数字转整数
 * <p>
 * 罗马数字包含以下七种字符: I， V， X， L，C，D 和 M。
 * <p>
 * 字符          数值
 * I             1
 * V             5
 * X             10
 * L             50
 * C             100
 * D             500
 * M             1000
 * <p>
 * 例如， 罗马数字 2 写做 II ，即为两个并列的 1。12 写做 XII ，即为 X + II 。
 * 27 写做 XXVII, 即为 XX + V + II 。
 * <p>
 * 通常情况下，罗马数字中小的数字在大的数字的右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4 。同样地，数字 9 表示为 IX。
 * 这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * <p>
 * 给定一个罗马数字，将其转换成整数。
 * <p>
 * 示例：
 * 输入: s = "III"      输出: 3
 * 输入: s = "IV"       输出: 4
 * 输入: s = "IX"       输出: 9
 * 输入: s = "LVIII"    输出: 58   (L = 50, V= 5, III = 3)
 * 输入: s = "MCMXCIV"  输出: 1994 (M = 1000, CM = 900, XC = 90, IV = 4)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/roman-to-integer/
 */
public class RomanToIntegerTest {

    public static void main(String[] args) {
        // 用例：罗马数字 -> 期望整数
        test("III", 3);
        test("IV", 4);
        test("IX", 9);
        test("LVIII", 58);
        test("MCMXCIV", 1994);
        test("XL", 40);
        test("XC", 90);
        test("CD", 400);
        test("CM", 900);
        test("I", 1);
        test("MMMCMXCIX", 3999);
    }

    private static void test(String s, int expected) {
        int r1 = romanToInt(s);
        int r2 = romanToInt2(s);
        boolean ok = r1 == expected && r2 == expected;
        System.out.printf("romanToInt(%-10s) = %d / %d  期望=%d  %s%n",
                s, r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法一：从右往左遍历（核心规则）
     * 规则：若当前字符值 >= 右侧（上一个）字符值则做加法，否则做减法。
     * 从右往左只需记录“上一个值”即可判断，无需向后看。
     */
    public static int romanToInt(String s) {
        int result = 0;
        int prev = 0; // 右侧（上一个）字符的值
        for (int i = s.length() - 1; i >= 0; i--) {
            int curr = valueOf(s.charAt(i));
            if (curr >= prev) {
                result += curr; // 正常情况，累加
            } else {
                result -= curr; // 减法特例，如 IV 中的 I
            }
            prev = curr;
        }
        return result;
    }

    /**
     * 解法二：从左往右遍历（向后看一位）
     * 规则：若当前字符值 < 右边相邻字符值，说明是减法形式（如 IV），减去当前值；否则加上。
     */
    public static int romanToInt2(String s) {
        int result = 0;
        int n = s.length();
        for (int i = 0; i < n; i++) {
            int curr = valueOf(s.charAt(i));
            // 不是最后一位，且当前值比右边小 -> 减法
            if (i < n - 1 && curr < valueOf(s.charAt(i + 1))) {
                result -= curr;
            } else {
                result += curr;
            }
        }
        return result;
    }

    /**
     * 罗马字符到数值的映射。
     * 用 switch 而非 HashMap，避免装箱与哈希开销，常数更小。
     */
    private static int valueOf(char c) {
        switch (c) {
            case 'I': return 1;
            case 'V': return 5;
            case 'X': return 10;
            case 'L': return 50;
            case 'C': return 100;
            case 'D': return 500;
            case 'M': return 1000;
            default:  return 0;
        }
    }
}
