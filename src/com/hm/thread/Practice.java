package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class Practice {

    public static void main(String[] args) {
        Object printer = new Object();
        ThreadB b = new ThreadB(printer);
        ThreadA a = new ThreadA(printer);
        a.start();
        b.start();
    }
}

/**
 * 打印数字
 */
class ThreadA extends Thread {

    Object printer;

    public ThreadA(Object printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        //同步代码块执行之前，必须先获得对同步监视器(printer)的锁定
        //任何时刻只能有一个线程可以获得对通监视器的锁定，
        // 当同步代码块执行结束后，该线程会释放对该同步监视器的锁定
        synchronized (printer) {
            for (int i = 1; i < 53; i++) {
                System.out.print(i + " ");
                if (i % 2 == 0) {
                    //唤醒其他线程
                    printer.notifyAll();
                    try {
                        //当当线程执行同步代码块或者同步方法的时候
                        // 程序执行了同步监视器的wait()方法
                        // 则当前线程暂停，并释放同步监视器
                        printer.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

/**
 * 打印字母
 */
class ThreadB extends Thread {

    private Object printer;

    public ThreadB(Object printer) {
        this.printer = printer;
    }

    @Override
    public void run() {
        synchronized (printer) {
            for (int i = 0; i < 26; i++) {
                System.out.print((char) (i + 65));
                //唤醒其他线程
                printer.notifyAll();
                try {
                    //最后一个就不要等了
                    if (i != 25) {
                        printer.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}




