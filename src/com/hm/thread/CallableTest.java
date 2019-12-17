package com.hm.thread;

import java.util.concurrent.*;

/**
 * Created by dumingwei on 2017/7/4.
 */
public class CallableTest {

    public static void main(String[] args) {
        Task task = new Task();
        FutureTask<Integer> result = new FutureTask<>(task);
        Thread thread = new Thread(result);
        thread.start();
        ExecutorService executor = Executors.newCachedThreadPool();
        StringTask stringTask = new StringTask();
        Future<String> stringResult = executor.submit(stringTask);
        executor.shutdown();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行任务");
        try {
            System.out.println("task运行结果" + result.get());
            System.out.println("stringTask运行结果" + stringResult.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("所有任务执行完毕");
    }
}

class Task implements Callable<Integer> {

    @Override
    public Integer call() throws Exception {
        System.out.println("子线程正在计算");
        Thread.sleep(3000);
        int sum = 0;
        for (int i = 0; i <= 100; i++) {
            sum += i;
        }
        return sum;
    }
}

class StringTask implements Callable<String> {

    @Override
    public String call() throws Exception {
        String result = null;
        try {
            //noinspection unchecked
            result = Integer.toString(3);
        } catch (Throwable tr) {
            throw tr;
        } finally {
            postResult();
        }
        return result;
    }

    private String postResult() {
        return "hello world";
    }
}
