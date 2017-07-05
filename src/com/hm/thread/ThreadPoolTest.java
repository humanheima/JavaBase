package com.hm.thread;

import java.util.concurrent.*;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {
        ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newFixedThreadPool(3);
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newSingleThreadExecutor();
        //ThreadPoolExecutor executor = (ThreadPoolExecutor) Executors.newCachedThreadPool();
        for (int i = 0; i < 15; i++) {
            MyTask myTask = new MyTask(i);
            executor.execute(myTask);
            System.out.println("线程池中线程数目：" + executor.getPoolSize() +
                    "队列中等待的任务数目：" + executor.getQueue().size() + ",已经执行完的任务数目：" +
                    executor.getCompletedTaskCount());
        }
        executor.shutdown();
    }
}

class MyTask implements Runnable {

    private int taskNum;

    public MyTask(int taskNum) {
        this.taskNum = taskNum;
    }

    @Override
    public void run() {
        System.out.println("正在执行task" + taskNum);
        try {
            Thread.currentThread().sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("task" + taskNum + "执行完毕");
    }
}

