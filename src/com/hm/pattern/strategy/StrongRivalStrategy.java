package com.hm.pattern.strategy;

/**
 * Created by dumingwei on 2017/4/20.
 * 具体策略实现类
 */
public class StrongRivalStrategy implements FightingStrategy {

    @Override
    public void fighting() {
        System.out.println("遇到较强的对手，张无忌使用乾坤大挪移");
    }
}
