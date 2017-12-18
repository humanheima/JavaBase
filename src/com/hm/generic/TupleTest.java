package com.hm.generic;

/**
 * Created by dumingwei on 2017/11/30 0030.
 */
public class TupleTest {

    static TwoTuple<String, Integer> f() {
        return new TwoTuple<>("hi", 47);
    }

    static TwoTuple<String, Integer> f2() {
        return Tuple.tuple("hi", 47);
    }

    static ThreeTuple<Amphibian, String, Integer> g() {
        //return new ThreeTuple<>(new Amphibian(), "hi", 47);
        return Tuple.tuple(new Amphibian(), "hi", 47);
    }

    static ForthTuple<Vehicle, Amphibian, String, Integer> h() {
        //return new ForthTuple<>(new Vehicle(), new Amphibian(), "hi", 47);
        return Tuple.tuple(new Vehicle(), new Amphibian(), "hi", 47);
    }

    static FiveTuple<Vehicle, Amphibian, String, Integer, Double> k() {
        //return new FiveTuple<>(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
        return Tuple.tuple(new Vehicle(), new Amphibian(), "hi", 47, 11.1);
    }

    public static void main(String[] args) {
        TwoTuple<String, Integer> ttsi = f();
        System.out.println(ttsi);
        System.out.println(f2());
        System.out.println(g());
        System.out.println(h());
        System.out.println(k());
    }
}

class Amphibian {
}

class Vehicle {
}
