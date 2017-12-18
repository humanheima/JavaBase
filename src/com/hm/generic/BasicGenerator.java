package com.hm.generic;

import com.hm.generic.coffee.Generator;

/**
 * Created by dumingwei on 2017/12/2 0002.
 */
public class BasicGenerator<T> implements Generator<T> {

    private Class<T> type;

    public BasicGenerator(Class<T> type) {
        this.type = type;
    }

    @Override
    public T next() {
        //assumes type is a public class
        try {
            return type.newInstance();
        } catch (Exception e) {
            throw new RuntimeException("BasicGenerator error in next() method");
        }
    }

    public static <T> Generator<T> create(Class<T> type) {
        return new BasicGenerator<>(type);
    }

}
