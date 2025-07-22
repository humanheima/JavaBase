package com.hm.base.interview.android;

/**
 * Created by p_dmweidu on 2025/7/22
 * Desc: 在旋转数组中搜索
 */
public class RotatedArraySearch {

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        RotatedArraySearch rotatedArraySearch = new RotatedArraySearch();
        int result = rotatedArraySearch.search(nums, target); // 返回 4
        System.out.println(result);
    }
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        
        int left = 0;
        int right = nums.length - 1;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            
            if (nums[mid] == target) {
                return mid;
            }
            
            // 判断哪半边是有序的
            if (nums[left] <= nums[mid]) { // 左半边有序
                // 检查目标值是否在左半边范围内
                if (nums[left] <= target && target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else { // 右半边有序
                // 检查目标值是否在右半边范围内
                if (nums[mid] < target && target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        
        return -1; // 未找到
    }
}