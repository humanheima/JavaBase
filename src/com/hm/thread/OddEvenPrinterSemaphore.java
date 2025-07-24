package com.hm.thread;

import java.util.concurrent.Semaphore;

/**
 * Created by p_dmweidu on 2025/7/24
 * Desc: 使用信号量，交替打印奇数和偶数
 */
public class OddEvenPrinterSemaphore {

    private int number = 1;
    private final int maxNumber;
    private final Semaphore oddSemaphore = new Semaphore(1); // 奇数线程初始有许可
    private final Semaphore evenSemaphore = new Semaphore(0); // 偶数线程初始无许可

    public OddEvenPrinterSemaphore(int maxNumber) {
        this.maxNumber = maxNumber;
    }

    public void printOdd() {
        while (number <= maxNumber) {
            try {
                oddSemaphore.acquire();
                if (number <= maxNumber && number % 2 != 0) {
                    System.out.println("Odd Thread: " + number);
                    number++;
                }
                evenSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public void printEven() {
        while (number <= maxNumber) {
            try {
                evenSemaphore.acquire();
                if (number <= maxNumber && number % 2 == 0) {
                    System.out.println("Even Thread: " + number);
                    number++;
                }
                oddSemaphore.release();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public static void main(String[] args) {
        final int MAX_NUMBER = 20;
        OddEvenPrinterSemaphore printer = new OddEvenPrinterSemaphore(MAX_NUMBER);

        Thread oddThread = new Thread(printer::printOdd, "Odd-Thread");
        Thread evenThread = new Thread(printer::printEven, "Even-Thread");

        oddThread.start();
        evenThread.start();
    }
}