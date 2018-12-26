package com.hm.thread;

import java.util.concurrent.*;

public class InterruptByFuture {

    public static void main(String[] args) throws Exception {
        ExecutorService es = Executors.newSingleThreadExecutor();
        Future<?> task = es.submit(new MyThread("我的线程"));

        try {
            //限定时间获取结果
            task.get(3, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            //超时触发线程中止
            System.out.println("thread over time");
        } catch (ExecutionException e) {
            throw e;
        } finally {
            System.out.println("cancel");
            boolean mayInterruptIfRunning = true;
            task.cancel(mayInterruptIfRunning);
        }

       /* CountOperate countOperate = new CountOperate();
        Thread thread = new Thread(countOperate);
        thread.setName("A");
        thread.start();*/

    }

    private static class MyThread extends Thread {

        public MyThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            super.run();
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    System.out.println("count");
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    System.out.println("interrupt");
                    System.out.println(Thread.currentThread().getName() + "," + Thread.currentThread().getId());
                    System.out.println(this.getName() + "," + this.getId());
                    Thread.currentThread().interrupt();
                }
            }
            System.out.println("thread stop");
        }

    }
}

