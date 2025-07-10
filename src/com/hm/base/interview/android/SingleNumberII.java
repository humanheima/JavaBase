package com.hm.base.interview.android;

/**
 * 只出现一次的两个数字.md
 * 只出现一次的两个数字，看懂了，哈哈
 * 结论：一个正整数 A 和其对应的 -A 进行按位与运算，结果是 A 的二进制表示中最低位 1 对应的值。
 * 例如
 * <p>
 * A = 5（00000101），最低位 1 在第 0 位（值为 2^0 = 1）。
 * -A = -5（11111011）。
 * A & -A = 00000001（十进制为 1）。
 * A = 12（00001100），最低位 1 在第 2 位（值为 2^2 = 4）。
 * -A = -12（11110100）。
 * A & -A = 00000100（十进制为 4）。
 */
public class SingleNumberII {

    public static void main(String[] args) {
        //int[] nums = {1, 2, 6, 2, 4, 1};
        int[] nums = {1, 2, 1, 3, 2, 5};
        int[] result = findTwoSingleNumbers(nums);

        System.out.println("只出现一次的两个数字是: " + result[0] + " 和 " + result[1]);
    }

    public static int[] findTwoSingleNumbers(int[] nums) {
        // 第一次异或：得到 x ^ y
        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }

        System.out.println("xorResult = " + xorResult + "  二进制表示 = " + Integer.toBinaryString(xorResult));
        System.out.println();

        /**
         * 掩码计算：
         * xorResult & (-xorResult) 是位运算技巧，提取最低位的 1。
         * 例如 7 (111)，-7 = 001（补码），7 & 001 = 001。
         *
         * xorResult = 2 (10), -2 = 10 (补码), 2 & 10 = 10 & 10 = 10，区分位是第二位(从右到左)
         *
         */
        // 找到最低位为 1 的掩码，这里是不是可以直接使用1？不可以，最低位的1，不是最右边1位的1。
        /**
         *
         * a 和 b 不相同，xor 一定不为 0，说明 a 和 b 在某些位上不同”，
         * 以及为什么找到 xor 中最低位的 1（记为 diff）可以用来区分 a 和 b
         *
         * 举个例子，比如 3 和 5
         *
         * xorResult = 3 ^ 5 = 011 ^ 101 = 110 = 6
         *
         * 3 和 5 在 xorResult 的右边第2位上不同，一个是 0，一个是 1。
         * 所以我们找到这个最低位的 1，就可以区分 3 和 5
         *
         * 最低位的1 =  6 & -6 = 110 & 010 = 010 = 2
         *
         * 3 & 2 = 011 & 010 = 010 = 010 = 0
         * 5 & 2 = 101 & 010 = 000 = 010
         *
         */
        int mask = xorResult & (-xorResult); // 提取最低位的 1
        System.out.println("xorResult & (-xorResult) = " + mask + "  二进制表示 = " + Integer.toBinaryString(mask));

        /**
         * 分组：
         * (num & mask) == 0：最低位为 0 的组。
         * (num & mask) != 0：最低位为 1 的组。
         *
         * 第二次异或：
         * 每组内成对数字抵消，剩下单次的数字。
         */
        // 分组并第二次异或
        int num1 = 0, num2 = 0;
        for (int num : nums) {
            if ((num & mask) == 0) {
                num1 ^= num; // 组 1：最低位为 0
            } else {
                num2 ^= num; // 组 2：最低位为 1
            }
        }

        return new int[]{num1, num2};
    }

}