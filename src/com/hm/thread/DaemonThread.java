package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class DaemonThread extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(getName() + " " + i);
        }
    }

    public static void main(String[] args) {
        DaemonThread t = new DaemonThread();
        t.setDaemon(true);
        t.start();
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+" "+i);
        }
        //main线程执行到此处结束
        //后台线程也随之结束
    }
}
