package com.hm.pattern.strategy;

/**
 * Created by dumingwei on 2017/4/20.
 * 客户端调用
 * 张无忌会对不同实力层次的对手，采用了不同的策略来应战,为了举例，这里省略了对不同实力层次进行判断的条件语句
 */
public class ZhangWuji {

    public static void main(String[] args) {
        Context context;
        //如果遇到弱的对手
        context = new Context(new WeakRivalStrategy());
        context.fighting();
        //如果遇到普通对手
        context = new Context(new CommonRivalStrategy());
        context.fighting();
        //如果遇到很强的对手
        context = new Context(new StrongRivalStrategy());
        context.fighting();
    }
}
