package com.hm.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2020/10/26.
 *
 * Desc:
 */
public class GenericTest {
    public static void main(String[] args) {
        List<Dog> dogs = new ArrayList<>();
        List<? extends Animal> animals = new ArrayList<>();
        animals = dogs; //complie error
        test(dogs);
    }

    private static <T extends Animal> void test(List<T>list){


    }
}

class Animal {

}

class Dog extends Animal {
}

