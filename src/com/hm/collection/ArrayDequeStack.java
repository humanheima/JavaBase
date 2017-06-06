package com.hm.collection;

import java.util.ArrayDeque;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class ArrayDequeStack {

    public static void main(String[] args) {
        ArrayDeque<String> stack = new ArrayDeque<>();
        stack.push("java");
        stack.push("java ee");
        stack.push("android");
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);
    }
}
