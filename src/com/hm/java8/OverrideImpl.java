package com.hm.java8;

import com.hm.java8.inter.DefaultInterface;

/**
 * Created by dumingwei on 2017/3/13.
 */
public class OverrideImpl implements DefaultInterface {
    @Override
    public String sayHello() {
        return "OverrideImpl hello world";
    }

    @Override
    public void sayGood() {
        System.out.println("OverrideImpl say hello");
    }
}
