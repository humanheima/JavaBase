package com.hm.generic.blog_demo;

/**
 * Crete by dumingwei on 2019-09-24
 * Desc:
 */

public class Circle extends Shape {

    static {
        System.out.println("静态代码块1");
    }

    {
        System.out.println("代码块1");
    }

    public Circle() {
        System.out.println("构造函数");
    }

    {
        System.out.println("代码块2");
    }

    static {
        System.out.println("静态代码块2");
    }

    @Override
    public void draw() {
        System.out.println("画一个圆形");
    }
}