package com.hm.thread;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by dumingwei on 2017/3/30.
 * 测试Thread 的join 方法
 * join() 方法主要是让调用该方法的thread完成run方法里面的东西后， 再执行join()方法后面的代码。
 */
public class ThreadJoinTest {

    public static void main(String args[]) throws InterruptedException {
        Thread threadA = new Thread(new RunnableImplA());
        Thread threadB = new Thread(new RunnableImplB());
        Thread threadC = new Thread(new RunnableImplC());
        threadA.start();
        threadA.join();
        threadB.start();
        threadB.join();
        threadC.start();
        threadC.join();
        System.out.println("join finish" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
    }


    static class RunnableImplA implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("RunnableImplA begin sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
                Thread.sleep(2000);
                System.out.println("RunnableImplA end sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class RunnableImplB implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("RunnableImplB begin sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
                Thread.sleep(2000);
                System.out.println("RunnableImplB end sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class RunnableImplC implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("RunnableImplC begin sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
                Thread.sleep(2000);
                System.out.println("RunnableImplC end sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}