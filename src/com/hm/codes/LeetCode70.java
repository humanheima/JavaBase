package com.hm.codes;

/**
 * Created by p_dmweidu on 2025/4/4
 * Desc: LeetCode70，爬楼梯 https://leetcode.cn/problems/climbing-stairs/
 */
class LeetCode70 {

    public static void main(String[] args) {

        LeetCode70 solution = new LeetCode70();
        System.out.println(solution.climbStairs(4));
    }
    /**
     * LeetCode70，爬楼梯问题 1 <= n <= 45
     *
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n <= 2) {
            return n;
        }

        int preTwo = 1;
        int prepOne = 2;
        int jumpN = 0;
        for (int i = 3; i <= n; i++) {
            jumpN = (preTwo + prepOne);
            preTwo = prepOne;
            prepOne = jumpN;
        }
        return jumpN;

    }
}