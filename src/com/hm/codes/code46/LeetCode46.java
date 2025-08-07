package com.hm.codes.code46;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by dumingwei on 2022/4/12.AtomicIntegerTest
 * <p>
 * Desc:全排列
 * <p>
 * 题目链接：https://leetcode-cn.com/problems/permutations/
 * <p>
 * 解题思路：
 * 回溯法：一种通过探索所有可能的候选解来找出所有的解的算法。如果候选解被确认不是一个解（或者至少不是最后一个解），回溯算法会通过在上一步进行一些变化抛弃该解，即回溯并且再次尝试。
 * <p>
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
 * 来源：力扣（LeetCode）
 * 这题还是难以理解
 * todo 后面再看
 */
public class LeetCode46 {


    public static void main(String[] args) {
        //test2();
        test1();
    }

    private static void test1() {
        int[] array = {1, 2, 3};
        LeetCode46 leetCode46 = new LeetCode46();
        List<List<Integer>> result = leetCode46.permute(array);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }
    private static void test2() {
        int[] array = {1, 2, 3, 4};
        LeetCode46 leetCode46 = new LeetCode46();
        List<List<Integer>> result = leetCode46.permute(array);
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> output = new ArrayList<>();
        for (int num : nums) {
            output.add(num);
        }

        int n = nums.length;
        backtrack(n, output, result, 0);
        return result;
    }

    /**
     * 回溯算法
     *
     * @param n
     * @param output 当前排列
     * @param res
     * @param start
     */
    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int start) {
        // 所有数都填完了
        if (start == n) {
            res.add(new ArrayList<>(output));
        }
        for (int i = start; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, start, i);
            // 继续递归填下一个数
            backtrack(n, output, res, start + 1);
            // 撤销操作
            Collections.swap(output, start, i);
        }
    }
}
