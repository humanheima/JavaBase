package com.hm.picture_of_patten.observer;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by dumingwei on 2022/5/10
 * <p>
 * Desc:扮演的角色，被观察者
 */
public abstract class NumberGenerator {
    private ArrayList<Observer> observers = new ArrayList<>();        // 保存Observer们

    public void addObserver(Observer observer) {    // 注册Observer
        observers.add(observer);
    }

    public void deleteObserver(Observer observer) { // 删除Observer
        observers.remove(observer);
    }

    public void notifyObservers() {               // 向Observer发送通知
        for (Observer o : observers) {
            o.update(this);
        }
    }

    public abstract int getNumber();                // 获取数值

    public abstract void execute();                 // 生成数值

}
