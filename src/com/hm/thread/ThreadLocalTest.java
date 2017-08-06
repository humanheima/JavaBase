package com.hm.thread;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class ThreadLocalTest {

    public static void main(String[] args) {
        List<TestRunnable> testRunnables = new ArrayList<>();
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 10; i++) {
            TestRunnable testRunnable = new TestRunnable(i);
            testRunnables.add(testRunnable);
        }
        for (int i = 0; i < testRunnables.size(); i++) {
            executorService.execute(testRunnables.get(i));
        }
        executorService.shutdown();
    }


    static class TestRunnable implements Runnable {

        private int anInt;

        public TestRunnable(int anInt) {
            this.anInt = anInt;
        }

        @Override
        public void run() {
            System.out.println(anInt);
        }
    }
}


