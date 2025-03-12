package com.hm.base.interview.android;

import java.util.Arrays;

/**
 * 最长递增子序列
 */
public class LongestIncreasingSubsequence {

    // 测试代码
    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};

        // 测试动态规划方法
        int resultDP = lengthOfLIS_DP(nums);
        System.out.println("DP Method: Length of LIS = " + resultDP); // 预期输出：4

        // 测试贪心+二分查找方法
        //int resultGreedy = lengthOfLIS_Greedy(nums);
        //System.out.println("Greedy Method: Length of LIS = " + resultGreedy); // 预期输出：4
    }

    // 方法1：动态规划
    public static int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // 每个元素至少是长度为1的子序列
        int maxLength = 1;  // 记录全局最长长度
        
        for (int i = 1; i < n; i++) {
            //这里是小于i，也就是取i元素之前的所有元素
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLength = Math.max(maxLength, dp[i]);
        }
        return maxLength;
    }

    // 方法2：贪心 + 二分查找
    public static int lengthOfLIS_Greedy(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        
        int n = nums.length;
        int[] tails = new int[n]; // tails[i] 表示长度为 i+1 的子序列的最小结尾
        tails[0] = nums[0];
        int len = 1; // 当前最长子序列的长度
        
        for (int i = 1; i < n; i++) {
            if (nums[i] > tails[len - 1]) {
                // 如果当前元素大于 tails 的最后一个元素，直接追加
                tails[len] = nums[i];
                len++;
            } else {
                // 二分查找找到第一个大于等于 nums[i] 的位置
                int left = 0, right = len - 1;
                while (left <= right) {
                    int mid = left + (right - left) / 2;
                    if (tails[mid] >= nums[i]) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
                tails[left] = nums[i]; // 更新该位置
            }
        }
        return len;
    }

}