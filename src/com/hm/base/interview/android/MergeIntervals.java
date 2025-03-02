package com.hm.base.interview.android;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * 题目描述
 * 给定一个区间的集合（通常以二维数组形式表示），请合并所有重叠的区间。
 * 例如：
 * 输入: [[1,3],[2,6],[8,10],[15,18]]
 * 输出: [[1,6],[8,10],[15,18]]
 * <p>
 * 解释：区间 [1,3] 和 [2,6] 重叠，将它们合并为 [1,6]。
 * <p>
 * 输入: intervals = [[1,4],[4,5]]
 * 输出: [[1,5]]
 * 解释: 边界相接的区间 [1,4] 和 [4,5] 也需要合并。
 */
public class MergeIntervals {

    public static void main(String[] args) {
        MergeIntervals solution = new MergeIntervals();
        //test1(solution);
        test2(solution);
    }

    private static void test1(MergeIntervals solution) {
        int[][] intervals = {{1, 3}, {2, 6}, {8, 10}, {15, 18}};
        int[][] merged = solution.merge(intervals);

        for (int[] interval : merged) {
            System.out.println("[" + interval[0] + "," + interval[1] + "]");
        }
    }

    private static void test2(MergeIntervals solution) {
        int[][] intervals = {{1, 4}, {4, 5}};
        int[][] merged = solution.merge(intervals);

        for (int[] interval : merged) {
            System.out.println("[" + interval[0] + "," + interval[1] + "]");
        }
    }

    public int[][] merge(int[][] intervals) {
        // 边界检查
        if (intervals == null || intervals.length <= 1) {
            return intervals;
        }

        // 按起始点排序
        //Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 使用 List 存储结果
        List<int[]> result = new ArrayList<>();
        result.add(intervals[0]); // 先加入第一个区间

        // 遍历所有区间
        for (int i = 1; i < intervals.length; i++) {
            int[] current = intervals[i];
            //当前列表最后一个区间
            int[] last = result.get(result.size() - 1);

            // 判断是否重叠
            if (current[0] <= last[1]) {
                // 更新结束点为两者最大值，取两者之间的最大值
                last[1] = Math.max(last[1], current[1]);
            } else {
                // 不重叠，直接添加当前区间
                result.add(current);
            }
        }

        // 转换为数组返回
        return result.toArray(new int[result.size()][]);
    }


}