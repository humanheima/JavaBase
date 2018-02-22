package com.hm.pattern.proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 代理类：代理类同样也要实现IShop接口，并且要持有被代理者
 */
public class Purchasing implements IShop {

    private IShop mShop;

    public Purchasing(IShop iShop) {
        this.mShop = iShop;
    }

    @Override
    public void buy() {
        mShop.buy();
    }
}
