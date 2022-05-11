package com.hm.picture_of_patten.observer;

/**
 * Created by dumingwei on 2022/5/10
 * <p>
 * Desc:
 */
public class DigitObserver implements Observer {

    public void update(NumberGenerator generator) {
        System.out.println("DigitObserver:" + generator.getNumber());
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }
}
