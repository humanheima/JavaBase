package com.hm.base.interview;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dumingwei on 2017/9/28.
 */
public class VolatileTest {

    //private volatile AtomicInteger inc = new AtomicInteger(0);
    private int inc;

    private void increase() {
        //inc.getAndIncrement();
        inc++;
    }

    public static void main(String[] args) {
        final VolatileTest volatileTest = new VolatileTest();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        volatileTest.increase();
                    }
                }
            }.start();
        }

        //保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
            System.out.println(volatileTest.inc);
        }
    }
}
