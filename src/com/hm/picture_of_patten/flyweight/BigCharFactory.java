package com.hm.picture_of_patten.flyweight;

import java.util.HashMap;

/**
 * Created by dumingwei on 2022/5/11
 * <p>
 * Desc:
 */
public class BigCharFactory {

    // 管理已经生成的BigChar的实例
    private HashMap<String, BigChar> pool = new HashMap<>();
    // Singleton模式
    private static BigCharFactory singleton = new BigCharFactory();

    // 构造函数
    private BigCharFactory() {
    }

    // 获取唯一的实例
    public static BigCharFactory getInstance() {
        return singleton;
    }

    // 生成（共享）BigChar类的实例
    public synchronized BigChar getBigChar(char charname) {
        BigChar bc = pool.get("" + charname);
        if (bc == null) {
            bc = new BigChar(charname); // 生成BigChar的实例
            pool.put("" + charname, bc);
        }
        return bc;
    }
}
