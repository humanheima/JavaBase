package com.hm.base.class_init;

/**
 * Crete by dumingwei on 2020-02-25
 * Desc:
 */
class HelloA {

    private static int a = 1;

    public HelloA() {
        System.out.println("A's constructor");
    }

    {
        System.out.println("A's code block");
    }

    static {
        System.out.println("A's static code block");
        System.out.println("A's static code block a = " + a);
    }
}


public class InheritLoad extends HelloA {

    private static int b = 1;

    public InheritLoad() {
        System.out.println("son constructor");
    }

    {
        System.out.println("son's code block");
    }

    static {
        System.out.println("son's static code block");
        System.out.println("son's static code block b = " + b);
    }

    public static void main(String[] args) {
        InheritLoad obj = new InheritLoad();
    }
}