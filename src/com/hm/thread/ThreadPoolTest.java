package com.hm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dumingwei on 2017/6/8.
 * 关于线程池
 * 1.如果当前线程池中的线程数目小于corePoolSize，则每来一个任务，就会创建一个线程去执行这个任务；
 * 2.如果当前线程池中的线程数目>=corePoolSize，则每来一个任务，会尝试将其添加到任务缓存队列当中，若添加成功，则该任务会
 * 等待空闲线程将其取出去执行；若添加失败（一般来说是任务缓存队列已满），则会尝试创建新的线程去执行这个任务；
 * 3.如果当前线程池中的线程数目达到maximumPoolSize，则会采取任务拒绝策略进行处理；
 * 4.如果线程池中的线程数量大于 corePoolSize时，如果某线程空闲时间超过keepAliveTime，线程将被终止，
 * 直至线程池中的线程数目不大于corePoolSize；如果允许为核心池中的线程设置存活时间，那么核心池中的线程空闲时间超过
 * keepAliveTime，线程也会被终止。
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
       /* ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        MyTask myTask = new MyTask(1);
        executor.execute(myTask);
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        *//**
         * executor.remove(myTask)方法不能成功移除一个已经运行的线程。
         *//*
        System.out.println(executor.remove(myTask));*/

        testSingleThreadExecutor();
    }

    private static void testSingleThreadExecutor() {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 10; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
        }
        executor.shutdown();
    }
}

class MyTask implements Runnable {

    private int taskNum;

    MyTask(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {

        System.out.println(Thread.currentThread().getName() + " task" + taskNum + " 正在执行");
        try {
            Thread.sleep(400);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis() + Thread.currentThread().getName() + " task" + taskNum + " 执行完毕\n");

    }
}

