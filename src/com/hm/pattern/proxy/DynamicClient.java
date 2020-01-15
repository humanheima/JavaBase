package com.hm.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 测试动态代理
 */
public class DynamicClient {

    public static void main(String[] args) {
        BuyTicket xiaochilao = new MyselfBuyTicket();
        /**
         *创建调用处理者
         */
        CtripInvocationHandler invocationHandler = new CtripInvocationHandler(xiaochilao);

        /**
         * 动态代理者
         */
        BuyTicket purchasing = (BuyTicket) Proxy.newProxyInstance(
                xiaochilao.getClass().getClassLoader(),
                xiaochilao.getClass().getInterfaces(),
                invocationHandler);
        /**
         *调用代理实例的buy()方法，该方法会转发到CtripInvocationHandler的invoke方法中，
         * 最终会调用被代理者xiaochilao的buy方法，实现真正的买票逻辑
         */
        purchasing.buy();

        ProxyUtils.generateClassFile(xiaochilao.getClass(), "XiaochilaoProxy");
    }
}
