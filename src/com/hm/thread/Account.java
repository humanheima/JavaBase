package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class Account {

    private String accountNo;
    private double balance;
    private boolean flag = false;

    public Account(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    /**
     * 取钱
     *
     * @param drawAmount
     */
    public synchronized void draw(double drawAmount) {
        try {
            //flag为假，表明账户还没有人存钱进去，取钱方法阻塞
            if (!flag) {
                wait();
            } else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName() + "取钱:" + drawAmount);
                balance -= drawAmount;
                System.out.println("余额为：" + balance);
                flag = false;
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 存钱
     *
     * @param depositAmount
     */
    public synchronized void deposit(double depositAmount) {
        try {
            //如果flag为真，表明账户中已有人存钱进去，存钱方法阻塞
            if (flag) {
                wait();
            } else {
                //执行存款操作
                System.out.println(Thread.currentThread().getName() + "存款:" + depositAmount);
                balance += depositAmount;
                System.out.println("余额为：" + balance);
                flag = true;
                notifyAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public int hashCode() {
        return accountNo.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj != null && obj.getClass() == Account.class) {
            Account target = (Account) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
}
