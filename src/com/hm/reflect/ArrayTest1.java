package com.hm.reflect;

import java.lang.reflect.Array;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class ArrayTest1 {

    public static void main(String[] args) {

        Object arr = Array.newInstance(String.class, 10);
        Array.set(arr, 5, "java");
        Array.set(arr, 6, "android");
        Object book1 = Array.get(arr, 5);
        Object book2 = Array.get(arr, 6);
        System.out.println(book1);
        System.out.println(book2);
    }
}
