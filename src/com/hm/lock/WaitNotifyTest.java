package com.hm.lock;

/**
 * Created by dumingwei on 2017/6/18.
 */
public class WaitNotifyTest {

    public static Object object = new Object();

    public static void main(String[] args) {
        WaitNotifyTest waitNotifyTest = new WaitNotifyTest();
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        try {
            Thread.sleep(2000);
            thread2.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(2000);
            thread2.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class Thread1 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
                try {
                    System.out.println("线程" + Thread.currentThread().getName() + "释放了锁");
                    object.wait();
                    System.out.println("线程" + Thread.currentThread().getName() + "再次获取到了锁");

                } catch (InterruptedException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    static class Thread2 extends Thread {
        @Override
        public void run() {
            synchronized (object) {
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了object.notify()");
            }
            System.out.println("线程" + Thread.currentThread().getName() + "释放了锁");
        }
    }

}

