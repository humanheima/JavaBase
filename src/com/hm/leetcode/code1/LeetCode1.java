package com.hm.leetcode.code1;

import java.util.HashMap;
import java.util.Map;

/**
 * Crete by dumingwei on 2020-03-04
 * 两数之和
 * Desc: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
 * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
 * <p>
 * 解体思路：
 * 注意：是返回两个整数的数组下标
 * 1 需要两层循环，外层循环从0到数组长度减去1，[0,arr.length-1)，左闭右开。遍历过程中将数组元素用arr[i]表示。
 * 2 内循环从1到数组长度，[1,arr.length)，左闭右开。遍历过程中将数组元素用arr[j]表示。
 * 3. 如果在遍历过程中 nums[i] + nums[j] == target 那么i和j就是我们要找的下标。
 * <p>
 * 链接：https://leetcode-cn.com/problems/two-sum/solution/liang-shu-zhi-he-by-leetcode-2/
 */
public class LeetCode1 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);

        System.out.println(result[0]);
        System.out.println(result[1]);

    }

    /**
     * 方法一：暴力枚举（Brute Force）
     * 思路
     * 双重循环遍历所有可能的元素组合，检查它们的和是否等于 target。
     * <p>
     * 时间复杂度：O(n²)
     * <p>
     * 空间复杂度：O(1)
     *
     * @param nums   输入
     * @param target
     * @return
     */
    public static int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];

        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }

        throw new IllegalArgumentException("No two sum solution");
    }


    /**
     * 方法二：哈希表优化（最优解）
     * 思路
     * 使用哈希表（Map）存储已遍历过的元素及其索引。
     *
     * 对于当前元素 nums[i]，计算差值 complement = target - nums[i]。
     *
     * 若 complement 存在于哈希表中，则找到解；否则将当前元素存入哈希表。
     *
     * 时间复杂度：O(n)（只需一次遍历）
     *
     * 空间复杂度：O(n)（哈希表的额外空间）
     *
     * 关键点
     * 哈希表的键为元素值，值为索引，通过快速查找 complement 是否存在。
     *
     * 边遍历边存储，避免重复元素的干扰（例如 nums = [3, 3], target = 6）。
     map.containsValue()
     *
     * @param nums
     * @param target
     * @return
     */
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement)) {
                return new int[]{map.get(complement), i};
            }
            //存储的value是下标
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }


}
