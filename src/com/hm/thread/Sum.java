package com.hm.thread;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class Sum {

    public static void main(String[] args) {
        int[] arr = new int[100];
        int sum=0;
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i+1;
            sum+=arr[i];

        }
        System.out.println(sum);
        ForkJoinPool pool = new ForkJoinPool();
        Future<Integer> future = pool.submit(new CalTask(arr, 0, arr.length));
        try {
            System.out.println(future.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        pool.shutdown();

    }
}

class CalTask extends RecursiveTask<Integer> {

    private static final int THRESHOLD = 20;
    private int[] arr;
    private int start;
    private int end;

    public CalTask(int[] arr, int start, int end) {
        this.arr = arr;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum = 0;
        if (end - start < THRESHOLD) {
            for (int i = start; i < end; i++) {
                sum += arr[i];

            }
            return sum;
        } else {
            int middle = (start + end) / 2;
            CalTask left = new CalTask(arr, start, middle);
            CalTask right = new CalTask(arr, middle , end);
            left.fork();
            right.fork();
            return left.join() + right.join();
        }
    }
}

