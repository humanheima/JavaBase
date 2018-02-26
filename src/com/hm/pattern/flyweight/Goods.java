package com.hm.pattern.flyweight;

/**
 * Created by dumingwei on 2018/2/26 0026.
 * 具体享元角色 它实现IGoods 接口，并实现了showGoodsPrice方法其中name为内部状态，
 * version为外部状态。showGoodsPrice方法根据version的不同会打印出不同的价格。
 */
public class Goods implements IGoods {

    private String name;
    private String version;

    public Goods(String name) {
        this.name = name;
    }

    @Override
    public void showGoodsPrice(String version) {
        if (version.equals("32GB")) {
            System.out.println("价格为5199元");
        } else if (version.equals("128GB")) {
            System.out.println("价格为8888元");
        }
    }
}
