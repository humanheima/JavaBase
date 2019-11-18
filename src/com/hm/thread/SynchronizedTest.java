package com.hm.thread;

public class SynchronizedTest {

    //同步方法
    public synchronized void doSth() {
        System.out.println("Hello World");
    }

    //同步代码块
    public void doSth1() {
        synchronized (SynchronizedTest.class) {
            System.out.println("Hello World");
        }
    }

}
