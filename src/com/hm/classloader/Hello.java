package com.hm.classloader;

/**
 * Created by dumingwei on 2018/5/17 0017.
 */
public class Hello {

    public static void main(String[] args) {
        for (String arg : args) {
            System.out.println("the arguments of running Hello:" + arg);
        }
    }
}
