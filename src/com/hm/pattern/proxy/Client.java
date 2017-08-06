package com.hm.pattern.proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 静态代理
 */
public class Client {

    public static void main(String[] args) {
        IShop liuwangshu = new LiuWangshu();
        IShop purchasing = new Purchasing(liuwangshu);
        purchasing.buy();
    }
}
