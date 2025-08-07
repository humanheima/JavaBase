package com.hm.codes.code18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by p_dmweidu on 2025/7/26
 * Desc: 四数之和
 */
public class Code18 {

    public static void main(String[] args) {
        // 测试用例
        int[][] testCases = {
                {1, 0, -1, 0, -2, 2}, // 测试用例1：常规情况
                {2, 2, 2, 2, 2},      // 测试用例2：重复元素
                {},                    // 测试用例3：空数组
                {0, 0, 0, 0},         // 测试用例4：全零
                {1, 2, 3},            // 测试用例5：数组长度不足
                {-5, -4, -3, -2, -1, 0, 1, 2, 3, 4, 5} // 测试用例6：包含正负数
        };
        int[] targets = {0, 8, 0, 0, 0, 0};

        Code18 solution = new Code18();

        for (int i = 0; i < testCases.length; i++) {
            System.out.println("Test Case " + (i + 1) + ": nums = " + Arrays.toString(testCases[i]) + ", target = " + targets[i]);
            List<List<Integer>> result = solution.fourSum(testCases[i], targets[i]);
            System.out.println("Result: " + result);
            System.out.println();
            List<List<Integer>> result2 = solution.fourSum2(testCases[i], targets[i]);
            System.out.println("Result2: " + result2);
            System.out.println();


        }
    }

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums); // 排序
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // 跳过重复的 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            // 剪枝：最小四数和 > target
            if ((long) nums[i] + nums[i + 1] + nums[i + 2] + nums[i + 3] > target) {
                break;
            }
            // 剪枝：最大四数和 < target
            if ((long) nums[i] + nums[n - 3] + nums[n - 2] + nums[n - 1] < target) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // 跳过重复的 nums[j]
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                // 剪枝：最小四数和 > target
                if ((long) nums[i] + nums[j] + nums[j + 1] + nums[j + 2] > target) {
                    break;
                }
                // 剪枝：最大四数和 < target
                if ((long) nums[i] + nums[j] + nums[n - 2] + nums[n - 1] < target) {
                    continue;
                }

                // 双指针
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 跳过重复的 nums[left]
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 跳过重复的 nums[right]
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }

    /**
     * 这种不用考虑剪枝算法，比较好理解
     * @param nums
     * @param target
     * @return
     */
    public List<List<Integer>> fourSum2(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 4) {
            return result;
        }

        Arrays.sort(nums); // 排序
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // 跳过重复的 nums[i]
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                // 跳过重复的 nums[j]
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 双指针
                int left = j + 1, right = n - 1;
                while (left < right) {
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 跳过重复的 nums[left]
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 跳过重复的 nums[right]
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        left++;
                        right--;
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }

        return result;
    }
}