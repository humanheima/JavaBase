package com.hm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author 60055558
 * @version 1.0
 */
public class ABCPrinter3 {

    private static Lock lock = new ReentrantLock();
    private static Condition A = lock.newCondition();//Condition是被绑定到Lock上的，必须使用lock.newCondition()才能创建一个Condition
    private static Condition B = lock.newCondition();
    private static Condition C = lock.newCondition();

    private static int count = 0;

    public static void main(String[] args) {
        new ThreadPrintA().start();
        new ThreadPrintB().start();
        new ThreadPrintC().start();
    }

    public static class ThreadPrintA extends Thread {

        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 0) {//注意这里是不等于0，也就是说没轮到该线程执行，之前一直等待状态
                        A.await();//该线程A将会释放lock锁，构造成节点加入等待队列并进入等待状态
                    }
                    System.out.print("A\n");
                    count++;
                    B.signalAll();// A执行完唤醒B线程
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadPrintB extends Thread {
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 1) {
                        B.await();// B释放lock锁，当前面A线程执行后会通过B.signalAll()唤醒该线程
                    }
                    System.out.print("B\n");
                    count++;
                    C.signalAll();// B执行完唤醒C线程
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static class ThreadPrintC extends Thread {
        public void run() {
            try {
                lock.lock();
                for (int i = 0; i < 10; i++) {
                    while (count % 3 != 2) {
                        C.await();// C释放lock锁
                    }
                    System.out.print("C\n");
                    count++;
                    A.signalAll();// C执行完唤醒A线程
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}
