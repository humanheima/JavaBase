package com.hm.leetcode.code15;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dumingwei on 2022/4/4.
 * <p>
 * Desc:15. 三数之和
 * <p>
 * <p>
 * <p>
 * 解题思路:参考官方的解题思路即可，自己没做出来。
 * https://leetcode-cn.com/problems/3sum/solution/san-shu-zhi-he-by-leetcode-solution/
 */
public class LeetCode15 {

    public static void main(String[] args) {


        LeetCode15 leetCode15 = new LeetCode15();
        leetCode15.test1();
        //leetCode15.test2();
        //leetCode15.test3();
        leetCode15.test4();
    }

    private void test1() {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> listList = threeSum(nums);
        if (listList != null && !listList.isEmpty()) {
            for (List<Integer> integers : listList) {

                System.out.print(integers + " ; ");
            }
        }
        System.out.println();
    }

    private void test2() {
        int[] nums = {};
        List<List<Integer>> listList = threeSum(nums);
        if (listList != null) {
            for (List<Integer> integers : listList) {
                System.out.println(integers);
            }
        }
        System.out.println();

    }

    private void test3() {
        int[] nums = {0};
        List<List<Integer>> listList = threeSum(nums);
        if (listList != null) {
            for (List<Integer> integers : listList) {
                System.out.println(integers);
            }
        }
        System.out.println();

    }

    private void test4() {
        int[] nums = {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};
        List<List<Integer>> listList = threeSum(nums);
        if (listList != null) {
            for (List<Integer> integers : listList) {
                System.out.print(integers + " ; ");
            }
        }
    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> listArrayList = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return listArrayList;
        }

        int length = nums.length;

        Arrays.sort(nums);
        System.out.println();

        for (int first = 0; first <= length - 3; first++) {
            // 需要和上一次枚举的数不相同
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int third = length - 1;
            /**
             * nums[first] + nums[second] + nums[third] == 0
             * nums[second] + nums[third] == -nums[first]
             */

            for (int second = first + 1; second <= length - 2; second++) {
                // 需要和上一次枚举的数不相同
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                while (second < third && nums[first] + nums[second] + nums[third] > 0) {
                    third--;
                }
                if (third == second) {
                    break;
                }
                if (nums[first] + nums[second] + nums[third] == 0) {
                    List<Integer> foundList = new ArrayList<>(3);
                    foundList.add(nums[first]);
                    foundList.add(nums[second]);
                    foundList.add(nums[third]);
                    //System.out.println("in method = " + foundList);
                    listArrayList.add(foundList);
                }

            }
        }

        return listArrayList;
    }


}
