package com.hm.pattern.decorator;

/**
 * Created by dumingwei on 2018/2/22 0022.
 * 具体实现类
 */
public class YangGuo extends SwordMan {

    @Override
    public void attackMagic() {
        //杨过初始的武学是全真剑法
        System.out.println("杨过使用全真剑法");
    }
}
