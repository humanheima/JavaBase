package com.hm.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2020/10/26.
 * <p>
 * Desc:
 */
public class GenericTest {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        List<? extends Animal> animals = new ArrayList<>();
        animals = dogs; //complie error
        test(dogs);
    }


    private void unSafeTest() {
        List<Dog> dogs = new ArrayList<>();

        /**
         * 1. 如果注释1处编译成功
         * 2. 那么我可以调用animals的add方法，添加一个monkey
         * 3. 调用dogs的get方法，这个时候获取的可能就不是Dog了。ClassCastException
         */
        //注释1处，将dogs赋值给animals；dogs 和 animals 指向同一个对象
        //List<Animal> animals = dogs;
        //注释2处，添加一个Monkey
        //animals.add(new Monkey());
        //注释3处
        //Dog dog = dogs.get(0);
    }

    private void safeTest() {
        List<Dog> dogs = new ArrayList<>();

        /**
         * 1. 注释1处可以编译成功
         * 2. 那么注释2处，add方法无法编译通过，也就是说animals无法添加对象，只能从中取数据，是安全的。
         */
        //注释1处，将dogs赋值给animals；dogs 和 animals 指向同一个对象
        List<? extends Animal> animals = dogs;
        //注释2处，取出来的就不是Dog了。
        //animals.add(new Monkey());
        if (!dogs.isEmpty()) {
            Dog dog = dogs.get(0);
        }
    }


    static <T extends Animal> void test(List<T> list) {


    }
}

class Animal {

}

class Dog extends Animal {
}

class Monkey extends Animal {

}

