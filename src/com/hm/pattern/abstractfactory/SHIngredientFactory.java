package com.hm.pattern.abstractfactory;

/**
 * Created by dumingwei on 2017/6/29.
 * 上海的获取原料工厂
 */
public class SHIngredientFactory extends AbstractIngredientFactory {


    @Override
    protected Bacon createBacon() {
        //咸味腊肉
        return new SaltyBacon();
    }

    @Override
    protected Ketchup createKetchup() {
        //大蒜番茄酱
        return new GarlicKetchup();
    }

    @Override
    protected Cheese createCheese() {
        //奶酪芝士
        return new ReggianoCheese();
    }
}
