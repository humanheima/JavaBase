package com.hm.pattern.abstractfactory;

/**
 * Created by dumingwei on 2017/6/29.
 * 高质量的Pizza
 */
public abstract class Pizza {

    protected String name;
    //腊肉
    protected Bacon bacon;
    //番茄酱
    protected Ketchup ketchup;
    //大豆油
    protected Cheese cheese;

    //准备方法声明称抽象的，在这个方法中，需要收集比萨所需的原料，而这些原料来自原料工厂
    abstract void prepare();

    void bake() {
        System.out.println("Bake for 25 minutes at 350");
    }

    void cut() {
        System.out.println("Cutting the pizza into diagonal slices");
    }

    void box() {
        System.out.println("Place pizza in official PizzaStore box");
    }

    @Override
    public String toString() {
        return "[name:" + name + ",bacon:" + bacon + ",ketchup:" + ketchup + ",cheese:" + cheese + "]";
    }

}
