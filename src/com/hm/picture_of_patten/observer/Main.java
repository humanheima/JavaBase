package com.hm.picture_of_patten.observer;

/**
 * Created by dumingwei on 2022/5/10
 * <p>
 * Desc:
 */
public class Main {

    public static void main(String[] args) {
        NumberGenerator generator = new RandomNumberGenerator();
        Observer observer1 = new DigitObserver();
        Observer observer2 = new GraphObserver();
        generator.addObserver(observer1);
        generator.addObserver(observer2);
        generator.execute();
    }

}
