package com.hm.leetcode.code42;

import java.util.Deque;
import java.util.LinkedList;

/**
 * Crete by dumingwei on 2026-06-14
 * 42. 接雨水
 * Desc: 给定 n 个非负整数表示每个宽度为 1 的柱子的高度图，计算按此排列的柱子，下雨之后能接多少雨水。
 * <p>
 * 解题思路：
 * 每个位置 i 能接的水量 = min(左侧最高, 右侧最高) - height[i]（小于 0 则接不到水）。
 * 1. 暴力：对每个 i 现场向两边扫出左右最高，O(n²)。
 * 2. 动态规划：预处理 leftMax[]、rightMax[] 两个数组，O(n) 时间 O(n) 空间。
 * 3. 双指针：用两个指针从两端向中间收缩，O(n) 时间 O(1) 空间（最优）。
 * 4. 单调栈：维护递减栈，遇到更高柱子时按层结算积水，O(n)。
 * <p>
 * 链接：https://leetcode.cn/problems/trapping-rain-water/
 */
public class LeetCode42 {

    public static void main(String[] args) {
        int[][] cases = {
                {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1}, // 期望 6
                {4, 2, 0, 3, 2, 5},                   // 期望 9
                {},                                   // 期望 0
                {5},                                  // 期望 0
                {3, 3, 3},                            // 期望 0
        };
        for (int[] h : cases) {
            System.out.printf("暴力=%d 动规=%d 双指针=%d 单调栈=%d%n",
                    trapBruteForce(h), trapDp(h), trapTwoPointers(h), trapStack(h));
        }
    }

    /**
     * 解法一：暴力枚举
     * 对每个位置向左、向右各扫一遍求两侧最高，取较小者减去当前高度。
     * 时间复杂度：O(n²)
     * 空间复杂度：O(1)
     */
    public static int trapBruteForce(int[] height) {
        int total = 0;
        for (int i = 1; i < height.length - 1; i++) {
            int leftMax = 0, rightMax = 0;
            for (int l = i; l >= 0; l--) {
                leftMax = Math.max(leftMax, height[l]);
            }
            for (int r = i; r < height.length; r++) {
                rightMax = Math.max(rightMax, height[r]);
            }
            total += Math.min(leftMax, rightMax) - height[i];
        }
        return total;
    }

    /**
     * 解法二：动态规划（空间换时间）
     * 预处理 leftMax[i]、rightMax[i]，再一次遍历累加每格水量。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int trapDp(int[] height) {
        int n = height.length;
        if (n == 0) {
            return 0;
        }
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }
        int total = 0;
        for (int i = 0; i < n; i++) {
            total += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return total;
    }

    /**
     * 解法三：双指针（最优）
     * 左右指针向中间收缩，谁那侧的「已知最高」更矮，谁就能确定当前格的水量。
     * 时间复杂度：O(n)
     * 空间复杂度：O(1)
     */
    public static int trapTwoPointers(int[] height) {
        int left = 0;
        int right = height.length - 1;
        int leftMax = 0, rightMax = 0, total = 0;
        while (left < right) {
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);
            // 哪边的最高值更小，哪边的当前格水量就已确定（受较小一侧约束）
            if (leftMax < rightMax) {
                total += leftMax - height[left];
                left++;
            } else {
                total += rightMax - height[right];
                right--;
            }
        }
        return total;
    }

    /**
     * 解法四：单调栈
     * 维护一个高度递减的下标栈，遇到比栈顶高的柱子时，
     * 弹出栈顶作为「凹槽底」，用左右两边界按层结算积水。
     * 时间复杂度：O(n)
     * 空间复杂度：O(n)
     */
    public static int trapStack(int[] height) {
        Deque<Integer> stack = new LinkedList<>();
        int total = 0;
        for (int i = 0; i < height.length; i++) {
            while (!stack.isEmpty() && height[i] > height[stack.peek()]) {
                int bottom = stack.pop();
                if (stack.isEmpty()) {
                    break;
                }
                int left = stack.peek();
                int width = i - left - 1;
                int boundedHeight = Math.min(height[left], height[i]) - height[bottom];
                total += width * boundedHeight;
            }
            stack.push(i);
        }
        return total;
    }

}
