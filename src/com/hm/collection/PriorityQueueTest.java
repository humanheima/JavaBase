package com.hm.collection;

import java.util.PriorityQueue;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class PriorityQueueTest {

    public static void main(String[] args) {

        PriorityQueue<Integer>queue=new PriorityQueue<>();
        queue.offer(6);
        queue.offer(-3);
        queue.offer(20);
        queue.offer(18);
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
