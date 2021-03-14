package com.hm.thread;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dumingwei on 2017/7/4.
 * 线程间协作的两种方式：wait、notify、notifyAll和Condition
 */
public class ThreadCommunicateTestUseWaitNotify {


    private int queueSize = 10;
    private Queue<Integer> queue = new LinkedList<>();

    public static void main(String[] args) {
        ThreadCommunicateTestUseWaitNotify test = new ThreadCommunicateTestUseWaitNotify();
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
                synchronized (queue) {
                    while (queue.size() == 0) {
                        try {
                            System.out.println("队列空，等待数据");
                            //当前线程等待，直到其他线程调用notify，或者notifyAll方法
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.poll();          //每次移走队首元素
                    System.out.println("从队列取走一个元素，队列剩余" + queue.size() + "个元素");
                    queue.notify();
                }
                /*try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
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
                synchronized (queue) {
                    while (queue.size() == queueSize) {
                        try {
                            System.out.println("队列满，等待有空余空间");
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                            queue.notify();
                        }
                    }
                    queue.offer(1);        //每次插入一个元素
                    System.out.println("向队列取中插入一个元素，队列剩余空间：" + (queueSize - queue.size()));
                    queue.notify();
                }

                /*try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }
        }
    }
}
