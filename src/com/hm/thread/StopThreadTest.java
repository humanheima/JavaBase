package com.hm.thread;

/**
 * Created by dumingwei on 2017/10/3.
 * How to stop a thread.
 */
public class StopThreadTest {

    public static void main(String[] args) {
       /* Runner runner = new Runner(false);
        runner.start();
        try {
            Thread.sleep(1000);
            runner.setbExit(true);
            runner.join();
            System.out.println("线程退出");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

        MyInterruptedThread thread = new MyInterruptedThread();
        thread.start();
        try {
            Thread.sleep(2000);
            thread.cancel();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

/**
 * 使用一个变量标志线程是否应该退出
 */
class Runner extends Thread {

    private volatile boolean bExit = false;

    public Runner(boolean bExit) {
        this.bExit = bExit;
    }

    public void setbExit(boolean bExit) {
        this.bExit = bExit;
    }

    @Override
    public void run() {
        while (!bExit) {
            System.out.println("Thread is running");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

class MyInterruptedThread extends Thread {

    @Override
    public void run() {
        while (!this.isInterrupted()) {
            try {
                System.out.println("running");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.out.println("InterruptedException occurred");
                //抛出InterruptedException后中断标志被清除，标准做法是再次调用interrupt恢复中断
                this.interrupt();
            }
        }
        System.out.println("stop");
    }

    public void cancel() {
        interrupt();
    }

}
