package com.hm.base.interview.android;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 滑动窗口最大值
 */
public class SlidingWindowMax {

    public static void main(String[] args) {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        //result 里面存放的就是最大值
        int[] result = maxSlidingWindow(nums, k);
        for (int max : result) {
            System.out.print(max + " ");
        }
        // 输出: 3 3 5 5 6 7
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1]; // 结果数组的大小
        Deque<Integer> deque = new ArrayDeque<>(); // 双端队列，存储索引

        for (int i = 0; i < n; i++) {
            // 移除超出窗口范围的队首元素
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 移除队列中所有比当前元素小的元素
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 将当前元素索引加入队列，加入的是索引
            deque.offerLast(i);

            // 当窗口大小达到 k 时，开始记录最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

}