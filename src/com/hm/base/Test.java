package com.hm.base;

/**
 * Created by dumingwei on 2017/7/11.
 */
public class Test {

    public static void main(String[] args) {
        Test test = new Test();

    }

    public Test() {
        System.out.println("构造函数");
        System.out.println(a);
    }

    private int a = 1;

    static {
        System.out.println("静态代码初始块");
    }

    {
        System.out.println("代码初始块");
        a = 2;
    }
}