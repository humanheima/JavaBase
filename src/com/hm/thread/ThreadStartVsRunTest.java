package com.hm.thread;

/**
 * Created by dumingwei on 2017/10/3.
 * 调用线程 start()方法和直接调用run()方法的区别测试
 * 1. 调用start方法会创建一个新的线程，run方法运行在新的线程里面
 * 直接调用run方法不会创建新线程，run方法会在当先线程执行。
 * 2. start方法只能调用一次 如果再次调用会抛出IllegalStateException,
 * run方法可以被调用多次。
 */
public class ThreadStartVsRunTest {

    public static void main(String[] args) {
        Thread startThread = new Thread(new Task("start"));
        Thread runThread = new Thread(new Task("run"));

        startThread.start();
        startThread.start();
        runThread.run();
        runThread.run();
    }


    private static class Task implements Runnable {

        private String caller;

        public Task(String caller) {
            this.caller = caller;
        }

        @Override
        public void run() {
            System.out.println("Caller:" + caller + ",and code on this Thread is executed by :" + Thread.currentThread().getName());
        }
    }
}
