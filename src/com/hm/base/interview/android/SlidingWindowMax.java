package com.hm.base.interview.android;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 解题思路/滑动窗口最大值.md
 * 自己写的话，还是使用循环吧。使用滑动窗口写起来有点困难。
 */
public class SlidingWindowMax {

    public static void main(String[] args) {

//        Deque<Integer> deque = new ArrayDeque<>(); // 双端队列，存储索引
//
//        deque.offerLast(2);
//        deque.offerLast(3);
//        deque.offerLast(7);
//
//        System.out.println(deque.peekFirst());
        test1();
    }

    private static void test1() {
        int[] nums = {1, 3, -1, -3, 5, 3, 6, 7};
        int k = 3;
        //result 里面存放的就是最大值
        int[] result = maxSlidingWindow(nums, k);
        for (int max : result) {
            System.out.print(max + " ");
        }
        // 输出: 3 3 5 5 6 7
    }

    /**
     * 手写的话，可以使用这种循环算法
     *
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow2(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1];

        // 遍历每个窗口
        for (int i = 0; i <= n - k; i++) {
            // 寻找当前窗口 [i, i+k-1] 的最大值
            int max = nums[i];
            for (int j = i; j < i + k; j++) {
                max = Math.max(max, nums[j]);
            }
            result[i] = max;
        }

        return result;
    }


    public static int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k <= 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result = new int[n - k + 1]; // 结果数组的大小
        Deque<Integer> deque = new ArrayDeque<>(); // 双端队列，存储索引

        for (int i = 0; i < n; i++) {
            // deque里面放的是索引，移除超出窗口范围的队首元素索引，长度最大是3
            while (!deque.isEmpty() && deque.peekFirst() < i - k + 1) {
                deque.pollFirst();
            }

            // 移除队列中所有比当前元素小的元素索引
            while (!deque.isEmpty() && nums[deque.peekLast()] < nums[i]) {
                deque.pollLast();
            }

            // 将当前元素 **索引** 加入队列尾部，加入的是索引
            deque.offerLast(i);

            // 当窗口大小达到 k 时，开始记录最大值
            if (i >= k - 1) {
                result[i - k + 1] = nums[deque.peekFirst()];
            }
        }

        return result;
    }

}