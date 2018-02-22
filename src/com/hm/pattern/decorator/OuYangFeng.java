package com.hm.pattern.decorator;

/**
 * Created by dumingwei on 2018/2/22 0022.
 */
public class OuYangFeng extends Master {

    public OuYangFeng(SwordMan swordMan) {
        super(swordMan);
    }

    public void teachAttackMagic() {
        System.out.println("欧阳锋教授蛤蟆功");
        System.out.println("杨过使用蛤蟆功");
    }

    @Override
    public void attackMagic() {
        super.attackMagic();
        teachAttackMagic();
    }
}
