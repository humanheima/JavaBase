package com.hm.thread;

import java.util.concurrent.CountDownLatch;

/**
 * Created by dumingwei on 2017/7/4.
 * CountDownLatch一般用于某个线程A等待若干个其他线程执行完任务之后，它才执行；
 */
public class CountDownLatchTest {

    public static void main(String[] args) {
        final CountDownLatch countDownLatch = new CountDownLatch(2);

        new Thread(() -> {
            System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
            countDownLatch.countDown();
        }).start();

        new Thread() {
            public void run() {
                try {
                    System.out.println("子线程" + Thread.currentThread().getName() + "正在执行");
                    Thread.sleep(3000);
                    System.out.println("子线程" + Thread.currentThread().getName() + "执行完毕");
                    countDownLatch.countDown();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }.start();

        try {
            System.out.println("等待两个子线程执行完毕");
            countDownLatch.await();
            System.out.println("两个子线程已经执行完毕");
            System.out.println("继续执行主线程");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
