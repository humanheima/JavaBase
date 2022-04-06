package com.hm.leetcode;

/**
 * Crete by dumingwei on 2020-02-12
 * Desc: 青蛙跳台阶问题。一只青蛙一次可以跳1级台阶，也可以跳2级台阶。求该青蛙跳上一个级的台阶总共有多少种跳法。
 * <p>
 * f(1)=1
 * f(2)=2
 * f(3)=3
 * <p>
 * 问题分析
 * 设f(n)表示青蛙跳上n级台阶的跳法数。当只有一个台阶时，
 * 即n = 1时， 只有1中跳法；
 * 当n = 2时，有两种跳法；
 * 当n = 3 时，有3种跳法；
 * 当n很大时，青蛙在最后一步跳到第n级台阶时，有两种情况：
 * 一种是青蛙在第n-1个台阶跳一个台阶，那么青蛙完成前面n-1个台阶，就有f(n-1)种跳法，这是一个子问题。
 * 另一种是青蛙在第n-2个台阶跳两个台阶到第n个台阶，那么青蛙完成前面n-2个台阶，就有f(n-2)种情况，这又是另外一个子问题。
 * <p>
 * 两个子问题构成了最终问题的解，所以当n>=3时，青蛙就有f(n)=f(n-1)+f(n-2)种跳法。上面的分析过程，
 * 其实我们用到了动态规划的方法，找到了状态转移方程，用数学方程表达如下：
 * 参考根目录下的图片FromJUmpSteps.png
 * <p>
 * 参考链接：https://blog.csdn.net/xmc281141947/article/details/70738918
 */
public class FrogJumpSteps {

    /**
     * @param args
     */
    public static void main(String[] args) {

        System.out.println(frogJump(3));
        System.out.println(frogJump(4));
        System.out.println(frogJump(5));
        System.out.println(frogJump(6));

        System.out.println();

        System.out.println(frogJumpRecursively(3));
        System.out.println(frogJumpRecursively(4));
        System.out.println(frogJumpRecursively(5));
        System.out.println(frogJumpRecursively(6));
    }

    /**
     * 动态规划解法
     *
     * @param n 台阶数 n>=3
     * @return
     */
    static int frogJump(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1) {
            return 1;
        }
        if (n == 2) {
            return 2;
        }

        int preTwo = 1;
        int prepOne = 2;
        int jumpN = 0;
        for (int i = 3; i <= n; i++) {
            jumpN = preTwo + prepOne;
            preTwo = prepOne;
            prepOne = jumpN;
        }
        return jumpN;
    }

    /**
     * 递归解法
     *
     * @param n 台阶数
     * @return
     */
    static int frogJumpRecursively(int n) {
        if (n == 1) {
            return 1;
        } else if (n == 2) {
            return 2;
        } else {
            return frogJumpRecursively(n - 1) + frogJumpRecursively(n - 2);
        }

    }


}
