package com.hm.base.class_init;

/**
 * Crete by dumingwei on 2020-02-25
 * Desc:
 *
 */
public class InitialClass {

    // 静态变量
    public static String staticField = "staticField";

    // 普通变量
    public String field = "field";

    static {
        System.out.println(staticField);
        System.out.println("static block init");
    }

    {
        System.out.println(field);
        System.out.println("block init");
    }

    public InitialClass() {
        System.out.println("Constructor init");
    }

    public static void main(String[] args) {
        new InitialClass();
    }
}