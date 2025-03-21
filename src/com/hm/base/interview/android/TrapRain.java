package com.hm.base.interview.android;

/**
 * 接雨水.md
 */
public class TrapRain {

    // 测试代码
    public static void main(String[] args) {
        TrapRain trapRain = new TrapRain();
        //int[] height = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
        int[] height = {4, 2, 0, 3, 2, 3}; // 小数组测试用例
        System.out.println("接雨水量: " + trapRain.trap(height)); // 输出: 6
    }

    public int trap(int[] height) {
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0;                // 左指针
        int right = height.length - 1; // 右指针
        int leftMax = 0;             // 左边最大高度
        int rightMax = 0;            // 右边最大高度
        int water = 0;               // 总接水量

        while (left < right) {
            // 更新左右最大高度
            leftMax = Math.max(leftMax, height[left]);
            rightMax = Math.max(rightMax, height[right]);

            // 如果左边最大高度小于右边最大高度，处理左边
            if (leftMax < rightMax) {
                water += leftMax - height[left]; // 计算当前位置的水量
                left++;                          // 左指针右移
            } else {
                // 否则处理右边
                water += rightMax - height[right]; // 计算当前位置的水量
                right--;                          // 右指针左移
            }
        }

        return water;
    }


}