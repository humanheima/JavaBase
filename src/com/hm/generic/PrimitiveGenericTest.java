package com.hm.generic;

import com.hm.generic.coffee.Generator;

/**
 * Created by dumingwei on 2017/12/13 0013.
 */
public class PrimitiveGenericTest {

    public static void main(String[] args) {
    }
}

class FArray {

    public static <T> T[] fill(T[] a, Generator<T> gen) {
        for (int i = 0; i < a.length; i++) {
            a[i] = gen.next();
        }
        return a;
    }
}
