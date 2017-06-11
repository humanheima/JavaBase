package com.hm.thread;

public class Park {

    private int state = 3;

    public synchronized void CarIn(int i) {
        try {
            Thread.sleep(100);
            while (state == 0) {
                System.out.println("目前空余车位为 0 请等待");
                wait();
            }
            System.out.println(i+"号车停车成功");
            state = state - 1;
            System.out.println("目前剩余车位： " + state);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void CarOut(int i) {
        try {
            Thread.sleep(100);
            while (state == 3) {
                wait();
            }
            System.out.println(i +"号车已驶出");
            state = state + 1;
            System.out.println("目前剩余车位： " + state);
            notifyAll();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
