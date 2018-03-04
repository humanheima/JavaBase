package com.hm.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by dumingwei on 2018/3/1 0001.
 * 使用线程的3种方式，也可以使用线程池。
 */
public class ThreeWayUseThread {

    public static void main(String[] args) {
        firstWay();
        secondWay();
        thirdWay();
    }

    private static void firstWay() {
        new MyThread("firstWay").start();
    }

    private static void secondWay() {
        new Thread(new MyRunnable()).start();
    }

    private static void thirdWay() {
        FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int result = 0;
                for (int i = 0; i < 100; i++) {
                    result += i;
                }
                return result;
            }
        });
        new Thread(futureTask).start();
        try {
            System.out.println(futureTask.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }

    static class MyRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }
}
