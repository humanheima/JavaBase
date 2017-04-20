package com.hm.java8interface;

import com.hm.java8interface.inter.DefaultInterface;

/**
 * Created by dumingwei on 2017/3/13.
 */
public class InterfaceTest {

    public static void main(String args[]) {
        DefaultInterface defaultInterface = DefaultFactory.create(DefaultImpl::new);
        DefaultInterface overrideInterface = DefaultFactory.create(OverrideImpl::new);
        System.out.println(defaultInterface.sayHello());
        System.out.println(overrideInterface.sayHello());
    }
}