package com.hm.leetcode.code7;

/**
 * Crete by dumingwei on 2026-06-07
 * Desc: 整数反转
 * <p>
 * 问题描述
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位有符号整数的范围 [-2^31, 2^31 - 1] ，就返回 0。
 * <p>
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 * <p>
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 * <p>
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 * <p>
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 * <p>
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 * <p>
 * 提示：-2^31 <= x <= 2^31 - 1
 * <p>
 * 解题思路：
 * <p>
 * 逐位弹出 + 推入。每次用 x % 10 取出末位 digit，用 x / 10 去掉末位，
 * 把 digit 推到结果 result 的末尾：result = result * 10 + digit。
 * <p>
 * 关键在于不借助 long、在 int 范围内提前判断溢出：
 * 在执行 result = result * 10 + digit 之前，先检查 result 是否会越界。
 * 正向：result > MAX/10，或 result == MAX/10 且 digit > 7（MAX = 2147483647，末位 7）；
 * 负向：result < MIN/10，或 result == MIN/10 且 digit < -8（MIN = -2147483648，末位 8）。
 * 一旦会溢出立即返回 0。
 * <p>
 * 取模对负数同样适用：Java 中 -123 % 10 == -3，-123 / 10 == -12，
 * 因此无需单独记录符号，负数全程保持为负即可。
 * <p>
 * 时间复杂度 O(log|x|)（位数），空间复杂度 O(1)。
 * <p>
 * https://leetcode.cn/problems/reverse-integer/
 */
public class LeetCode7 {

    // 测试
    public static void main(String[] args) {
        LeetCode7 solution = new LeetCode7();
        int[] testCases = {123, -123, 120, 0, 1534236469, Integer.MAX_VALUE, Integer.MIN_VALUE, 5};
        int[] expected = {321, -321, 21, 0, 0, 0, 0, 5};

        for (int i = 0; i < testCases.length; i++) {
            int result = solution.reverse(testCases[i]);
            System.out.printf("Input: %d, Output: %d, Expected: %d, %s%n",
                    testCases[i], result, expected[i],
                    result == expected[i] ? "Pass" : "Fail");
        }
    }

    /**
     * 不能用long
     *
     * @param x
     * @return
     */
    public int reverse(int x) {
        int result = 0;
        while (x != 0) {
            int digit = x % 10; // 取出末位（负数时 digit 为负）
            x /= 10;            // 去掉末位

            // 推入前判断正向溢出：result*10 + digit > Integer.MAX_VALUE
            if (result > Integer.MAX_VALUE / 10
                    || (result == Integer.MAX_VALUE / 10 && digit > 7)) {
                return 0;
            }
            // 推入前判断负向溢出：result*10 + digit < Integer.MIN_VALUE
            if (result < Integer.MIN_VALUE / 10
                    || (result == Integer.MIN_VALUE / 10 && digit < -8)) {
                return 0;
            }

            result = result * 10 + digit;
        }
        return result;
    }

    /**
     * 允许使用 long 的简化版。
     * <p>
     * 一个 int 最多 10 位，反转后的值绝对值不超过 9999999999，
     * 远在 long（约 9.2e18）的范围内，因此整个反转过程不会溢出 long。
     * 这样可以：
     * 1. 不用记录符号——long 取模对负数同样向零取整，负号自然保留；
     * 2. 不用在循环内逐位判溢出——把完整结果累加进 long 后，循环外一次性判断越界即可。
     *
     * @param x 待反转的整数
     * @return 反转结果，越界返回 0
     */
    public int reverseUseLong(int x) {
        long reversed = 0;
        while (x != 0) {
            reversed = reversed * 10 + x % 10; // 负数时 x % 10 为负，符号自然保留
            x /= 10;
        }
        // 反转完成后一次性判断是否超出 int 范围
        if (reversed > Integer.MAX_VALUE || reversed < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) reversed;
    }

}
