package com.hm.collection;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dumingwei on 2017/6/19.
 * 不允许null值作为key和value
 * 还需要深入理解
 */

public class ConcurrentHashMapTest {

    private static ConcurrentHashMap<Integer, Integer> hashMap;

    public static void main(String[] args) {
        hashMap = new ConcurrentHashMap<>();
        hashMap.put(0, 0);
        hashMap.get(0);
        hashMap.remove(0);
        hashMap.size();
        System.out.println(16 - (16 >>> 2));
    }
}
