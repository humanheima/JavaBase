package com.hm.base.interview;

/**
 * Created by dumingwei on 2017/10/1.
 */
public class HelloTest {

    public static void main(String[] args) {
        A ab = new B();
        ab = new B();
    }
}

class A {

    static {
        System.out.println("1");
    }

    public A() {
        System.out.println("2");

    }
}

class B extends A {

    static {
        System.out.println("a");
    }

    public B() {
        System.out.println("B");
    }
}
