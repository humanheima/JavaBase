package com.hm.generic;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dumingwei on 2017/12/12 0012.
 */
public class GenericReading {

    private static <T> T readExact(List<T> list) {
        return list.get(0);
    }

    private static List<Apple> apples = Arrays.asList(new Apple());
    private static List<Fruit> fruits = Arrays.asList(new Fruit());

    static void f1() {
        Apple a = readExact(apples);
        Fruit f = readExact(fruits);
        f = readExact(apples);
    }

    static class Reader<T> {

        T readExact(List<T> list) {
            return list.get(0);
        }

    }

    static void f2() {
        Reader<Fruit> fruitReader = new Reader<>();
        Fruit f = fruitReader.readExact(fruits);
        // Fruit a = fruitReader.readExact(apples);//error
    }

    static class CovariantReader<T> {

        T readCovariant(List<? extends T> list) {
            return list.get(0);
        }
    }

    static void f3() {
        CovariantReader<Fruit> fruitReader = new CovariantReader<>();
        Fruit f = fruitReader.readCovariant(fruits);
        Fruit a = fruitReader.readCovariant(apples);
    }

    public static void main(String[] args) {
        f1();
        f2();
        f3();
    }


}
