package com.hm.codes.code539;

import java.time.LocalTime;
import java.util.Arrays;

/**
 * Created by p_dmweidu on 2025/8/7
 * Desc:24小时制，最小的时间差，以分钟的形式返回
 */
public class MinTimeDifference {

    // 测试代码
    public static void main(String[] args) {
        MinTimeDifference solution = new MinTimeDifference();

        // 测试用例 1：普通情况
        String[] test0 = {"23:59", "00:01", "12:34"};
        System.out.println("Test 0: " + solution.findMinDifference(test0) + " (Expected: 2)");


        // 测试用例 1：普通情况
        String[] test1 = {"23:59", "00:00", "12:34"};
        System.out.println("Test 1: " + solution.findMinDifference(test1) + " (Expected: 1)");


        // 测试用例 2：多个时间点，较小差值
        String[] test2 = {"01:00", "02:00", "03:00"};
        System.out.println("Test 2: " + solution.findMinDifference(test2) + " (Expected: 60)");

        // 测试用例 3：相同时间点
        String[] test3 = {"12:00", "12:00", "12:00"};
        System.out.println("Test 3: " + solution.findMinDifference(test3) + " (Expected: 0)");

        // 测试用例 4：跨天情况
        String[] test4 = {"00:00", "23:00"};
        System.out.println("Test 4: " + solution.findMinDifference(test4) + " (Expected: 60)");

        // 测试用例 5：单一时间
        String[] test5 = {"12:00"};
        System.out.println("Test 5: " + solution.findMinDifference(test5) + " (Expected: 0)");

        // 测试用例 6：密集时间点
        String[] test6 = {"00:00", "00:01", "00:02", "23:58", "23:59"};
        System.out.println("Test 6: " + solution.findMinDifference(test6) + " (Expected: 1)");

        // 测试用例 7：空数组
        String[] test7 = {};
        System.out.println("Test 7: " + solution.findMinDifference(test7) + " (Expected: 0)");

    }

    public int findMinDifference(String[] timePoints) {
        if (timePoints == null || timePoints.length < 2) {
            return 0;
        }

        // 将时间字符串转换为分钟数并排序
        int[] minutes = new int[timePoints.length];
        for (int i = 0; i < timePoints.length; i++) {
            LocalTime time = LocalTime.parse(timePoints[i]);
            minutes[i] = time.getHour() * 60 + time.getMinute();
        }
        //一定要先排序
        Arrays.sort(minutes);

        // 计算相邻时间的最小差值
        int minDiff = Integer.MAX_VALUE;
        for (int i = 1; i < minutes.length; i++) {
            int diff = minutes[i] - minutes[i - 1];
            minDiff = Math.min(minDiff, diff);
        }

        // 考虑跨天的情况（首尾时间差）
        int crossDayDiff = (1440 + minutes[0]) - minutes[minutes.length - 1];
        minDiff = Math.min(minDiff, crossDayDiff);

        return minDiff;
    }


}