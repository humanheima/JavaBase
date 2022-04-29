package com.hm.picture_of_patten.factoryMethod;

import com.hm.picture_of_patten.factoryMethod.framework.Factory;
import com.hm.picture_of_patten.factoryMethod.framework.Product;
import com.hm.picture_of_patten.factoryMethod.idcard.IDCardFactory;

/**
 * framework层不依赖任何具体的类，
 */
public class Main {

    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("小明");
        Product card2 = factory.create("小红");
        Product card3 = factory.create("小刚");
        card1.use();
        card2.use();
        card3.use();
    }

}
