package com.hm.pattern.abstractfactory;

/**
 * Created by dumingwei on 2017/6/29.
 * 腊肉披萨
 */
public class BaconPizza extends Pizza {

    AbstractIngredientFactory factory;

    /**
     * 要制作披萨，需要从工厂里获取优质原料，所以需要传入一个原料工厂实例
     *
     * @param factory 原料工厂
     */
    public BaconPizza(AbstractIngredientFactory factory) {
        this.factory = factory;
    }

    @Override
    void prepare() {
        System.out.println("Preparing" + name);
        //prepare方法从原料工厂获取原料，腊肉披萨需要芝士和腊肉
        cheese = factory.createCheese();
        bacon = factory.createBacon();
    }
}
