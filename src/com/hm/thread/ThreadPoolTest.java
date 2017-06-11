package com.hm.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class ThreadPoolTest {

    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(6);
        Runnable target = () -> {
            for (int i = 0; i < 100; i++) {
                System.out.println(Thread.currentThread().getName() + "的 i 值为：" + i);
            }
        };
        pool.submit(target);
        pool.submit(target);
        pool.shutdown();
    }
}
