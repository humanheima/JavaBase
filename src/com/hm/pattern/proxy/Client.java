package com.hm.pattern.proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 测试静态代理
 */
public class Client {

    public static void main(String[] args) {
        BuyTicket xiaochilao = new MyselfBuyTicket();
        BuyTicket purchasing = new CtripBuyTicket(xiaochilao);
        purchasing.buy();
    }
}
