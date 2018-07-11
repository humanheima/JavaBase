package com.hm.thread;

/**
 * 顺序输出ABCD
 */
public class SoutAbcdInSequence {

    public static void main(String[] args) throws InterruptedException {

        // 线程A
        final Thread a = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("A");
            }
        });

        // 线程B
        final Thread b = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("B");

            }
        });

        // 线程C
        final Thread c = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("C");
            }
        });

        // 线程D
        Thread d = new Thread(new Runnable() {

            @Override
            public void run() {
                System.out.println("D");
            }
        });

        // 启动四个线程
        a.start();
        a.join();
        b.start();
        b.join();
        c.start();
        c.join();
        d.start();

    }

}  