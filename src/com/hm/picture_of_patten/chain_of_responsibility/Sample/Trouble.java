package com.hm.picture_of_patten.chain_of_responsibility.Sample;

/**
 * Created by dumingwei on 2022/5/8
 * <p>
 * Desc:
 */
public class Trouble {

    private int number;             // 问题编号

    public Trouble(int number) {    // 生成问题
        this.number = number;
    }

    public int getNumber() {        // 获取问题编号
        return number;
    }

    public String toString() {      // 代表问题的字符串
        return "[Trouble " + number + "]";
    }
}
