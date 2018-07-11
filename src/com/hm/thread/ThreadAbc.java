package com.hm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 顺序循环输出abc
 */
public class ThreadAbc {

    public static final int THREAD_A = 0;
    public static final int THREAD_B = 1;
    public static final int THREAD_C = 2;

    private int threadCode = 0;

    static class Task implements Runnable {
        private static final Lock lock = new ReentrantLock();
        private static final Condition condition = lock.newCondition();

        private final ThreadAbc threadAbc;
        private final int threadCode;

        public Task(ThreadAbc threadAbc, int threadCode) {
            this.threadAbc = threadAbc;
            this.threadCode = threadCode;
        }

        @Override
        public void run() {
            for (int x = 0; x < 100; x++) {
                runOnce();
            }
        }

        private void runOnce() {
            lock.lock();
            try {
                while (this.threadCode != threadAbc.threadCode) {
                    condition.await();
                }

                if (this.threadCode == THREAD_A) {
                    System.out.print('A');
                } else if (this.threadCode == THREAD_B) {
                    System.out.print('B');
                } else if (this.threadCode == THREAD_C) {
                    System.out.println('C');
                } else {
                    return;
                }

                threadAbc.threadCode = (threadAbc.threadCode + 1) % 3;

                condition.signalAll();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadAbc threadAbc = new ThreadAbc();

        Thread threadA = new Thread(new Task(threadAbc, THREAD_A));
        Thread threadB = new Thread(new Task(threadAbc, THREAD_B));
        Thread threadC = new Thread(new Task(threadAbc, THREAD_C));

        threadA.start();
        threadB.start();
        threadC.start();
    }
}