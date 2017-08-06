package com.hm.pattern.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by dumingwei on 2017/7/6.
 * 动态代理类
 */
public class DynamicPurchasing implements InvocationHandler {

    //指向被代理类
    private Object obj;

    public DynamicPurchasing(Object obj) {
        this.obj = obj;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object result = method.invoke(obj, args);
        return result;
    }
}
