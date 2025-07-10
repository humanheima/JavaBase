package com.hm.base.interview.android;

/**
 * 给定一个非空整数数组，其中每个元素都出现两次，只有一个元素出现一次。找出那个只出现一次的数字。
 * 只出现一次的数字，用异或解决
 * 看懂了
 * 异或的性质：
 * a ^ a = 0：相同数字异或结果为 0。
 * a ^ 0 = a：任何数字与 0 异或结果为自身。
 * a ^ b ^ a = b：异或满足交换律和结合律。
 * 使用异或可以将出现两次的数字抵消，只保留出现一次的数字
 */
public class SingleNumber {

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 1};
        int[] nums2 = {4, 1, 2, 1, 2};

        System.out.println("数组 [2, 2, 1] 中只出现一次的数字是: " + singleNumber(nums1)); // 输出: 1
        System.out.println("数组 [4, 1, 2, 1, 2] 中只出现一次的数字是: " + singleNumber(nums2)); // 输出: 4
    }

    public static int singleNumber(int[] nums) {
        int result = 0;
        //0 与任何正整数 A 的异或结果是 A 本身。
        for (int num : nums) {
            result ^= num; // 异或运算
        }
        return result;
    }


}