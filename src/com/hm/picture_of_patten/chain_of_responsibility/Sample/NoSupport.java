package com.hm.picture_of_patten.chain_of_responsibility.Sample;

/**
 * Created by dumingwei on 2022/5/8
 *
 * Desc:
 */
public class NoSupport extends Support {
    public NoSupport(String name) {
        super(name);
    }
    protected boolean resolve(Trouble trouble) {     // 解决问题的方法
        return false; // 自己什么也不处理
    }
}
