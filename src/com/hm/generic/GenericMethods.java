package com.hm.generic;

/**
 * Created by dumingwei on 2017/11/29 0029.
 * 定义泛型方法，只需将泛型参数列表置于返回值之前
 */
public class GenericMethods {

    public <T> void f(T x) {
        System.out.println(x.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        GenericMethods gm = new GenericMethods();
        gm.f("");
        gm.f(1);
        gm.f(1.0);
        gm.f(1.0F);
        gm.f('c');
        gm.f(gm);
    }
}
