package com.hm.thread;

import java.util.concurrent.*;

/**
 * Created by dumingwei on 2017/10/3.
 * How to stop a thread.
 */
public class StopThreadTest {

    public static void main(String[] args) {
      /*  TestMyThread thread = new TestMyThread(false);
        thread.start();
        try {
            Thread.sleep(1000);
            thread.setShouldStop(true);
            System.out.println("setShouldStop true");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        /*MyInterruptedThread thread = new MyInterruptedThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        MyInterruptedThread thread = new MyInterruptedThread();
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        Future<?> future = executorService.submit(thread);

        try {
            future.get(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("InterruptedException");
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
            System.out.println("thread over time");
        } finally {
            //取消
            System.out.println("cancel");
            future.cancel(true);
        }
    }
}

/**
 * 使用一个变量标志线程是否应该退出
 */
class TestMyThread extends Thread {

    private volatile boolean shouldStop;

    public TestMyThread(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    public void setShouldStop(boolean shouldStop) {
        this.shouldStop = shouldStop;
    }

    @Override
    public void run() {
        while (!shouldStop) {
            System.out.println(getName() + "Thread is running");
            try {
                //让线程睡眠
                sleep(100000000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(getName() + "线程退出");
    }
}

class MyInterruptedThread extends Thread {

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println("running");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException occurred");
                //抛出InterruptedException后中断标志被清除，标准做法是再次调用interrupt恢复中断
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("stop");
    }

}
