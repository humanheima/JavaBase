package com.hm.base.interview;

/**
 * Created by dumingwei on 2017/10/1.
 */
public class TestAnonymousInnerClass {

    public static void main(String[] args) {
        new Thread(new MyRunnable() {
            @Override
            public void run() {

            }
        });
    }
}
