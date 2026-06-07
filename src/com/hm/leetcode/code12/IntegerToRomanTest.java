package com.hm.leetcode.code12;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 12. 整数转罗马数字
 * <p>
 * 罗马数字包含以下七种字符： I， V， X， L，C，D 和 M。
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
 * 例如，罗马数字 2 写做 II，即两个并列的 1；12 写做 XII，即 X + II；27 写做 XXVII，即 XX + V + II。
 * <p>
 * 通常，罗马数字中小的数字在大的数字右边。但也存在特例，例如 4 不写做 IIII，而是 IV。
 * 数字 1 在数字 5 的左边，所表示的数等于大数 5 减小数 1 得到的数值 4。
 * 同样地，数字 9 表示为 IX。这个特殊的规则只适用于以下六种情况：
 * <p>
 * I 可以放在 V (5) 和 X (10) 的左边，来表示 4 和 9。
 * X 可以放在 L (50) 和 C (100) 的左边，来表示 40 和 90。
 * C 可以放在 D (500) 和 M (1000) 的左边，来表示 400 和 900。
 * <p>
 * 给你一个整数，将其转为罗马数字。题目数据保证 1 <= num <= 3999。
 * <p>
 * 示例：
 * 输入: num = 3      输出: "III"
 * 输入: num = 4      输出: "IV"
 * 输入: num = 9      输出: "IX"
 * 输入: num = 58     输出: "LVIII"  (L = 50, V = 5, III = 3)
 * 输入: num = 1994   输出: "MCMXCIV" (M = 1000, CM = 900, XC = 90, IV = 4)
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/integer-to-roman/
 */
public class IntegerToRomanTest {

    public static void main(String[] args) {
        // 用例：数字 -> 期望罗马数字
        test(3, "III");
        test(4, "IV");
        test(9, "IX");
        test(58, "LVIII");
        test(1994, "MCMXCIV");
        test(40, "XL");
        test(90, "XC");
        test(400, "CD");
        test(900, "CM");
        test(1, "I");
        test(3999, "MMMCMXCIX");
    }

    private static void test(int num, String expected) {
        String r1 = intToRoman(num);
        String r2 = intToRoman2(num);
        boolean ok = r1.equals(expected) && r2.equals(expected);
        System.out.printf("intToRoman(%d) = %s / %s  期望=%s  %s%n",
                num, r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    // 罗马数字的值和符号对应数组（含 4、9 等特殊减法形式），从大到小排列
    private static final int[] VALUES = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] SYMBOLS = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    /**
     * 解法一：贪心 + 减法
     * 从最大的面值开始，能减就减，并追加对应符号，直到 num 减为 0。
     */
    public static String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        for (int i = 0; i < VALUES.length && num > 0; i++) {
            // 当前面值能匹配几次就追加几次
            while (num >= VALUES[i]) {
                roman.append(SYMBOLS[i]);
                num -= VALUES[i];
            }
        }
        return roman.toString();
    }

    // 按数位（千、百、十、个）预先打表，每一位 0~9 对应的罗马字符串
    private static final String[] THOUSANDS = {"", "M", "MM", "MMM"};
    private static final String[] HUNDREDS = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    private static final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    private static final String[] ONES = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};

    /**
     * 解法二：硬编码数位查表
     * 把每一位（千/百/十/个）0~9 的罗马表示预先打表，直接按位取出拼接，无循环。
     */
    public static String intToRoman2(int num) {
        return THOUSANDS[num / 1000]
                + HUNDREDS[num % 1000 / 100]
                + TENS[num % 100 / 10]
                + ONES[num % 10];
    }
}
