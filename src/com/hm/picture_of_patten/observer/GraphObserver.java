package com.hm.picture_of_patten.observer;

/**
 * Created by dumingwei on 2022/5/10
 *
 * Desc:
 */
public class GraphObserver implements Observer {

    public void update(NumberGenerator generator) {

        System.out.print("GraphObserver:");
        int count = generator.getNumber();
        for (int i = 0; i < count; i++) {
            System.out.print("*");
        }
        System.out.println("");
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }

    }
}
