package com.hm.thread;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class ForkJoinPoolTest {

    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        pool.submit(new PrintTask(0, 300));
        try {
            pool.awaitTermination(2, TimeUnit.SECONDS);
            pool.shutdown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class PrintTask extends RecursiveAction {

    private static final int THRESHOLD = 50;
    private int start;
    private int end;

    public PrintTask(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected void compute() {
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                System.out.println(Thread.currentThread().getName() + "i=" + i);
            }
        } else {
            int middle = (start + end) / 2;
            PrintTask left = new PrintTask(start, middle);
            PrintTask right = new PrintTask(middle + 1, end);
            left.fork();
            right.fork();
        }
    }
}
