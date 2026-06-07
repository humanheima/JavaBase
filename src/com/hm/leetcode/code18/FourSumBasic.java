package com.hm.leetcode.code18;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 18. 四数之和 —— 双指针「基础版」（不含任何剪枝优化）
 * <p>
 * 这是把 {@link Code18#fourSum2} 抽出来的纯净教学版：只保留「排序 + 固定两数 + 双指针 + 去重」
 * 这套最小骨架，去掉上/下界剪枝，便于先把核心逻辑看明白。正确性与剪枝版完全一致，
 * 只是没有提前退出的加速。
 * <p>
 * 链接：https://leetcode.cn/problems/4sum/
 */
public class FourSumBasic {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        // 1. 边界：不足 4 个数，不可能组成四元组
        if (nums == null || nums.length < 4) {
            return result;
        }

        // 2. 排序：让相等的数相邻，既方便去重，也是双指针的前提
        Arrays.sort(nums);
        int n = nums.length;

        // 3. 第一层：固定第一个数 nums[i]
        for (int i = 0; i < n - 3; i++) {
            // 去重 i：同一个值只让它当一次「第一个数」
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            // 4. 第二层：固定第二个数 nums[j]
            for (int j = i + 1; j < n - 2; j++) {
                // 去重 j：注意是 j > i + 1，而不是 j > 0；
                // 否则会把 j == i+1 这个合法起点也跳过
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }

                // 5. 第三、四个数用双指针在 [j+1, n-1] 区间里夹逼
                int left = j + 1;
                int right = n - 1;
                while (left < right) {
                    // 关键：用 long 求和，避免四个 ±10^9 相加溢出 int
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];

                    if (sum == target) {
                        // 命中：记录一个四元组
                        result.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // 去重 left：跳过右边所有相同的值
                        while (left < right && nums[left] == nums[left + 1]) {
                            left++;
                        }
                        // 去重 right：跳过左边所有相同的值
                        while (left < right && nums[right] == nums[right - 1]) {
                            right--;
                        }
                        // 两侧各走一步，继续找下一组
                        left++;
                        right--;
                    } else if (sum < target) {
                        // 和偏小，left 右移变大
                        left++;
                    } else {
                        // 和偏大，right 左移变小
                        right--;
                    }
                }
            }
        }

        return result;
    }

    // ===== 自测 =====
    public static void main(String[] args) {
        test(new int[]{1, 0, -1, 0, -2, 2}, 0, "[[-2, -1, 1, 2], [-2, 0, 0, 2], [-1, 0, 0, 1]]");
        test(new int[]{2, 2, 2, 2, 2}, 8, "[[2, 2, 2, 2]]");
        test(new int[]{0, 0, 0, 0}, 0, "[[0, 0, 0, 0]]");
        test(new int[]{1, 2, 3}, 0, "[]");
        // 溢出用例：四个 10^9 之和真实值 = 4×10^9，不等于 target，long 下应返回 []
        test(new int[]{1000000000, 1000000000, 1000000000, 1000000000}, -294967296, "[]");
    }

    private static void test(int[] nums, int target, String expected) {
        String got = new FourSumBasic().fourSum(nums.clone(), target).toString();
        boolean ok = got.equals(expected);
        System.out.printf("%-32s target=%-12d => %s  期望:%s  %s%n",
                Arrays.toString(nums), target, got, expected, ok ? "✓" : "✗ FAIL");
    }
}
