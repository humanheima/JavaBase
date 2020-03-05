package com.hm.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Crete by dumingwei on 2020-03-04
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
public class LeetCode_1 {

    public static void main(String[] args) {
        int[] nums = {3, 2, 7, 11, 15};
        int target = 9;

        int[] result = twoSum(nums, target);

        System.out.println(result[0]);
        System.out.println(result[1]);

    }

    /**
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
     * 两遍hash算法
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] twoSum2(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (map.containsKey(complement) && map.get(complement) != i) {
                return new int[]{i, map.get(complement)};
            }
        }
        throw new IllegalArgumentException("No two sum solution");


    }

    /**
     * 一遍hash
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
