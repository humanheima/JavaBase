package com.hm.picture_of_patten.observer;

import java.util.Random;

/**
 * Created by dumingwei on 2022/5/10
 * <p>
 * Desc:
 */
public class RandomNumberGenerator extends NumberGenerator {

    private Random random = new Random();   // 随机数生成器
    private int number;                     // 当前数值

    public int getNumber() {                // 获取当前数值
        return number;
    }

    public void execute() {
        for (int i = 0; i < 20; i++) {
            number = random.nextInt(50);
            notifyObservers();
        }
    }
}
