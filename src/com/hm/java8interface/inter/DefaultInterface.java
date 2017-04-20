package com.hm.java8interface.inter;

/**
 * Created by dumingwei on 2017/3/13.
 * 接口中带有默认方法的接口
 */
public interface DefaultInterface {

    default String sayHello() {
        return "hello world";
    }

    void sayGood();
}
