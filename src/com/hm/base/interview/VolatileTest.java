package com.hm.base.interview;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dumingwei on 2017/9/28.
 * Desc：期待结果输出10000
 */
public class VolatileTest {

    //private volatile AtomicInteger inc = new AtomicInteger(0);
    private volatile int inc;

    private CountDownLatch countDownLatch = new CountDownLatch(10);

    /**
     * 无法实现
     */
    private void increase() {
        //inc.getAndIncrement();
        inc++;
    }


    /**
     * 可以实现
     */
    private synchronized void increaseSync() {
        inc++;
    }

    /**
     * 可以实现
     */

    private Lock lock = new ReentrantLock();

    private synchronized void increaseWithLock() {
        lock.lock();
        try {
            inc++;
        } finally {
            lock.unlock();
        }
    }

    private volatile AtomicInteger incAnother = new AtomicInteger(0);

    /**
     * 可以实现
     */
    private void increaseAtomic() {
        incAnother.getAndIncrement();
    }

    /**
     * 期待结果输出10000
     *
     * @param args
     */
    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest.increase();
                        //volatileTest.increaseWithLock();
                    }
                    volatileTest.countDownLatch.countDown();
                }
            }.start();
        }

        //保证前面的线程都执行完
        try {
            volatileTest.countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(volatileTest.inc);
    }
}
