package com.hm.generic.blog_demo;

import com.hm.clone.Dog;
import com.hm.reflect.Animal;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2020/10/27.
 * <p>
 * Desc:
 */
public class MainTest2 {


    public static void main(String[] args) {
        List<Cat> catList = new ArrayList<>();
        List<? extends Animal> list = catList;

        Herd<Dog> dogs = new Herd<>();
        // 不会报错
        //countLegs(dogs);
        // 报错
        feedAll(dogs);
        feedAll2(dogs);


        List<Object> objs = new ArrayList<>();
        List<? super Animal> animals = objs;

//        List<Animal> animalList = new ArrayList<>();
//        animalList.add(new Cat());
//        animalList.add(new Dog());
//        Animal animal = animalList.get(0);

    }

    public static void feedAll(Herd<? extends Animal> animals) {
        for (int i = 0; i < animals.getSize(); i++) {
            animals.get(i).feed();
        }
    }

    public static <T extends Animal> void feedAll2(Herd<T> animals) {
        for (int i = 0; i < animals.getSize(); i++) {
            animals.get(i).feed();
        }
    }

    public static int countLegs(Herd<Animal> animals) {
        int retVal = 0;

        return retVal;
    }

    public static <T extends Animal> int countLegs1(List<T> animals) {
        int retVal = 0;
        for (Animal animal : animals) {
            retVal += animal.countLegs();
        }
        return retVal;
    }

    static class Animal {

        public void feed() {

        }

        public int countLegs() {
            return 0;
        }
    }

    static class Dog extends Animal {

    }

    static class Cat extends Animal {

    }

    static class Herd<T extends Animal> {

        List<T> list = new ArrayList<>();

        public int getSize() {
            return list.size();
        }

        public T get(int i) {
            return list.get(i);
        }
    }

}





