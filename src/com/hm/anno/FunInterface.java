package com.hm.anno;

/**
 * Created by dumingwei on 2017/8/2.
 */
@FunctionalInterface
public interface FunInterface {

    static void foo() {
        System.out.println("foo类方法");
    }

    default void bar() {
        System.out.println("bar默认方法");
    }

    void test();

}
