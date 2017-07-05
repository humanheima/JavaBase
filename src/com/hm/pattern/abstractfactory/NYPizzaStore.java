package com.hm.pattern.abstractfactory;

/**
 * Created by dumingwei on 2017/6/29.
 */
public class NYPizzaStore extends PizzaStore {

    @Override
    protected Pizza createPizza(String type) {
        Pizza pizza = null;
        //把工厂传给一个披萨，以便披萨从工厂中取得原料
        AbstractIngredientFactory factory = new NYIngredientFactory();
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
