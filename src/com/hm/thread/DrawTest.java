package com.hm.thread;

/**
 * Created by dumingwei on 2017/6/8.
 */
public class DrawTest {

    public static void main(String[] args) {
        AccountAnother account = new AccountAnother("1234567", 0);
        new DrawThread("杜明伟1", account, 800).start();
        //new DepositThread("杜明伟2",account,800).start();
    }

    public synchronized void test() {

    }
}

