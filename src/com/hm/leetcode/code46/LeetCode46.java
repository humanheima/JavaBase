package com.hm.leetcode.code46;

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
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 这题还是难以理解
 */
public class LeetCode46 {


    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4};
        LeetCode46 leetCode46 = new LeetCode46();
        leetCode46.permute(array);
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
     * @param first
     */
    public void backtrack(int n, List<Integer> output, List<List<Integer>> res, int first) {
        // 所有数都填完了
        if (first == n) {
            res.add(new ArrayList<>(output));
        }
        for (int i = first; i < n; i++) {
            // 动态维护数组
            Collections.swap(output, first, i);
            // 继续递归填下一个数
            backtrack(n, output, res, first + 1);
            // 撤销操作
            Collections.swap(output, first, i);
        }
    }
}
