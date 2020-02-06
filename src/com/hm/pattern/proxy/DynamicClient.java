package com.hm.pattern.proxy;

import java.lang.reflect.Proxy;

/**
 * Created by dumingwei on 2017/7/6.
 * 测试动态代理
 * 首先通过newProxyInstance方法获取代理类实例，而后我们便可以通过这个代理类实例调用代理类的方法，
 * 对代理类的方法的调用实际上都会调用InvocationHandler的invoke方法，
 * 在invoke方法中我们调用委托类的相应方法，并且可以添加自己的处理逻辑。
 * <p>
 * 参考链接：https://juejin.im/post/5ad3e6b36fb9a028ba1fee6a
 */
public class DynamicClient {

    public static void main(String[] args) {

        //被代理对象
        BuyTicket xiaochilao = new MyselfBuyTicket();

        //创建调用处理者
        CtripInvocationHandler invocationHandler = new CtripInvocationHandler(xiaochilao);

        //创建动态代理对象
        BuyTicket purchasing = (BuyTicket) Proxy.newProxyInstance(
                xiaochilao.getClass().getClassLoader(),
                xiaochilao.getClass().getInterfaces(),
                invocationHandler);

        /**
         * 注意：这里调用的是代理对象的方法，该方法会转发到CtripInvocationHandler的invoke方法中，
         * 最终会调用被代理对象对应的方法。
         */
        purchasing.buy();

        //用来生成动态代理对象的class文件
        ProxyUtils.generateClassFile(xiaochilao.getClass(), "XiaochilaoProxy");
    }
}
