package com.hm.leetcode.code27;

import java.util.Arrays;

/**
 * Create by dumingwei on 2026-06-08
 * Desc: LeetCode 27. 移除元素
 * <p>
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，
 * 并返回移除后数组的新长度 k。元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 判题标准：假设返回了 k，那么 nums 的前 k 个元素应包含所有不等于 val 的元素
 * （顺序不要求一致），nums 在 k 之后的元素无所谓。
 * <p>
 * 示例 1：
 * 输入：nums = [3,2,2,3], val = 3
 * 输出：2, nums = [2,2,_,_]
 * <p>
 * 示例 2：
 * 输入：nums = [0,1,2,2,3,0,4,2], val = 2
 * 输出：5, nums = [0,1,4,0,3,_,_,_]（顺序可不同）
 * <p>
 * 提示：0 <= nums.length <= 100；0 <= nums[i] <= 50；0 <= val <= 100。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-element/
 */
public class Code27 {

    public static void main(String[] args) {
        test(new int[]{3, 2, 2, 3}, 3, 2);
        test(new int[]{0, 1, 2, 2, 3, 0, 4, 2}, 2, 5);
        test(new int[]{}, 1, 0);
        test(new int[]{1, 1, 1}, 1, 0);
        test(new int[]{1, 2, 3}, 4, 3);
        test(new int[]{4, 5}, 5, 1);
    }

    private static void test(int[] nums, int val, int expected) {
        // 解法一：快慢指针（保序）
        int[] a = Arrays.copyOf(nums, nums.length);
        int k1 = new Code27().removeElement(a, val);
        // 解法二：对撞指针（不保序，但更少写入）
        int[] b = Arrays.copyOf(nums, nums.length);
        int k2 = new Code27().removeElementTwoEnds(b, val);

        boolean lenOk = (k1 == expected && k2 == expected);
        boolean contentOk = noTargetLeft(a, k1, val) && noTargetLeft(b, k2, val);
        System.out.printf("nums=%-26s val=%d 快慢:%d 对撞:%d 期望:%d 前k项[快慢]=%-18s %s%n",
                Arrays.toString(nums), val, k1, k2, expected,
                Arrays.toString(Arrays.copyOf(a, k1)),
                (lenOk && contentOk) ? "✓" : "✗ FAIL");
    }

    /** 校验前 k 项里不再含 val */
    private static boolean noTargetLeft(int[] nums, int k, int val) {
        for (int i = 0; i < k; i++) {
            if (nums[i] == val) {
                return false;
            }
        }
        return true;
    }

    /**
     * 解法一：快慢指针（保持相对顺序）
     * slow 指向下一个待写入位置，fast 扫描；遇到不等于 val 的元素就写入并 slow++。
     * 时间 O(n)，空间 O(1)。
     */
    public int removeElement(int[] nums, int val) {
        int slow = 0;
        for (int fast = 0; fast < nums.length; fast++) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
        }
        return slow;
    }

    /**
     * 解法二：对撞指针（不保证顺序，写入次数更少）
     * 当 nums[left] == val 时，用末尾元素覆盖它，再缩小右边界；否则 left 前移。
     * 适合「待删元素很少」的场景，避免大量挪动。
     * 时间 O(n)，空间 O(1)。
     */
    public int removeElementTwoEnds(int[] nums, int val) {
        int left = 0;
        int right = nums.length;
        while (left < right) {
            if (nums[left] == val) {
                nums[left] = nums[right - 1]; // 用末尾元素覆盖当前
                right--;                      // 末尾收缩（被覆盖位置后续再判）
            } else {
                left++;
            }
        }
        return left;
    }
}
