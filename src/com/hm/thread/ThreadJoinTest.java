package com.hm.thread;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by dumingwei on 2017/3/30.
 * 测试Thread 的join 方法
 */
public class ThreadJoinTest {

    public static void main(String args[]) {
        Thread thread = new Thread(new RunnableImpl());
        thread.start();
        try {
            //System.out.println("join begin" + System.currentTimeMillis());
            thread.join(0);
            System.out.println("join finish" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    static class RunnableImpl implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println("begin sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
                Thread.sleep(2000);
                System.out.println("end sleep" + DateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis())));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}