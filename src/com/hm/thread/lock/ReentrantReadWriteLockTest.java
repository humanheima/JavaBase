package com.hm.thread.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by dumingwei on 2017/6/18.
 * 测试读写锁
 */
public class ReentrantReadWriteLockTest {

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();
        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();

        new Thread() {
            public void run() {
                test.write(Thread.currentThread());
            }
        }.start();
    }

    private void get(Thread thread) {
        rwl.readLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 100) {
                System.out.println(thread.getName() + "正在进行读操作");
            }
            System.out.println(thread.getName() + "读操作完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.readLock().unlock();
        }
    }

    private void write(Thread thread) {
        rwl.writeLock().lock();
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 100) {
                System.out.println(thread.getName() + "正在进行写操作");
            }
            System.out.println(thread.getName() + "写操作完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
