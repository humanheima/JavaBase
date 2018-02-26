package com.hm.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dumingwei on 2018/2/26 0026.
 * 享元工厂GoodsFactory 用来创建Goods对象。通过Map容器来存储Goods对象，
 * 将内部状态name作为Map的key，以便标识Goods对象。如果Map容器中包含此key，
 * 则使用Map容器中存储的Goods对象，否则就新创建Goods对象，并放入Map容器中。
 */
public class GoodsFactory {

    private static Map<String, Goods> pool = new HashMap<>();

    public static Goods getGoods(String name) {
        if (pool.containsKey(name)) {
            System.out.println("使用缓存，key为：" + name);
            return pool.get(name);
        } else {
            Goods goods = new Goods(name);
            pool.put(name, goods);
            System.out.println("创建商品：key为：" + name);
            return goods;
        }
    }
}
