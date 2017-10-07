package com.hm.thread;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * Created by dumingwei on 2017/10/3.
 */
public class ProducerConsumerPattern {

    public static void main(String[] args) {
        //create shared object
        BlockingQueue sharedQueue = new LinkedBlockingDeque();

        Thread producer = new Thread(new Producer(sharedQueue));
        Thread consumer = new Thread(new Consumer(sharedQueue));

        producer.start();
        consumer.start();

    }

    static class Producer implements Runnable {

        private final BlockingQueue sharedQueue;

        public Producer(BlockingQueue sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println("produced:" + i);
                    sharedQueue.put(i);
                } catch (InterruptedException e) {
                    System.out.println("produce error:" + e.getMessage());
                }
            }
        }
    }

    static class Consumer implements Runnable {

        private final BlockingQueue sharedQueue;

        public Consumer(BlockingQueue sharedQueue) {
            this.sharedQueue = sharedQueue;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    int result = (int) sharedQueue.take();
                    System.out.println("consumed:" + result);
                    if (result == 9)
                        break;
                } catch (InterruptedException e) {
                    System.out.println("consume error:" + e.getMessage());
                }
            }
        }
    }
}
