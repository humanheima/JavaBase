package com.hm.string;

/**
 * Created by dumingwei on 2017/4/13.
 */
public class StringTest {

    public static void main(String[] args) {

        String s = "hello world hello world";
        s = s.replaceAll(" ", "_");
        System.out.println(s);
    }
}
