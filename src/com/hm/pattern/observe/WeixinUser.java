package com.hm.pattern.observe;

/**
 * Created by dumingwei on 2018/2/26 0026.
 * 具体观察者
 */
public class WeixinUser implements Observer {

    private String name;

    public WeixinUser(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println("name-" + message);
    }
}
