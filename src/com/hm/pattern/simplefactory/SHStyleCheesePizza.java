package com.hm.pattern.simplefactory;

/**
 * Created by dumingwei on 2017/6/28.
 */
public class SHStyleCheesePizza extends Pizza {

    @Override
    protected void cut() {
        System.out.println("上海披萨切片");
    }
}
