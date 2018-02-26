package com.hm.pattern.flyweight;

/**
 * Created by dumingwei on 2018/2/26 0026.
 */
public class Client {

    public static void main(String[] args) {
        Goods goods1 = GoodsFactory.getGoods("iphonex");
        goods1.showGoodsPrice("32GB");
        Goods goods2 = GoodsFactory.getGoods("iphonex");
        goods2.showGoodsPrice("32GB");
        Goods goods3 = GoodsFactory.getGoods("iphonex");
        goods1.showGoodsPrice("128GB");
    }
}
