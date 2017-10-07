package com.hm.thread;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 * Created by dumingwei on 2017/7/4.
 * 线程间协作的两种方式：wait、notify、notifyAll和Condition
 */
public class ThreadCommunicateTest {


    private int queueSize = 100;
    private PriorityQueue<Integer> queue = new PriorityQueue<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        /**
         * wait notify 方式
         */
        final Queue sharedQ = new LinkedList();
        Thread1 thread1 = new Thread1(sharedQ);
        Thread2 thread2 = new Thread2(sharedQ);
        thread1.start();
        thread2.start();
        /*ThreadCommunicateTest test = new ThreadCommunicateTest();
        Producer producter = test.new Producer();
        Consumer consumer = test.new Consumer();
        producter.start();
        consumer.start();*/
    }

    static class Thread1 extends Thread {

        private final Queue<Integer> sharedQ;

        public Thread1(Queue<Integer> sharedQ) {
            super("Producer");
            this.sharedQ = sharedQ;
        }

        @Override
        public void run() {
            for (int i = 0; i < 4; i++) {
                synchronized (sharedQ) {
                    /**
                     * 在循环中使用wait和notify，而不是If代码块中
                     * Producer线程要在队列满了的情况下调用wait，所以第一反应都是使用If语句。但是，在If代码块中调用
                     * wait会产生bug，因为线程存在一定的可能在等待条件没有改变的情况下假唤醒(spurious wake up)。
                     * 所以如果没有使用循环在线程唤醒后检查等待条件，可能会造成尝试在已经满了的队列中插入元素或者在空了
                     * 的队列中取元素。这就是为什么我们要在while循环中调用wait而不是if。
                     */
                    while (sharedQ.size() >= 1) {
                        try {
                            System.out.println("queue is full waiting");
                            sharedQ.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("producing:" + i);
                    sharedQ.add(i);
                    sharedQ.notify();
                }
            }
        }
    }

    static class Thread2 extends Thread {

        private final Queue<Integer> sharedQ;

        public Thread2(Queue<Integer> sharedQ) {
            super("Consumer:");
            this.sharedQ = sharedQ;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (sharedQ) {
                    //waiting condition - wait until Queue is not empty
                    while (sharedQ.size() == 0) {
                        try {
                            System.out.println("Queue is empty,waiting");
                            sharedQ.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    int number = sharedQ.poll();
                    System.out.println("consuming:" + number);
                    sharedQ.notify();
                    //termination condition
                    if (number == 3)
                        break;
                }
            }
        }
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

}
