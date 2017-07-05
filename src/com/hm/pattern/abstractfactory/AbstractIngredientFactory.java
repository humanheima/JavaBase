package com.hm.pattern.abstractfactory;

/**
 * Created by dumingwei on 2017/6/29.
 * 抽象的获取原料的工厂
 */
public abstract class AbstractIngredientFactory {

    //提供面粉
    protected Flour createFlour() {

        return null;
    }

    //提供腊肉
    protected abstract Bacon createBacon();

    //提供番茄酱
    protected abstract Ketchup createKetchup();

    //提供芝士
    protected abstract Cheese createCheese();

}
