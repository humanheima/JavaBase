package com.hm.picture_of_patten.chain_of_responsibility.Sample;

/**
 * Created by dumingwei on 2022/5/8
 *
 * Desc:
 */
public class OddSupport extends Support {
    public OddSupport(String name) {                // 构造函数
        super(name);
    }
    protected boolean resolve(Trouble trouble) {    // 解决问题的方法
        if (trouble.getNumber() % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }
}
