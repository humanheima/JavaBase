package com.hm.leetcode;

import java.util.Arrays;

/**
 * Created by p_dmweidu on 2025/4/2
 * Desc: 删除有序数组中的重复项
 */
class LeetCode26 {

    public static void main(String[] args) {

        //int[] nums = {1, 1, 1, 2, 2, 3};


        int[] nums = {0,0,1,1,1,2,2,3,3,4};

        LeetCode26 solution = new LeetCode26();
        int result = solution.removeDuplicates(nums);
        System.out.println(result);
        System.out.println(Arrays.toString(nums));
    }

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int prev = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != prev) {
                nums[count++] = nums[i];
                prev = nums[i];
            }
        }
        return count;

    }

}