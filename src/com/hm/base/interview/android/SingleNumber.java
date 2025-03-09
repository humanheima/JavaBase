package com.hm.base.interview.android;

/**
 * 给定一个非空整数数组，其中每个元素都出现两次，只有一个元素出现一次。找出那个只出现一次的数字。
 * 只出现一次的数字，用异或解决
 */
public class SingleNumber {
    public static int singleNumber(int[] nums) {
        int result = 0;
        for (int num : nums) {
            result ^= num; // 异或运算
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums1 = {2, 2, 1};
        int[] nums2 = {4, 1, 2, 1, 2};
        
        System.out.println("数组 [2, 2, 1] 中只出现一次的数字是: " + singleNumber(nums1)); // 输出: 1
        System.out.println("数组 [4, 1, 2, 1, 2] 中只出现一次的数字是: " + singleNumber(nums2)); // 输出: 4
    }
}