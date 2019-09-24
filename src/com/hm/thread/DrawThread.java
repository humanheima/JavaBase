package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class DrawThread extends Thread {

    private AccountAnother account;
    //当前线程希望取的钱数
    private double drawAmount;

    public DrawThread(String name, AccountAnother account, double drawAmount) {
        super(name);
        this.account = account;
        this.drawAmount = drawAmount;
    }

    /*@Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            account.draw(drawAmount);
        }
    }*/
}
