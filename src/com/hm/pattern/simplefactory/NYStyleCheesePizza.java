package com.hm.pattern.simplefactory;

/**
 * Created by dumingwei on 2017/6/28.
 */
public class NYStyleCheesePizza extends Pizza {

    @Override
    protected void cut() {
        System.out.println("纽约披萨切片");
    }
}
