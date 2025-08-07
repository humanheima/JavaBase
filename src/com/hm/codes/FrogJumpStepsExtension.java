package com.hm.codes;


/**
 * Crete by dumingwei on 2020-02-12
 * Desc: 一只青蛙一次可以跳上1级台阶，也可以跳上2级，也可以跳上3级台阶，……也可以跳上n级，那么青蛙跳上一个n级的台阶总共有多少种跳法？
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
        System.out.println(frogJumpRecursively(0));
        System.out.println(frogJumpRecursively(1));
        System.out.println(frogJumpRecursively(2));
        System.out.println(frogJumpRecursively(3));
        System.out.println(frogJumpRecursively(4));
        System.out.println(frogJumpRecursively(5));

        System.out.println(JumpFloorII(0));
        System.out.println(JumpFloorII(1));
        System.out.println(JumpFloorII(2));
        System.out.println(JumpFloorII(3));
        System.out.println(JumpFloorII(4));
        System.out.println(JumpFloorII(5));

    }

    /**
     * 递归实现
     *
     * @param n
     * @return
     */
    static int frogJumpRecursively(int n) {
        if (n == 0 || n == 1) return 1;
        return 2 * frogJumpRecursively(n - 1);
    }

    /**
     * 循环实现
     *
     * @param n
     * @return
     */
    public static int JumpFloorII(int n) {
        if (n == 0 || n == 1) return 1;
        int result = 1;
        while (n > 1) {
            result *= 2;
            n--;
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
