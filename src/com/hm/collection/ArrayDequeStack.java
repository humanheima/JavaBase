package com.hm.collection;

import java.util.ArrayDeque;

/**
 * Created by dumingwei on 2017/6/6.
 * ArrayDeque 源码分析
 */
public class ArrayDequeStack {

    public static void main(String[] args) {
        ArrayDeque<Integer> deque = new ArrayDeque<>(7);

        for (int i = 0; i < 7; i++) {
            deque.addFirst(i);
        }
        deque.addLast(11);

       /* int a[] = {1, 2, 3, 4};
        int b[] = new int[8];
        System.arraycopy(a,0,b,0,4);
        System.arraycopy(a,0,b,4,0);
        for (int i : b) {
            System.out.print(i);
        }*/

      /*  stack.push("java");
        stack.push("java ee");
        stack.push("android");
        System.out.println(stack);
        System.out.println(stack.peek());
        System.out.println(stack);
        System.out.println(stack.pop());
        System.out.println(stack);*/
    }
}
