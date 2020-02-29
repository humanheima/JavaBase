package com.hm.lock;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by dumingwei on 2017/6/18.
 * 测试读写锁
 */
public class ReentrantReadWriteLockTest {

    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public static void main(String[] args) {
        ReentrantReadWriteLockTest test = new ReentrantReadWriteLockTest();

        test.testLockDowngrade();

        /*new Thread() {
            public void run() {
                test.write(Thread.currentThread());
            }
        }.start();*/

       /* try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
*/
        /*new Thread() {
            public void run() {
                test.read(Thread.currentThread());
            }
        }.start();*/


    }

    private void read(Thread thread) {
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

    private void testLockDowngrade() {
        CachedData cachedData = new CachedData();
        cachedData.processCachedData();
    }


    class CachedData {

        String data;
        volatile boolean cacheValid;

        final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

        void processCachedData() {
            rwl.readLock().lock();
            if (!cacheValid) {
                // 在获取写锁之前必须释放读锁
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    // 重新检查状态，因为另一个线程可能在执行此操作之前已获得写锁定并更改了状态。
                    if (!cacheValid) {
                        //修改数据
                        System.out.println("修改数据");
                        data = "Hello world";
                        cacheValid = true;
                    }
                    // 通过在释放写锁之前获取读锁来实现降级
                    rwl.readLock().lock();
                } finally {
                    rwl.writeLock().unlock(); // 释放写锁但仍持有读锁
                }
            }

            try {
                //读数据
                System.out.println("读取数据");
                System.out.println(data);
            } finally {
                rwl.readLock().unlock();
            }
        }
    }

}
