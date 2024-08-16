package com.hm.thread;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author 60055558
 * @version 1.0
 */
public class ABCPrinter4 {

    private AtomicInteger ai = new AtomicInteger(0);

    private static final int MAX_SYC_VALUE = 9;

    public static void main(String[] args) {
        ABCPrinter4 print = new ABCPrinter4();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.MILLISECONDS, new SynchronousQueue());
        executor.execute(print.new ThreadPrintA());
        executor.submit(print.new ThreadPrintB());
        executor.submit(print.new ThreadPrintC());
        executor.shutdown();
    }

    private class ThreadPrintA implements Runnable {
        public void run() {
            while (ai.get() < MAX_SYC_VALUE) {
                System.out.println("进入A线程\n");
                if (ai.get() % 3 == 0) {
                    System.out.print("A\n");
                    ai.getAndIncrement();
                }
            }
        }
    }

    private class ThreadPrintB implements Runnable {
        public void run() {
            while (ai.get() < MAX_SYC_VALUE) {
                System.out.println("进入B线程\n");
                if (ai.get() % 3 == 1) {
                    System.out.print("B\n");
                    ai.getAndIncrement();
                }
            }
        }
    }

    private class ThreadPrintC implements Runnable {
        public void run() {
            while (ai.get() < MAX_SYC_VALUE) {
                System.out.println("进入C线程\n");
                if (ai.get() % 3 == 2) {
                    System.out.print("C\n");
                    ai.getAndIncrement();
                }
            }
        }
    }
}
