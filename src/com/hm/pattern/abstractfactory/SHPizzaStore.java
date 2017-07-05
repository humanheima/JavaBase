package com.hm.pattern.abstractfactory;

/**
 * Created by dumingwei on 2017/6/29.
 * 上海的披萨店
 */
public class SHPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        //上海的披萨店，制造披萨的时候，使用的是上海的原料工厂
        AbstractIngredientFactory factory = new SHIngredientFactory();
        if (type.equals("bacon")) {
            //腊肉披萨
            pizza = new BaconPizza(factory);
        } else if (type.equals("ketchup")) {
            //番茄酱披萨
            pizza = new KetchupPizza(factory);
        }
        return pizza;
    }
}
