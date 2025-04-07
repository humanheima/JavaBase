package com.hm.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Created by p_dmweidu on 2025/4/7
 * Desc: 锁降级的操作
 */
public class LockDowngradeExample {
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
    private int data = 0;

    public void processData() {
        // 获取写锁
        rwLock.writeLock().lock();
        try {
            // 修改数据
            data = 42;
            System.out.println(Thread.currentThread().getName() + " 修改数据为: " + data);

            // 在持有写锁时获取读锁（开始锁降级）
            rwLock.readLock().lock();
            try {
                // 这里仍然持有写锁和读锁
                System.out.println(Thread.currentThread().getName() + " 在写锁下读取数据: " + data);
            } finally {
                // 释放写锁，完成锁降级（此时只持有读锁）
                rwLock.writeLock().unlock();
            }

            // 现在只持有读锁，可以继续读取
            System.out.println(Thread.currentThread().getName() + " 降级后读取数据: " + data);
        } finally {
            // 释放读锁
            rwLock.readLock().unlock();
        }
    }

    public static void main(String[] args) {
        LockDowngradeExample example = new LockDowngradeExample();
        Thread thread = new Thread(example::processData, "Thread-1");
        thread.start();
    }
}