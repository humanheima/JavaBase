package com.hm.thread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by dumingwei on 2017/7/4.
 * 生产者消费者模式，生产一个消费两个，感觉可以理解为一个生产者，两个消费者
 */
public class ThreadCommunicateTestUseLock2 {


    private int queueSize = 2;
    private Queue<Integer> queue = new ArrayDeque<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ThreadCommunicateTestUseLock2 test = new ThreadCommunicateTestUseLock2();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();
        Consumer consumer1 = test.new Consumer();

        producer.start();
        consumer.start();
        consumer1.start();
    }

    class Consumer extends Thread {

        @Override
        public void run() {
            consume();
        }

        private void consume() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列空，等待数据");
                            notEmpty.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sleep(100);
                    queue.poll();                //每次移走队首元素
                    notFull.signal();
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

    class Producer extends Thread {

        @Override
        public void run() {
            produce();
        }

        private void produce() {
            while (true) {
                lock.lock();
                try {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列满，等待有空余空间");
                            notFull.await();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sleep(100);
                    queue.offer(1);       //每次插入一个元素
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                    notEmpty.signal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
