package com.hm.lock;

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
                test.write(Thread.currentThread());
            }
        }.start();

       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        new Thread() {
            public void run() {
                test.get(Thread.currentThread());
            }
        }.start();
    }

    private void get(Thread thread) {
        rwl.readLock().lock();
        try {
            for (int i = 0; i < 1000; i++) {
                System.out.println(thread.getName() + "正在进行读操作" + i);
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
            for (int i = 0; i < 100; i++) {
                System.out.println(thread.getName() + "正在进行写操作" + i);
            }
            System.out.println(thread.getName() + "写操作完毕");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwl.writeLock().unlock();
        }
    }
}
