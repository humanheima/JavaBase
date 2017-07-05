package com.hm.thread.lock;

import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dumingwei on 2017/6/18.
 */
public class Test {

    private ArrayList<Integer> list = new ArrayList<>();
    private Lock lock = new ReentrantLock();
    private ReadWriteLock readWriteLock;
    public static void main(String[] args) {
        Test test = new Test();
        MyThread thread1 = new MyThread(test);
        MyThread thread2 = new MyThread(test);
        thread1.start();
        thread2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.interrupt();
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
            System.out.println(Thread.currentThread().getName()+"执行finally");
            lock.unlock();
            System.out.println(thread.getName() + "释放了锁");
        }
    }

    /* public void tryInsert(Thread thread) {
         if (lock.tryLock()) {
             try {
                 System.out.println(thread.getName() + "获得了锁");
                 for (int i = 0; i < 5; i++) {
                     list.add(i);
                 }
             } catch (Exception e) {

             } finally {
                 lock.unlock();
                 System.out.println(thread.getName() + "释放了锁");
             }
         } else {
             System.out.println(thread.getName() + "获取锁失败");
         }
     }
 }
 */
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

