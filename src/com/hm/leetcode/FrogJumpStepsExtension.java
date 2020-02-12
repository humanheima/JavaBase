package com.hm.leetcode;


/**
 * Crete by dumingwei on 2020-02-12
 * Desc: 一只青蛙一次可以跳上1级台阶，也可以跳上2 级……它也可以跳上n 级，此时该青蛙跳上一个n级的台阶总共有多少种跳法？（拓展问题）
 * <p>
 * 感觉这个扩展问题并不好，关于f(0)的问题
 *
 * <p>
 * 问题分析参考链接
 * <p>
 * https://blog.csdn.net/bin_ge_love/article/details/52415363
 * https://blog.csdn.net/xmc281141947/article/details/70738918
 * https://blog.csdn.net/Artprog/article/details/67049383
 * https://www.jianshu.com/p/dca673b44f91
 */

public class FrogJumpStepsExtension {


    public static void main(String[] args) {
        System.out.println(jumpFloorII(5));
        System.out.println(frogJumpFloorII(5));
        System.out.println(frogJumpFloor(5));
        System.out.println(JumpFloorIIAnother(5));

        System.out.println(jumpFloorII(0));
        System.out.println(frogJumpFloorII(0));
        System.out.println(frogJumpFloor(0));
        System.out.println(JumpFloorIIAnother(0));

    }

    public static int jumpFloorII(int n) {//递归方法
        if (n == 0 || n == 1) {
            return 1;
        } else {
            return 2 * jumpFloorII(n - 1);
        }
    }

    static int frogJumpFloorII(int n) {//递归方法
        if (n == 0 || n == 1) return 1;
        return 2 * frogJumpFloorII(n - 1);
    }

    public int JumpFloorII(int n) {//迭代方法
        int result = 1;
        while (--n != 0) {
            result *= 2;
        }
        return result;
    }

    static int frogJumpFloor(int n) {//迭代方法
        int result = 1;
        if (n == 1) {
            return result;
        } else {
            for (int i = 2; i <= n; i++)
                result = 2 * result;
            return result;
        }
    }

    /**
     * 最终计算结果等价于2的n-1次方
     *
     * @param n
     * @return
     */
    public static int JumpFloorIIAnother(int n) {
        if (n == 0 || n == 1) return 1;
        return 1 << (n - 1);
    }

}
