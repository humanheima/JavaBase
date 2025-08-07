package com.hm.codes.code16;

import java.util.Arrays;

/**
 * Created by p_dmweidu on 2025/7/22
 * Desc: 最接近的三数之和
 */
public class LeetCode16 {

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums); // 排序数组
        int closestSum = nums[0] + nums[1] + nums[2]; // 初始化结果
        int minDiff = Math.abs(closestSum - target); // 初始化最小差值
        
        for (int i = 0; i < nums.length - 2; i++) {
            int left = i + 1, right = nums.length - 1;
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                int diff = Math.abs(sum - target);
                
                // 更新最小差值和结果
                if (diff < minDiff) {
                    minDiff = diff;
                    closestSum = sum;
                }
                
                if (sum == target) {
                    return sum; // 找到精确解，直接返回
                } else if (sum < target) {
                    left++; // 和太小，左指针右移
                } else {
                    right--; // 和太大，右指针左移
                }
            }
        }
        
        return closestSum;
    }
}