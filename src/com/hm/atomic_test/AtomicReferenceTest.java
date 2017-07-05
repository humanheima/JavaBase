package com.hm.atomic_test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by dumingwei on 2017/7/5.
 * 原子更新引用类型
 */
public class AtomicReferenceTest {

    private static int threadCount = 10;
    private static CountDownLatch countDown = new CountDownLatch(threadCount);
    private static AtomicReference<User> atomicReference = new AtomicReference<>();


    public static void main(String[] args) {
        Thread[] threads = new Thread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            threads[i] = new Thread(new ReferenceUpdater(new User("name" + i, i)));
        }
        for (int i = 0; i < threadCount; i++) {
            threads[i].start();
        }
        try {
            countDown.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getOld());
    }

    private static class ReferenceUpdater implements Runnable {

        private User user;

        public ReferenceUpdater(User user) {
            this.user = user;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                User oldUser = atomicReference.get();
                atomicReference.compareAndSet(oldUser, user);
                Thread.yield();
            }
            countDown.countDown();
        }
    }

    private static class User {
        private String name;
        private int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public int getOld() {
            return old;
        }
    }

}
