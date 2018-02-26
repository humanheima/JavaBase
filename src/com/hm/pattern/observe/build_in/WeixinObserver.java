package com.hm.pattern.observe.build_in;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by dumingwei on 2018/2/26 0026.
 */
public class WeixinObserver implements Observer {

    private String name;

    public WeixinObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(Observable o, Object arg) {
        System.out.println(name + " receive  update message：" + arg);
    }
}
