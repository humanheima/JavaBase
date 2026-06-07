package com.hm.leetcode.code11;

/**
 * Created by p_dmweidu on 2025/7/22
 * Desc:
 * https://leetcode.cn/problems/container-with-most-water/
 * <p>
 * 就是看[left, right]之间最大的体积
 * <p>
 * (right - left) * min(height[left], height[right])
 */
class LeetCode11 {

    public static void main(String[] args) {
        //int [] height = {1,8,6,2,5,4,8,3,7};
        int[] height = {1, 1};
        System.out.println(new LeetCode11().maxArea(height));
    }

    public int maxArea(int[] height) {
        int left = 0; // 左指针
        int right = height.length - 1; // 右指针
        int maxArea = 0; // 最大水量

        while (left < right) {
            // 计算当前容器的水量
            int width = right - left;
            int minHeight = Math.min(height[left], height[right]);
            int area = width * minHeight;

            // 更新最大水量
            maxArea = Math.max(maxArea, area);

            // 移动较短边的指针
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return maxArea;
    }

    /**
     * 这种写法时间超限了
     *
     * @param height
     * @return
     */
    public int maxAreaLongTime(int[] height) {
        int maxArea = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                maxArea = Math.max(maxArea, (j - i) * Math.min(height[i], height[j]));
            }
        }

        return maxArea;

    }


}