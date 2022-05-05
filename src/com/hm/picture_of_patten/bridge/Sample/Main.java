package com.hm.picture_of_patten.bridge.Sample;


/**
 * Created by dumingwei on 2022/5/5
 * <p>
 * Desc:
 */
public class Main {

    public static void main(String[] args) {

        Display d1 = new Display(new StringDisplayImpl("Hello, China."));
        Display d2 = new CountDisplay(new StringDisplayImpl("Hello, World."));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello, Universe."));
        d1.display();
        d2.display();
        d3.display();
        d3.multiDisplay(5);
    }

}
