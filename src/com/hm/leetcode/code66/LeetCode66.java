package com.hm.leetcode.code66;

import java.util.Arrays;

/**
 * Create by dumingwei on 2026-06-09
 * Desc: LeetCode 66. 加一
 * <p>
 * 给定一个由 整数 组成的 非空 数组所表示的非负整数，在该数的基础上加一。
 * <p>
 * 最高位数字存放在数组的首位， 数组中每个元素只存储单个数字。
 * <p>
 * 你可以假设除了整数 0 之外，这个整数不会以零开头。
 * <p>
 * 示例 1：
 * 输入：digits = [1,2,3]
 * 输出：[1,2,4]
 * 解释：输入数组表示数字 123。
 * <p>
 * 示例 2：
 * 输入：digits = [4,3,2,1]
 * 输出：[4,3,2,2]
 * 解释：输入数组表示数字 4321。
 * <p>
 * 示例 3：
 * 输入：digits = [9]
 * 输出：[1,0]
 * 解释：输入数组表示数字 9，加一后得到 10。
 * <p>
 * 提示：
 * 1 <= digits.length <= 100
 * 0 <= digits[i] <= 9
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/plus-one/
 */
public class LeetCode66 {

    public static void main(String[] args) {
        LeetCode66 solution = new LeetCode66();

        // 标准示例
        test(solution, new int[]{1, 2, 3}, new int[]{1, 2, 4});
        test(solution, new int[]{4, 3, 2, 1}, new int[]{4, 3, 2, 2});
        test(solution, new int[]{9}, new int[]{1, 0});

        // 需要进位扩容
        test(solution, new int[]{9, 9, 9}, new int[]{1, 0, 0, 0});
        test(solution, new int[]{1, 9, 9}, new int[]{2, 0, 0});

        // 普通进位
        test(solution, new int[]{1, 9}, new int[]{2, 0});
        test(solution, new int[]{2, 0, 0}, new int[]{2, 0, 1});

        // 边界：0 和 单元素
        test(solution, new int[]{0}, new int[]{1});
        test(solution, new int[]{5}, new int[]{6});
    }

    private static void test(LeetCode66 solution, int[] digits, int[] expected) {
        // 由于方法会原地修改数组，我们传入副本
        int[] inputCopy = Arrays.copyOf(digits, digits.length);
        int[] result = solution.plusOne(inputCopy);

        boolean ok = Arrays.equals(result, expected);
        System.out.printf("输入=%-20s  输出=%-20s  期望=%-20s  %s%n",
                Arrays.toString(digits),
                Arrays.toString(result),
                Arrays.toString(expected),
                ok ? "✓" : "✗ FAIL");
    }

    /**
     * 从数组末尾开始模拟加一操作。
     * 使用 carry 记录进位，初始为 1（表示加一）。
     * 若最终仍有进位，说明需要扩容（原数字全为 9）。
     */
    public int[] plusOne(int[] digits) {
        int n = digits.length;
        int carry = 1; // 初始进位为1，相当于加一

        for (int i = n - 1; i >= 0; i--) {
            int sum = digits[i] + carry;
            digits[i] = sum % 10;
            carry = sum / 10;
            if (carry == 0) {
                break; // 无进位则提前终止循环
            }
        }

        if (carry == 1) {
            // 需要扩展数组，例如999 -> 1000
            int[] newDigits = new int[n + 1];
            newDigits[0] = 1;
            return newDigits;
        } else {
            return digits;
        }
    }
}
