package com.hm.base;

import java.util.ArrayDeque;

/**
 * Created by p_dmweidu on 2025/7/7
 * Desc: 测试ArrayDeque，以后就以 ArrayDequeTest 这个类为基准。
 */
public class ArrayDequeTest {

    public static void main(String[] args) {

        ArrayDeque<Integer> deque = new ArrayDeque<>(7);
        for (int i = 0; i < 8; i++) {
            deque.addFirst(i);
            //deque.addLast(i);
        }

        System.out.println(deque);
        //deque.removeFirstOccurrence(1);

        System.out.println(deque.pollFirst());
    }
}
