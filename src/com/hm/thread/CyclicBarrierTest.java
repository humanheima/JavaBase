package com.hm.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by dumingwei on 2017/7/4.
 * CyclicBarrier一般用于一组线程互相等待至某个状态，然后这一组线程再同时执行；
 */
public class CyclicBarrierTest {

    public static void main(String[] args) {

        int N = 5;
        CyclicBarrier barrier = new CyclicBarrier(N);
        for (int i = 0; i < N; i++) {
            new Writer(barrier).start();
        }

    }

    static class Writer extends Thread {

        private CyclicBarrier cyclicBarrier;

        public Writer(CyclicBarrier cyclicBarrier) {
            this.cyclicBarrier = cyclicBarrier;
        }

        @Override
        public void run() {
            System.out.println("线程" + Thread.currentThread().getName() + "hello");
            try {
                cyclicBarrier.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (BrokenBarrierException e) {
                e.printStackTrace();
            }
            //互相等待，然后同时执行这行代码
            System.out.println("线程" + Thread.currentThread().getName() + "world");
        }
    }
}
