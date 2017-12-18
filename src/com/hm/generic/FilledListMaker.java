package com.hm.generic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public class FilledListMaker<T> {

    List<T> create(T t, int n) {
        List<T> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            result.add(t);
        }
        return result;
    }

    public static void main(String[] args) {
        FilledListMaker<String> stringListMaker = new FilledListMaker<>();
        List<String> stringList = stringListMaker.create("hello",4);
        System.out.println(stringList);
    }
}
