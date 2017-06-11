package com.hm.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> bq = new ArrayBlockingQueue<>(1);
       new Producer(bq).start();
       new Producer(bq).start();
       new Producer(bq).start();
       new Consumer(bq).start();
    }
}

class Producer extends Thread {

    private BlockingQueue<String> bq;

    public Producer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        String[] strArr = new String[]{"Java", "Struts", "Spring"};
        for (int i = 0; i < 20; i++) {
            System.out.println(getName() + "生产者准备生产集合元素");
            try {
                Thread.sleep(200);
                //尝试放入元素，如果队列已满，则线程被阻塞
                bq.put(strArr[i % 3]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "生产完成" + bq);
        }
    }
}

class Consumer extends Thread {

    private BlockingQueue<String> bq;

    public Consumer(BlockingQueue<String> bq) {
        this.bq = bq;
    }

    @Override
    public void run() {
        while (true){
            System.out.println(getName() + "消费者准备消费集合元素");
            try {
                Thread.sleep(200);
                //尝试取出元素，如果队列已空，线程被阻塞
                bq.take();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(getName() + "消费完成" + bq);
        }
    }
}
