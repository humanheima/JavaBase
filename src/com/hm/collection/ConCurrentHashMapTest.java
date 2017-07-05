package com.hm.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dumingwei on 2017/6/19.
 */
public class ConCurrentHashMapTest {

    private static ConcurrentHashMap<Integer, Integer> hashMap;

    public static void main(String[] args) {
        hashMap = new ConcurrentHashMap<>();
        hashMap.get(0);
    }
}
