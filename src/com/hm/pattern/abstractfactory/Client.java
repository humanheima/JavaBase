package com.hm.pattern.abstractfactory;


/**
 * Created by dumingwei on 2017/6/28.
 */
public class Client {

    public static void main(String[] args) {
        //在上海吃上海风味的披萨
        PizzaStore shStore = new SHPizzaStore();
        Pizza shCheesePizza = shStore.orderPizza("cheese");

        //在纽约吃纽约风味的披萨
        PizzaStore nyStore = new NYPizzaStore();
        Pizza nyCheesePizza = nyStore.orderPizza("cheese");
    }
}
