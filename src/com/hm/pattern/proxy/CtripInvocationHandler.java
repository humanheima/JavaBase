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

    public void buy() {
        System.out.println("亲，已抢到第一车厢3号座位");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("30块钱到手，哈哈");
        return method.invoke(obj, args);
    }
}
