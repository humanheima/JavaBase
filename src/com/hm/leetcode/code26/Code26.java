package com.hm.leetcode.code26;

import java.util.Arrays;

/**
 * Create by dumingwei on 2026-06-08
 * Desc: LeetCode 26. 删除有序数组中的重复项
 * <p>
 * 给你一个 非严格递增排列 的数组 nums，请你 原地 删除重复出现的元素，
 * 使每个元素 只出现一次，返回删除后数组的新长度。元素的相对顺序应该保持一致。
 * 然后返回 nums 中唯一元素的个数 k。
 * <p>
 * 判题标准：假设返回了 k，那么 nums 的前 k 个元素应包含去重后的全部元素，
 * 且顺序与原数组一致；nums 在 k 之后的元素无所谓。
 * <p>
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：2, nums = [1,2,_]
 * <p>
 * 示例 2：
 * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
 * 输出：5, nums = [0,1,2,3,4,_,_,_,_,_]
 * <p>
 * 提示：1 <= nums.length <= 3*10^4；-100 <= nums[i] <= 100；nums 已按非严格递增排列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class Code26 {

    public static void main(String[] args) {
        test(new int[]{1, 1, 2}, 2);
        test(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4}, 5);
        test(new int[]{1}, 1);
        test(new int[]{1, 2, 3, 4, 5}, 5);
        test(new int[]{2, 2, 2, 2}, 1);
        test(new int[]{-100, -100, 0, 0, 100}, 3);
    }

    private static void test(int[] nums, int expected) {
        int[] copy = Arrays.copyOf(nums, nums.length);
        int k = new Code26().removeDuplicates(copy);
        boolean ok = (k == expected);
        System.out.printf("原数组=%-28s 新长度=%d 期望=%d 去重后前k项=%-20s %s%n",
                Arrays.toString(nums), k, expected,
                Arrays.toString(Arrays.copyOf(copy, k)), ok ? "✓" : "✗ FAIL");
    }

    /**
     * 快慢指针：slow 指向「已去重区间的末尾」，fast 向后扫描。
     * 当 nums[fast] != nums[slow] 时，说明遇到新值，slow 前移并写入。
     * 时间 O(n)，空间 O(1)。
     */
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int slow = 0; // [0..slow] 是已去重的部分
        for (int fast = 1; fast < nums.length; fast++) {
            if (nums[fast] != nums[slow]) {
                slow++;
                nums[slow] = nums[fast];
            }
        }
        return slow + 1; // 长度 = 末尾下标 + 1
    }
}
