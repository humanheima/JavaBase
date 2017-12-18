package com.hm.generic;

/**
 * Created by dumingwei on 2017/12/2 0002.
 */
public class CountedObject {

    private static long counter = 0;
    private final long id = counter++;

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "CountedObject" + id;
    }
}

