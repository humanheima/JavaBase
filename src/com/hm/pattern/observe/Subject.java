package com.hm.pattern.observe;

/**
 * Created by dumingwei on 2017/7/2.
 * 抽象被观察者
 */
public interface Subject {

    //添加观察者
    void addObserver(Observer observer);

    //删除观察者
    void deleteObserver(Observer observer);

    //通知所有的观察者有更新
    void notifyObservers(String message);

}
