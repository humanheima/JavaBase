package com.hm.pattern.simplefactory;

/**
 * Created by dumingwei on 2017/6/28.
 */
public class SHPizzaStore extends PizzaStore {

    //实现createPizza(String type)方法
    @Override
    protected Pizza createPizza(String type) {
        if (type.equals("cheese")) {
            return new SHStyleCheesePizza();
        } else if (type.equals("pepperoni")) {
            return new SHStylePepperoniPizza();
        } else if (type.equals("clam")) {
            return new SHStyleClamPizza();
        } else if (type.equals("veggie")) {
            return new SHStyleVeggiePizza();
        } else {
            return null;
        }
    }
}
