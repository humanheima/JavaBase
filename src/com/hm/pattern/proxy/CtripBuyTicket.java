package com.hm.pattern.proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 代理类：代理类同样也要实现BuyTicket接口，并且要持有被代理者
 */
public class CtripBuyTicket implements BuyTicket {

    /**
     * 被代理者，小赤佬
     */
    private BuyTicket xiaochilao;

    public CtripBuyTicket(BuyTicket buyTicket) {
        this.xiaochilao = buyTicket;
    }

    @Override
    public void buy() {
        System.out.println("30块钱到手，哈哈");
        xiaochilao.buy();
    }
}
