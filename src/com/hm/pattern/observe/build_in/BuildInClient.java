package com.hm.pattern.observe.build_in;

import java.util.Observable;

/**
 * Created by dumingwei on 2018/2/26 0026.
 * 测试java.util包下的观察者
 */
public class BuildInClient {

    public static void main(String[] args) {

        WeixinObserver observer1 = new WeixinObserver("乔峰");
        WeixinObserver observer2 = new WeixinObserver("虚竹");
        WeixinObserver observer3 = new WeixinObserver("段誉");

        MyObservable wexinObservable = new MyObservable();
        wexinObservable.addObserver(observer1);
        wexinObservable.addObserver(observer2);
        wexinObservable.addObserver(observer3);
        wexinObservable.setChanged();
        wexinObservable.notifyObservers("公众号更新了");
    }
}
