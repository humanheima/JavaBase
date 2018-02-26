package com.hm.pattern.observe;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/7/2.
 * 具体被观察者
 */
public class WeixinSubject implements Subject {

    private List<Observer> observerList = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observerList.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        observerList.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observerList) {
            observer.update(message);
        }
    }
}
