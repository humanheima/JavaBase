package com.hm.pattern.decorator;

/**
 * Created by dumingwei on 2018/2/22 0022.
 */
public class HongQiGong extends Master {

    public HongQiGong(SwordMan swordMan) {
        super(swordMan);
    }

    public void teachAttackMagic() {
        System.out.println("洪七公教授打狗棒法");
        System.out.println("杨过使用打狗棒法");
    }

    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();
    }
}
