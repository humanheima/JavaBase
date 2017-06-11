package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class YieldTest extends Thread {

    public YieldTest(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            System.out.println(getName() + " " + i);
            if (i == 20) {
                //使用yield方法行当前线程让步
                Thread.yield();
            }
        }
    }

    public static void main(String[] args) {
        YieldTest yt1 = new YieldTest("高级");
        yt1.setPriority(MIN_PRIORITY);
        yt1.start();
        YieldTest yt2 = new YieldTest("低级");
         yt2.setPriority(MAX_PRIORITY);
        yt2.start();
    }
}
