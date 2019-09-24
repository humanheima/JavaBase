package com.hm.thread;

/**
 * Created by dumingwei on 2018/3/4 0004.
 */
public class Test {

    public static void main(String[] args) {
        final InsertData insertData = new InsertData();
        new Thread() {
            @Override
            public void run() {
                insertData.insert();
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                InsertData.insert1();
            }
        }.start();
    }
}

class InsertData {
    public synchronized void insert() {
        System.out.println("执行insert");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("执行insert完毕");
    }

    public synchronized static void insert1() {
        System.out.println("执行insert1");
        System.out.println("执行insert1完毕");
    }
}
