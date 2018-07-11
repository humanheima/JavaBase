package com.hm.algorithm.cp_one.practice_1_1;

/**
 * Created by dumingwei on 2018/6/10 0010.
 */
public class Fibonacci {

    public static void main(String[] args) {
        for (int N = 0; N < 10; N++) {
            System.out.println(N+" "+F(N));
        }
    }

    private static long F(int N) {
        if (N == 0) {
            return 0;
        }
        if (N == 1) {
            return 1;
        }
        return F(N - 1) + F(N - 2);
    }
}
