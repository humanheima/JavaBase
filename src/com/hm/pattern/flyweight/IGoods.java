package com.hm.pattern.flyweight;

/**
 * Created by dumingwei on 2018/2/26 0026.
 * 抽象享元角色是一个商品接口，它定义了showGoodsPrice方法用来展示商品的价格
 */
public interface IGoods {

    void showGoodsPrice(String name);
}
