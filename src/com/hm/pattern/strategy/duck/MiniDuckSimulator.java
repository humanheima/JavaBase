package com.hm.pattern.strategy.duck;

public class MiniDuckSimulator {

    public static void main(String[] args) {

        MallardDuck mallard = new MallardDuck();
        mallard.performQuack();
        mallard.performFly();
        //RubberDuck rubberDuckie = new RubberDuck();
        //DecoyDuck decoy = new DecoyDuck();

        Duck model = new ModelDuck();

        // rubberDuckie.performQuack();
        //decoy.performQuack();

        model.performFly();
        model.setFlyBehavior(new FlyRocketPowered());
        model.performFly();
    }
}
