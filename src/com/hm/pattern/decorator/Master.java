package com.hm.pattern.decorator;

/**
 * Created by dumingwei on 2018/2/22 0022.
 * 抽象装饰者
 */
public abstract class Master extends SwordMan {

    private SwordMan mSwordMan;

    public Master(SwordMan swordMan) {
        this.mSwordMan = swordMan;
    }

    @Override
    public void attackMagic() {
        mSwordMan.attackMagic();
    }
}
