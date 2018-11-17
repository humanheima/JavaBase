package com.hm.base.interview;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dumingwei on 2017/9/28.
 * 参考链接：{@see <a href="https://www.cnblogs.com/dolphin0520/p/3920373.html">Java并发编程：volatile关键字解析</a>}
 * 可见性只能保证每次读取的是最新的值
 * volatile 不能保证原子性
 * <p>
 * Desc：期待结果输出10000
 */
public class VolatileTest {

    //private volatile AtomicInteger inc = new AtomicInteger(0);
    private volatile int inc;

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
                        //volatileTest.increase();
                        volatileTest.increaseWithLock();
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
