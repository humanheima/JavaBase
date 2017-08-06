package com.hm.pattern.proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 真实主题类
 */
public class LiuWangshu implements IShop {

    @Override
    public void buy() {
        System.out.println("购买");
    }
}
