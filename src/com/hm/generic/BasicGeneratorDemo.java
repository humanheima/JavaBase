package com.hm.generic;

import com.hm.generic.coffee.Generator;

/**
 * Created by dumingwei on 2017/12/2 0002.
 */
public class BasicGeneratorDemo {

    public static void main(String[] args) {
      /*  Generator<CountedObject> gen = BasicGenerator.create(CountedObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(gen.next());
        }*/
      Generator<CountedObject>generator=new BasicGenerator<>(CountedObject.class);
        for (int i = 0; i < 5; i++) {
            System.out.println(generator.next());
        }
    }
}
