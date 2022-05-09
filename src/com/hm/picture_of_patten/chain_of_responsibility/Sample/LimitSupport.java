package com.hm.picture_of_patten.chain_of_responsibility.Sample;

/**
 * Created by dumingwei on 2022/5/8
 *
 * Desc:
 */
public class LimitSupport extends Support {
    private int limit;                              // 可以解决编号小于limit的问题
    public LimitSupport(String name, int limit) {   // 构造函数
        super(name);
        this.limit = limit;
    }
    protected boolean resolve(Trouble trouble) {    // 解决问题的方法
        if (trouble.getNumber() < limit) {
            return true;
        } else {
            return false;
        }
    }
}
