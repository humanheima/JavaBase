package com.hm.codes;

import java.util.*;

public class Test {


    public static void main(String[] args) {

    }


    /**
     * 两数之和
     * Desc: 给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回他们的数组下标。
     * 你可以假设每种输入只会对应一个答案。但是，你不能重复利用这个数组中同样的元素。
     */

    private int[] twoSum(int[] nums, int target) {
        int[] result = new int[2];
        if (nums == null || nums.length < 2) {
            return result;
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            //剩余的值
            int remainVal = target - val;
            if (map.containsKey(remainVal)) {
                result[0] = map.get(remainVal);
                result[1] = i;
                return result;
            } else {
                map.put(val, i);
            }
        }

        return result;
    }

    private List<List<Integer>> threeSums(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return result;
        }
        //先排序好
        Arrays.sort(nums);

        //1,2,3
        int length = nums.length;

        for (int i = 0; i < length - 2; i++) {
            int value = nums[i];
            int left = i + 1;
            int right = length - 1;
            while (left < right) {
                int sum = value + nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> item = new ArrayList<>(3);
                    item.add(i);
                    item.add(left);
                    item.add(right);
                    result.add(item);
                    //跳过重复的left
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    //跳过重复的right
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    //跳过重复的以后，还需要改游标值
                    left++;
                    right--;
                } else if (sum < 0) {
                    left++;
                } else {
                    right--;
                }


            }
        }

        return result;
    }
}
