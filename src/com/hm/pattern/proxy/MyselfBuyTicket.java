package com.hm.pattern.proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 真实的对象
 */
public class MyselfBuyTicket implements BuyTicket {

    @Override
    public void buy() {
        System.out.println("老板，我想买票");
    }

}
