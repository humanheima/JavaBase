package com.hm.thread;

import java.util.PriorityQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dumingwei on 2017/7/4.
 * 线程间协作的两种方式：wait、notify、notifyAll和Condition
 */
public class ThreadCommunicateTest {

    public static Object object = new Object();
    private int queueSize = 10;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        /**
         * wait notify 方式
         */
        Thread1 thread1 = new Thread1();
        Thread2 thread2 = new Thread2();
        thread1.start();
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread2.start();
      /*  ThreadCommunicateTest test = new ThreadCommunicateTest();
        Producer producter = test.new Producer();
        Consumer consumer = test.new Consumer();
        producter.start();
        consumer.start();*/

    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consumer();
        }
    }

    private void consumer() {
        while (true) {
            lock.lock();
            try {
                while (queue.size() == 0) {
                    System.out.println("队列空，等待数据");
                    try {
                        notEmpty.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.poll();//每次移走队首元素
                notFull.signal();//唤醒生产者
                System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
            } finally {
                lock.unlock();
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }
    }

    private void produce() {
        while (true) {
            lock.lock();
            try {
                while (queue.size() == queueSize) {
                    System.out.println("队列满，等待有空余空间");
                    try {
                        notFull.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                queue.offer(1);//每次插入一个元素
                notEmpty.signal();//唤醒消费者
                System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
            } finally {
                lock.unlock();
            }
        }
    }

    static class Thread1 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                try {
                    object.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程" + Thread.currentThread().getName() + "获取到了锁");
            }
        }
    }

    static class Thread2 extends Thread {

        @Override
        public void run() {
            synchronized (object) {
                object.notify();
                System.out.println("线程" + Thread.currentThread().getName() + "调用了object.notify()");
            }
            System.out.println("线程" + Thread.currentThread().getName() + "释放了锁");
        }
    }
}
