package com.hm.generic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by dumingwei on 2017/12/4 0004.
 */
public class ArrayMaker<T> {

    private Class<T> kind;

    public ArrayMaker(Class<T> kind) {
        this.kind = kind;
    }

    T[] create(int size) {
        return (T[]) Array.newInstance(kind, size);
    }

    public static void main(String[] args) {
      ArrayMaker<String> stringMaker=new ArrayMaker<>(String.class);
      String[]stringArray=stringMaker.create(9);
        System.out.println(Arrays.toString(stringArray));
    }

}
