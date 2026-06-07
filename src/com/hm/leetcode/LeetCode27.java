package com.hm.leetcode;

/**
 * Created by p_dmweidu on 2025/4/2
 * Desc: 移除元素???
 */
class LeetCode27 {


    /**
     * 从数组中移除元素val，返回移除后的数组长度
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[count++] = nums[i];
            }
        }
        return count;
    }
}