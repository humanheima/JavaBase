package com.hm.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/11/29 0029.
 */
public class GenericVarargs {

    public static <T> List<T> makeList(T... args) {
        List<T> result = new ArrayList<T>();
        for (T arg : args) {
            result.add(arg);
        }
        return result;
    }

    public static void main(String[] args) {
        List<String> ls = makeList("A");
        System.out.println(ls);
        ls=makeList("a","b","c");
        System.out.println(ls);
        ls=makeList("ABCDEFGHIJKLMNOPQRSTUVWXYZ".split(""));
        System.out.println(ls);
    }
}
