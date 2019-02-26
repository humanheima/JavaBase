package com.hm.thread;

/**
 * Created by dumingwei on 2017/10/3.
 */
public class JoinTest {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread().getName() + " is started");

        Thread exampleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is started");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " is completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        exampleThread.start();
        /**
         * main线程等待exampleThread结束以后才会往下执行
         */
        exampleThread.join();
        System.out.println(Thread.currentThread().getName() + " is completed");

        /*Thread exampleThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(Thread.currentThread().getName() + " is started");
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + " is completed");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        ProducerThread thread1 = new ProducerThread(exampleThread);
        ProducerThread thread2 = new ProducerThread(thread1);
        thread2.start();*/
    }

    static class Thread1 extends Thread {
        private Thread joinThread;

        public Thread1(Thread joinThread) {
            this.joinThread = joinThread;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is started");
                Thread.sleep(1000);
                joinThread.start();
                joinThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + " is completed");
        }
    }
}
