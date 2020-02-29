package com.hm.base.class_init;

public class Parent {


    private static int a = 1;
    private static int b = 2;


    private int c = 2;
    protected int d = 4;


    {
        System.out.println("parent 初始化代码块 c = " + c);
    }

    public Parent() {
        System.out.println("parent 构造函数 c = " + c);
        System.out.println("parent 构造函数 d = " + d);
        test();
    }

    public void test() {
        System.out.println("parent test c = " + c);
    }

}
