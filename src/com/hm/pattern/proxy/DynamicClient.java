package com.hm.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by dumingwei on 2017/7/6.
 */
public class DynamicClient {

    public static void main(String[] args) {
        IShop liuwangshu = new LiuWangshu();
        //创建动态代理
        DynamicPurchasing dynamicPurchasing = new DynamicPurchasing(liuwangshu);
        //创建LiuWangShu的ClassLoader
        ClassLoader loader = liuwangshu.getClass().getClassLoader();
        //动态创建代理类
        IShop purchasing = (IShop) Proxy.newProxyInstance(loader, new Class[]{IShop.class}, dynamicPurchasing);
        purchasing.buy();
    }
}
