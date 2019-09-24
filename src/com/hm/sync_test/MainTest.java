package com.hm.sync_test;

public class MainTest {

   /* public static void main(String[] args) {
        MainTest mainTest=new MainTest();
        mainTest.test();
    }*/

    /*private Lock lock = new ReentrantLock();

    public void test() {
        lock.lock();
        try {
            System.out.println("Hello world");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            lock.unlock();
        }
    }*/


    public void test() {
        synchronized (MainTest.class) {
            System.out.println("Hello world");
        }
    }


    /*public synchronized void test() {
        System.out.println("Hello world");
    }*/
}
