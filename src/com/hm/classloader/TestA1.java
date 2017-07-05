package com.hm.classloader;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class TestA1 {

    public static void main(String[] args) {
        A a = new A();
        a.a++;
        System.out.println(a.a);
    }
}
