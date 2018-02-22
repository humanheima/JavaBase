package com.hm.pattern.decorator;

/**
 * Created by dumingwei on 2018/2/22 0022.
 * 经过洪七公和欧阳锋的教导，杨过除了初始武学全真剑法又学会了打狗棒法和蛤蟆功
 */
public class Client {

    public static void main(String[] args) {
        YangGuo yangGuo = new YangGuo();
        HongQiGong hongQiGong = new HongQiGong(yangGuo);
        hongQiGong.attackMagic();
        System.out.println("---------------------------------");
        OuYangFeng ouYangFeng = new OuYangFeng(yangGuo);
        ouYangFeng.attackMagic();
    }
}
