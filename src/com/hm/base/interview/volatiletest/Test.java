package com.hm.base.interview.volatiletest;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by dumingwei on 2017/9/28.
 */
public class Test {

    private volatile AtomicInteger inc = new AtomicInteger(0);

    private void increase() {
        inc.getAndIncrement();
    }

    public static void main(String[] args) {

        final Test test = new Test();
        for (int i = 0; i < 10; i++) {
            new Thread() {
                @Override
                public void run() {
                    for (int j = 0; j < 1000; j++) {
                        test.increase();
                    }
                }
            }.start();
        }

        //保证前面的线程都执行完
        while (Thread.activeCount() > 1) {
            Thread.yield();
            System.out.println(test.inc);
        }

    }
}
