package com.hm.pattern.observe;

/**
 * Created by dumingwei on 2018/2/26 0026.
 */
public class Client {

    public static void main(String[] args) {
        WeixinSubject subject = new WeixinSubject();
        //创建微信用户
        WeixinUser user1 = new WeixinUser("杨影枫");
        WeixinUser user2 = new WeixinUser("月眉儿");
        WeixinUser user3 = new WeixinUser("紫轩");
        subject.addObserver(user1);
        subject.addObserver(user2);
        subject.addObserver(user3);
        subject.notifyObservers("刘望舒的专栏更新了");
    }

}
