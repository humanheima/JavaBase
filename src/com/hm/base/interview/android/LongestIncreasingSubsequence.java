package com.hm.base.interview.android;

import java.util.Arrays;

/**
 * 算法解题思路/最长递增子序列.md
 * review 使用动态规划算法
 */
public class LongestIncreasingSubsequence {

    // 测试代码
    public static void main(String[] args) {

        int[] nums = {10, 9, 2, 5, 3, 7, 101, 8};

        // 测试动态规划方法
        int resultDP = lengthOfLIS_DP(nums);
        System.out.println("DP Method: Length of LIS = " + resultDP); // 预期输出：4

//        int[] findLIS = findLIS(nums);
//        for (int findLI : findLIS) {
//            System.out.print(findLI + " ");
//        }

//        System.out.println();
//        System.out.println(handWrite(nums));
        // 测试贪心+二分查找方法
        //int resultGreedy = lengthOfLIS_Greedy(nums);
        //System.out.println("Greedy Method: Length of LIS = " + resultGreedy); // 预期输出：4
    }

    // 方法1：动态规划 Dynamic Programming
    public static int lengthOfLIS_DP(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

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


    /**
     * 返回最长递增子序列
     *
     * @param nums
     * @return
     */
    public static int[] findLIS(int[] nums) {
        if (nums == null || nums.length == 0) return new int[0];

        int n = nums.length;
        int[] dp = new int[n]; // dp[i] 表示以 nums[i] 结尾的 LIS 长度
        int[] prev = new int[n]; // 记录前驱索引
        int maxLen = 1; // LIS 最大长度
        int maxIndex = 0; // LIS 结束的索引

        // 初始化
        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        // 动态规划
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j] && dp[j] + 1 > dp[i]) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] > maxLen) {
                maxLen = dp[i];
                maxIndex = i;
            }
        }

        // 回溯构造 LIS
        int[] lis = new int[maxLen];
        int index = maxIndex;
        for (int i = maxLen - 1; i >= 0; i--) {
            lis[i] = nums[index];
            index = prev[index];
        }

        return lis;
    }

    private static int handWrite(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int maxLength = 1;
        for (int i = 1; i < n; i++) {
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