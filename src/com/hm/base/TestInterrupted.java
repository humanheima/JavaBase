package com.hm.base;

/**
 * Created by dmw on 2018/12/25.
 * Desc:
 */
public class TestInterrupted {

    static class Runner extends Thread {
        @Override
        public void run() {
            while (true) {
                System.out.println("hello");
                if (this.isInterrupted()) {
                    System.out.println("Interruted!");
                    break;
                }
            }
        }
    }

    public static void main(String[] args) {
        Runner runner = new Runner();
        Thread thread = new Thread(runner);
        thread.start();
        thread.interrupt();
    }

}
