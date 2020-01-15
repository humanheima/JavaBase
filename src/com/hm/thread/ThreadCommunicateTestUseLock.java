package com.hm.thread;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by dumingwei on 2017/7/4.
 * 生产者消费者模式 生产一个消费一个
 */
public class ThreadCommunicateTestUseLock {


    private int queueSize = 10;
    private Queue<Integer> queue = new ArrayDeque<>(queueSize);
    private Lock lock = new ReentrantLock();
    private Condition notFull = lock.newCondition();
    private Condition notEmpty = lock.newCondition();

    public static void main(String[] args) {
        ThreadCommunicateTestUseLock test = new ThreadCommunicateTestUseLock();
        Producer producer = test.new Producer();
        Consumer consumer = test.new Consumer();

        producer.start();
        consumer.start();
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
                            //队列已空
                            notEmpty.await();
                            System.out.println("Consumer after await");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sleep(100);
                    queue.poll();                //每次移走队首元素
                    //通知生产者生产数据
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
                            //队列已满
                            notFull.await();
                            System.out.println("Producer after await");
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    sleep(100);
                    queue.offer(1);       //每次插入一个元素
                    //通知消费者消费数据
                    notEmpty.signal();
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }
    }

}
