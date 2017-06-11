package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class DepositThread extends Thread {

    private AccountAnother account;
    //当前线程希望取的钱数
    private double depositAmount;

    public DepositThread(String name, AccountAnother account, double depositAmount) {
        super(name);
        this.account = account;
        this.depositAmount = depositAmount;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            account.deposit(depositAmount);
        }
    }
}
