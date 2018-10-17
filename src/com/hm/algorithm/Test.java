package com.hm.algorithm;

public class Test {

    public static void main(String[] args) {

        String original = "123456";
        fun(original);

    }

    /**
     * @param original 原始的6位字符串
     */

    private static void fun(String original) {

        String front = original.substring(0, 3);
        String behind = original.substring(3, 6);
        System.out.println(front);
        System.out.println(behind);
    }


}
