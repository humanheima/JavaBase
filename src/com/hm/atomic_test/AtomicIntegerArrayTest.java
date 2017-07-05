package com.hm.atomic_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * Created by dumingwei on 2017/7/5.
 * 原子更新数组里的某个元素
 */
public class AtomicIntegerArrayTest {

    private static int threadCount = 10;
    private static CountDownLatch countDown = new CountDownLatch(threadCount);
    private static int[] vales = new int[10];
    private static AtomicIntegerArray array = new AtomicIntegerArray(vales);


    public static void main(String[] args) {
        Thread[] threads = new Thread[threadCount];
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
        for (int i = 0; i < 10; i++) {
            System.out.print(array.get(i) + " ");
        }
        System.out.println();
        for (int i = 0; i < 10; i++) {
            System.out.print(vales[i] + " ");
        }
    }

    private static class Counter implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 100; i++) {
                for (int j = 0; j < 10; j++) {
                    //vales[j]++;
                    array.getAndIncrement(j);
                }
            }
            countDown.countDown();
        }
    }
}
