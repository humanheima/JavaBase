package com.hm.thread;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * Created by p_dmweidu on 2025/6/25
 * Desc:三个线程分别打印 0、奇数和偶数，输出格式为 01020304...。
 */
public class PrintZeroEvenOdd {
    private int n;


    private Semaphore zeroSem = new Semaphore(1);  // 控制打印0
    private Semaphore evenSem = new Semaphore(0);  // 控制打印偶数
    private Semaphore oddSem = new Semaphore(0);   // 控制打印奇数

    public PrintZeroEvenOdd(int n) {
        this.n = n;
    }

    // 打印0的线程
    public void zero(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i++) {
            //获取一个许可，减少计数器。如果计数 < 0，线程阻塞。
            zeroSem.acquire();
            printNumber.accept(0);
            if (i % 2 == 1) {
                //释放一个许可，增加计数器，并唤醒一个等待线程（如果有）。
                oddSem.release();  // 奇数轮次释放奇数信号量
            } else {
                //释放一个许可，增加计数器，并唤醒一个等待线程（如果有）。
                evenSem.release(); // 偶数轮次释放偶数信号量
            }
        }
    }

    // 打印偶数的线程
    public void even(IntConsumer printNumber) throws InterruptedException {
        for (int i = 2; i <= n; i += 2) {
            evenSem.acquire();
            printNumber.accept(i);
            zeroSem.release(); // 释放给打印0的线程
        }
    }

    // 打印奇数的线程
    public void odd(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; i += 2) {
            oddSem.acquire();
            printNumber.accept(i);
            zeroSem.release(); // 释放给打印0的线程
        }
    }

    public static void main(String[] args) {
        int n = 5; // 示例输入
        PrintZeroEvenOdd printer = new PrintZeroEvenOdd(n);
        IntConsumer printNumber = new IntConsumer() {
            @Override
            public void accept(int value) {
                System.out.print(value);
            }
        };

        // 创建三个线程
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.zero(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.odd(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.even(printNumber);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        t1.start();
        t2.start();
        t3.start();
    }
}