package com.hm.base.interview.android;

/**
 * 只出现一次的两个数字，看懂了，哈哈
 */
public class SingleNumberII {

    public static int[] findTwoSingleNumbers(int[] nums) {
        // 第一次异或：得到 x ^ y
        int xorResult = 0;
        for (int num : nums) {
            xorResult ^= num;
        }

        /**
         * 掩码计算：
         * xorResult & (-xorResult) 是位运算技巧，提取最低位的 1。
         * 例如 7 (111)，-7 = 001（补码），7 & 001 = 001。
         */
        // 找到最低位为 1 的掩码
        int mask = xorResult & (-xorResult); // 提取最低位的 1

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

    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 2, 4, 1};
        int[] result = findTwoSingleNumbers(nums);

        System.out.println("只出现一次的两个数字是: " + result[0] + " 和 " + result[1]);
    }
}