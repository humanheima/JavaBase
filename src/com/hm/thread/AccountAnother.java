package com.hm.thread;

import java.util.Locale;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class AccountAnother {

    private final Lock lock = new ReentrantLock();
    private final Condition cond = lock.newCondition();
    private String accountNo;
    private double balance;
    private boolean flag = false;

    public AccountAnother(String accountNo, double balance) {
        this.accountNo = accountNo;
        this.balance = balance;
    }

    /**
     * 取钱
     *
     * @param drawAmount
     */
    public void draw(double drawAmount) {
        //加锁
        lock.lock();
        try {
            //flag为假，表明账户还没有人存钱进去，取钱方法阻塞
            if (!flag) {
                cond.await();
            } else {
                //执行取钱操作
                System.out.println(Thread.currentThread().getName() + "取钱:" + drawAmount);
                balance -= drawAmount;
                System.out.println("余额为：" + balance);
                flag = false;
                //唤醒其他线程
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 存钱
     *
     * @param depositAmount
     */
    public void deposit(double depositAmount) {
        lock.lock();
        try {
            //如果flag为真，表明账户中已有人存钱进去，存钱方法阻塞
            if (flag) {
                cond.await();
            } else {
                //执行存款操作
                System.out.println(Thread.currentThread().getName() + "存款:" + depositAmount);
                balance += depositAmount;
                System.out.println("余额为：" + balance);
                flag = true;
                //唤醒其他线程
                cond.signalAll();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
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
        if (obj != null && obj.getClass() == AccountAnother.class) {
            AccountAnother target = (AccountAnother) obj;
            return target.getAccountNo().equals(accountNo);
        }
        return false;
    }
}
