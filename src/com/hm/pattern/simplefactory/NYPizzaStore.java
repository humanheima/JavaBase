package com.hm.pattern.simplefactory;

/**
 * Created by dumingwei on 2017/6/28.
 */
public class NYPizzaStore extends PizzaStore {

    //实现createPizza(String type)方法
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new NYStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            return new NYStylePepperoniPizza();
        } else if (type.equals("clam")) {
            return new NYStyleClamPizza();
        } else if (type.equals("veggie")) {
            return new NYStyleVeggiePizza();
        } else {
            return null;
        }
    }
}
