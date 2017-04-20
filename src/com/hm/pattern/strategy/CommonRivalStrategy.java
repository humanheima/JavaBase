package com.hm.pattern.strategy;

/**
 * Created by dumingwei on 2017/4/20.
 * 具体策略实现
 */
public class CommonRivalStrategy implements FightingStrategy {

    @Override
    public void fighting() {
        System.out.println("遇到普通的对手，张无忌使用圣火令的功夫");
    }
}
