package com.hm.thread;

import java.util.concurrent.Semaphore;

/**
 * Created by p_dmweidu on 2025/6/25
 * Desc:
 */
public class PrintABC {

    private int n; // 循环次数
    private Semaphore semA = new Semaphore(1); // 控制打印 A
    private Semaphore semB = new Semaphore(0); // 控制打印 B
    private Semaphore semC = new Semaphore(0); // 控制打印 C

    public PrintABC(int n) {
        this.n = n;
    }

    // 打印 A 的线程
    public void printA() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semA.acquire(); // 获取 A 的信号量
            System.out.print("A");
            semB.release(); // 释放 B 的信号量
        }
    }

    // 打印 B 的线程
    public void printB() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semB.acquire(); // 获取 B 的信号量
            System.out.print("B");
            semC.release(); // 释放 C 的信号量
        }
    }

    // 打印 C 的线程
    public void printC() throws InterruptedException {
        for (int i = 0; i < n; i++) {
            semC.acquire(); // 获取 C 的信号量
            System.out.print("C");
            semA.release(); // 释放 A 的信号量
        }
    }

    public static void main(String[] args) {
        int n = 5; // 打印 3 次 ABC
        PrintABC printer = new PrintABC(n);

        // 创建三个线程
        Thread tA = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.printA();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread tB = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.printB();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread tC = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    printer.printC();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // 启动线程
        tA.start();
        tB.start();
        tC.start();
    }
}