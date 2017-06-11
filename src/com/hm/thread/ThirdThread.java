package com.hm.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by dumingwei on 2017/6/7.
 */
public class ThirdThread {

    public static void main(String[] args) {
        FutureTask<Integer> task = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                int i = 0;
                for (; i < 100; i++) {
                    System.out.println(Thread.currentThread().getName() + "的循环变量i的值：" + i);
                }
                return i;
            }
        });
        new Thread(task, "有返回值的线程").start();

        try {
            System.out.println("子线程的返回值:" + task.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
