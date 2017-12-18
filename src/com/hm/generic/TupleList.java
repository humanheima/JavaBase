package com.hm.generic;

import java.util.ArrayList;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public class TupleList<A, B, C, D> extends ArrayList<ForthTuple<A, B, C, D>> {

    public static void main(String[] args) {
        TupleList<Vehicle, Amphibian, String, Integer> list = new TupleList<>();
        list.add(TupleTest.h());
        list.add(TupleTest.h());
        for (ForthTuple<Vehicle, Amphibian, String, Integer> forthTuple : list) {
            System.out.println(forthTuple);
        }
    }
}
