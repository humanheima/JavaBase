package com.hm.anno;

/**
 * Created by dumingwei on 2017/7/28.
 */
public class Fruit {

    public void info() {
        System.out.println("水果的info方法");
    }

    @Deprecated
    public void deprecatedMethod() {
        System.out.println("水果的info方法");
    }

}
