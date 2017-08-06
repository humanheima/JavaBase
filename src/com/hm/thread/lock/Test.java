package com.hm.thread.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dumingwei on 2017/6/18.
 */
public class Test {

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) {
        Test test = new Test();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
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

    public void insert(Thread thread) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            System.out.println(thread.getName() + "获得了锁");
            long startTime = System.currentTimeMillis();
            for (; ; ) {
                if (System.currentTimeMillis() - startTime >= Integer.MAX_VALUE)
                    break;
                //插入数据
            }
        } catch (Exception e) {

        } finally {
            System.out.println(Thread.currentThread().getName() + "执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }
}

class MyThread extends Thread {
    private Test test = null;

    public MyThread(Test test) {
        this.test = test;
    }

    @Override
    public void run() {

        try {
            test.insert(Thread.currentThread());
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + "被中断");
        }
    }
}

