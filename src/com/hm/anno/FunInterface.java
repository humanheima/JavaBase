package com.hm.anno;

import java.lang.annotation.Target;

/**
 * Created by dumingwei on 2017/8/2.
 * 函数式接口
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
