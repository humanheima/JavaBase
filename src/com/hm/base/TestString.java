package com.hm.base;

/**
 * Created by dumingwei on 2017/10/16.
 */
public class TestString {

    public static void main(String[] args) {
        StringBuilder builder=new StringBuilder();
        builder.append("123");
        builder.append('\n');
        builder.append("1234");
        builder.append('\n');
        System.out.println(builder.toString());
    }
}
