package com.hm.generic.coffee;

/**
 * Created by Administrator on 2017/11/29 0029.
 */
public class Coffee {

    private static long counter = 0;
    private final long id = counter++;

    @Override
    public String toString() {
        return getClass().getSimpleName() + " " + id;
    }
}
