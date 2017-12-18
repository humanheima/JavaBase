package com.hm.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/12/12 0012.
 */
public class GenericWriting {

    private static <T> void writeExact(List<T> list, T item) {
        list.add(item);
    }

    private static List<Apple> apples = new ArrayList<>();
    private static List<Fruit> fruits = new ArrayList<>();

    private static void f1() {
        writeExact(apples, new Apple());
        writeExact(fruits, new Apple());
    }

    private static <T> void writeWithWildcard(List<? super T> list, T item) {
        list.add(item);
    }

    private static void f2(){
        writeWithWildcard(apples,new Apple());
        //writeWithWildcard(apples,new Fruit());//
    }


    public static void main(String[] args) {
        f1();
        f2();
    }

}
