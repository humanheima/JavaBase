package com.hm.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by dumingwei on 2017/7/6.
 */
public class CtripInvocationHandler implements InvocationHandler {

    /**
     * 在动态代理类中我们声明一个Object的引用，该引用指向被代理类
     */
    private Object obj;

    public CtripInvocationHandler(Object obj) {
        this.obj = obj;
    }

    /**
     * @param proxy  代理对象
     * @param method 代理对象调用的方法
     * @param args   方法调用参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("服务费3块钱，美滋滋");
        //obj是被代理对象，反射啊，哈哈
        return method.invoke(obj, args);
    }
}
