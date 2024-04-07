package com.hm.base.interview.sword_to_offer;

/**
 * Created by dmw on 2018/12/1.
 * Desc:实现函数double Power(double base, int exponent)，求base的exponent次方。不得使用库函数，同时不需要考虑大数问题。
 * <p>
 * 在数学中，一个数的 n 次方定义为将该数自乘 n 次。例如，2 的 3 次方（也写作 2^3）等于 2 * 2 * 2，结果是 8。
 * 如果 n 是负数，那么一个数的 n 次方定义为 1 除以该数的 |n| 次方。
 * 例如，2 的 -3 次方（也写作 2^-3）等于 1 / (2 * 2 * 2)，结果是 1/8。
 * 如果 n 是 0，任何数（除了 0）的 0 次方都定义为 1。0 的 0 次方在数学中是未定义的。
 */
public class Test16 {

    public static void main(String[] args) {

        System.out.println(power(3, 3) + " 对比 Math.pow(3, 3) =  " + Math.pow(3, 3));
        System.out.println(power(0, 3) + " 对比 Math.pow(0, 3) =  " + Math.pow(0, 3));
        System.out.println(power(0, 0) + " 对比 Math.pow(0, 0) =  " + Math.pow(0, 0));
        System.out.println(power(0, 1) + " 对比 Math.pow(0, 1) =  " + Math.pow(0, 1));
        System.out.println(power(2, -2) + " 对比 Math.pow(2, -2) =  " + Math.pow(2, -2));
        System.out.println(power(-2, -2) + " 对比 Math.pow(-2, -2) =  " + Math.pow(-2, -2));
        System.out.println(power(-2, -3) + " 对比 Math.pow(-2, -3) =  " + Math.pow(-2, -3));
    }

    /**
     * @param base
     * @param exponent 指数
     * @return
     */
    public static double power(double base, int exponent) {
        if (base == 0 && exponent < 0) {
            throw new IllegalArgumentException("Invalid input,base is zero and exponent less than zero.");
        }
        //指数为0,就返回1.
        if (exponent == 0) {
            return 1;
        }
        //求指数的绝对值
        long exp = exponent;
        if (exponent < 0) {
            exp = -exp;
        }
        double result = powerWithUnsignedExponent(base, exp);
        if (exponent < 0) {
            result = 1 / result;
        }
        return result;
    }

    public static double power2(double x, int n) {
        if (x == 0 && n < 0) {
            throw new IllegalArgumentException("Invalid input,x is zero and n less than zero.");
        }
        //指数为0,就返回1.
        if (n == 0) {
            return 1;
        }
        //求指数的绝对值
        long exp = n;
        if (n < 0) {
            exp = -exp;
        }
        double result = powerWithUnsignedExponent2(x, exp);
        if (n < 0) {
            result = 1 / result;
        }
        return result;
    }

    private static double powerWithUnsignedExponent2(double base, long exp) {
        //方法1 效率高
        if (exp == 0) {
            return 1;
        }
        if (exp == 1) {
            return base;
        }
        double result = powerWithUnsignedExponent2(base, exp >> 1);
        result *= result;
        //如果指数是奇数，就还要乘以一次底数。
        if (exp % 2 != 0) {
            result *= base;
        }
        return result;
//        //方法2 效率低
//        double result = 1.0;
//        for (int i = 1; i <= exp; i++) {
//            result *= base;
//        }
//        return result;
    }

    /**
     * 求一个数的整数次幂，不考虑溢出
     *
     * @param base
     * @param exp
     * @return
     */
    private static double powerWithUnsignedExponent(double base, long exp) {
        //方法1 效率高
        if (exp == 0) {
            return 1;
        }
        if (exp == 1) {
            return base;
        }
        double result = powerWithUnsignedExponent(base, exp >> 1);
        result *= result;
        //如果指数是奇数，就还要乘以一次底数。
        if (exp % 2 != 0) {
            result *= base;
        }
        return result;

        //方法2 效率低

       /* double result = 1.0;
        for (int i = 1; i <= exp; i++) {
            result *= base;
        }
        return result;*/

    }
}
