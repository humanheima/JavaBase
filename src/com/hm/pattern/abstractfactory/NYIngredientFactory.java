package com.hm.pattern.abstractfactory;

/**
 * Created by dumingwei on 2017/6/29.
 * 纽约的获取原料工厂
 */
public class NYIngredientFactory extends AbstractIngredientFactory {

    @Override
    protected Bacon createBacon() {
        //甜味腊肉
        return new SweetyBacon();
    }

    @Override
    protected Ketchup createKetchup() {
        //沙拉番茄酱
        return new SaladKetchup();
    }

    @Override
    protected Cheese createCheese() {
        //蓝莓芝士
        return new BlueberryCheese();
    }
}
