package com.hm.base.interview.android;

/**
 * 1. 一个数的0次方=1
 * 数值的整数次方，可以使用快速幂算法
 */
public class Power {

    public static void main(String[] args) {
        // 测试用例
        System.out.println("2.0 ^ 10 = " + myPow(2.0, 10));    // 8.0
//        System.out.println("2.0 ^ -2 = " + myPow(2.0, -2));  // 0.25
//        System.out.println("3.0 ^ 0 = " + myPow(3.0, 0));    // 1.0
//        System.out.println("0.5 ^ 2 = " + myPow(0.5, 2));    // 0.25
    }

    public static double myPow(double base, int exponent) {
        // 处理特殊情况
        if (base == 0) {
            if (exponent <= 0) {
                throw new IllegalArgumentException("0 的负指数或 0 次方无意义");
            }
            return 0;
        }
        if (exponent == 0) {
            return 1.0;
        }

        // 将 n 转为 long 类型，避免负数溢出
        long exp = exponent; // 用 long 避免 int 溢出
        if (exponent < 0) {
            exp = -exp;
            base = 1 / base;
        }

        // 快速幂算法，按位处理指数
        double result = 1.0;
        String binaryString = Long.toBinaryString(exp);
        System.out.println("指数2进制的表示：" + binaryString);
        while (exp > 0) {
            // 如果当前指数最低位为 1，乘上当前 base
            if ((exp & 1) == 1) {
                result *= base;
            }
            // 底数平方，指数右移
            // 注意，这里base会变大
            base *= base;
            exp >>= 1;
        }

        return result;
    }


}