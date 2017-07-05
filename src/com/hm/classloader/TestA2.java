package com.hm.classloader;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class TestA2 {

    static {
        b = 6;
    }

    private static int b = 9;

    public static void main(String[] args) {
        System.out.println(b);
    }
}
