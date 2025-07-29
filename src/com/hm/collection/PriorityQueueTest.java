package com.hm.collection;

import java.util.PriorityQueue;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class PriorityQueueTest {

    public static void main(String[] args) {

        PriorityQueue<Integer>queue=new PriorityQueue<>();
        queue.offer(2);
        queue.offer(4);
        queue.offer(3);
        queue.offer(7);
        queue.offer(5);
        System.out.println(queue);
        System.out.println(queue.poll());
        System.out.println(queue);
    }
}
