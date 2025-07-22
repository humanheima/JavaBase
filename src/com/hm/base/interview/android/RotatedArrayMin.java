package com.hm.base.interview.android;

/**
 * Created by p_dmweidu on 2025/7/22
 * Desc:
 */
public class RotatedArrayMin {

    public int findMin(int[] nums) {
        if (nums == null || nums.length == 0) {
            return -1; // 或者抛出异常，视需求而定
        }

        int left = 0;
        int right = nums.length - 1;

        // 如果数组没有旋转，直接返回第一个元素
        if (nums[left] <= nums[right]) {
            return nums[0];
        }

        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            // 检查是否找到最小值
            // 最小值满足：比前一个元素小，或是数组开头
            if (mid == 0 || nums[mid] < nums[mid - 1]) {
                return nums[mid];
            }
            
            // 判断哪半边包含最小值
            if (nums[mid] > nums[right]) { // 最小值在右半边
                left = mid + 1;
            } else { // 最小值在左半边
                right = mid - 1;
            }
        }
        
        return nums[0]; // 理论上不会到达这里，但在循环未找到时返回
    }
}