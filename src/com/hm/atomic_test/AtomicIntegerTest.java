package com.hm.atomic_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dumingwei on 2017/7/5.
 * 原子更新基本数据类型
 */
public class AtomicIntegerTest {

    private static int threadCount = 10;
    private static CountDownLatch countDown = new CountDownLatch(threadCount);
    private static AtomicInteger count = new AtomicInteger(0);//原子操作类
    //private static int count = 0;

    public static void main(String[] args) {
      /*  Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new Counter());
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
         System.out.println("count=" + count.get());*/
        //System.out.println("count=" + count);
        System.out.println(count.getAndDecrement());
        System.out.println(count.get());
    }

    private static class Counter implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                count.getAndAdd(1);
            }
            countDown.countDown();
        }
    }
}
