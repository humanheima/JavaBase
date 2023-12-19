package com.hm.pattern.strategy;

/**
 * Created by dumingwei on 2017/4/20.
 * 环境实现类
 * 环境类的构造函数包含了策略类，通过传进来不同的具体策略来调用不同策略的fighting方法：
 * 无论策略怎么变，这里的逻辑是不用动的。
 */
public class Context {

    private FightingStrategy fightingStrategy;

    public Context(FightingStrategy fightingStrategy) {
        this.fightingStrategy = fightingStrategy;
    }

    public void fighting() {
        fightingStrategy.fighting();
    }
}
