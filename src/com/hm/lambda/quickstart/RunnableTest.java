package com.hm.lambda.quickstart;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class RunnableTest {

    public static void main(String[] args) {

        Runnable r1 = new Runnable() {
            @Override
            public void run() {
                System.out.printf("hello r1");
            }
        };
        r1.run();

        Runnable r2 = () -> System.out.println("hello r2");
        r2.run();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

            }
        });
        Thread thread = new Thread(() -> System.out.println("hahahha"));
    }
}
