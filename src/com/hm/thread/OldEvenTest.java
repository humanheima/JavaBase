package com.hm.thread;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Crete by dumingwei on 2019-07-23
 * Desc: 两个线程交替打印奇数和偶数
 */
public class OldEvenTest {

    public static void main(String[] args) {

        //method1();
        //method2();

        method3();
    }


    private static volatile Boolean flag = true;

    private static AtomicInteger num = new AtomicInteger();

    private static final Integer TOTAL = 100;

    private static void method3() {

        Thread jsThread = new Thread(new Runnable() {

            @Override
            public void run() {
                while (num.get() <= TOTAL - 1) {
                    if (!flag) {
                        System.out.println(Thread.currentThread().getName() + ": " + num.getAndIncrement());
                        flag = true;
                    }
                }
            }
        });

        jsThread.setName("奇数线程");

        Thread osThread = new Thread(new Runnable() {

            @Override
            public void run() {

                while (num.get() <= TOTAL) {
                    if (flag) {
                        System.out.println(Thread.currentThread().getName() + ":" + num.getAndIncrement());
                        flag = false;
                    }
                }
            }
        });

        osThread.setName("偶数线程");

        osThread.start();
        jsThread.start();

    }

    private static int count = 0;

    private static void method2() {
        Lock lock = new ReentrantLock();
        Condition evenCondition = lock.newCondition();
        Condition oldCondition = lock.newCondition();
        //偶数线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count <= 100) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ":" + count);
                        count++;
                        //唤醒奇数线程
                        oldCondition.signal();

                        //打印出100后，就不再等待
                        if (count <= 100) {
                            evenCondition.await();
                        }
                        //线程恢复后，一定是已经重新获取了锁的

                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "偶数").start();

        //奇数线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (count <= 100) {
                    try {
                        lock.lock();
                        System.out.println(Thread.currentThread().getName() + ":" + count);
                        count++;
                        //唤醒偶数线程
                        evenCondition.signal();

                        oldCondition.await();
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            }
        }, "奇数").start();
    }


    private static void method1() {
        Object monitor = new Object();
        new Thread(new EvenPrintTask(monitor), "偶数").start();
        new Thread(new OldPrintTask(monitor), "奇数").start();
    }


    static class OldPrintTask implements Runnable {

        private Object monitor;
        private int value = 1;

        public OldPrintTask(Object monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (value < 100) {
                synchronized (monitor) {
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                    value += 2;
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class EvenPrintTask implements Runnable {

        private Object monitor;
        private int value = 0;

        public EvenPrintTask(Object monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (value <= 100) {
                synchronized (monitor) {
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                    value += 2;
                    monitor.notify();
                    try {
                        if (value <= 100) {
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    static class OldPrintTask2 implements Runnable {

        private Object monitor;
        private int value = 1;

        public OldPrintTask2(Object monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (value < 100) {
                synchronized (monitor) {
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                    value += 2;
                    monitor.notify();
                    try {
                        monitor.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    static class EvenPrintTask2 implements Runnable {

        private Object monitor;
        private int value = 0;

        public EvenPrintTask2(Object monitor) {
            this.monitor = monitor;
        }

        @Override
        public void run() {
            while (value <= 100) {
                synchronized (monitor) {
                    System.out.println(Thread.currentThread().getName() + ":" + value);
                    value += 2;
                    monitor.notify();
                    try {
                        if (value <= 100) {
                            monitor.wait();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

}
