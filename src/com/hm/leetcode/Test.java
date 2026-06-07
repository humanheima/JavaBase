package com.hm.leetcode;

import java.util.*;

public class Test {


    public static void main(String[] args) {

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        //进位
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int value1 = l1 == null ? 0 : l1.val;
            int value2 = l2 == null ? 0 : l2.val;

            int tempValue = value1 + value2 + carry;
            int nodeValue = tempValue % 10;
            ListNode listNode = new ListNode(nodeValue);
            carry = tempValue / 10;
            current.next = listNode;
            current = listNode;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        return dummy.next;


    }


    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> integerMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {

            //当前值
            int value = nums[i];
            int leftValue = target - nums[i];

            if (integerMap.containsKey(leftValue)) {
                return new int[]{integerMap.get(leftValue), i};
            }
            //没有的话，先存起来。
            integerMap.put(nums[i], i);
        }

        throw new IllegalStateException("no available numers");
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
