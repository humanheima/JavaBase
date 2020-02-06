package com.hm.collection;

import java.util.HashMap;
import java.util.Hashtable;

/**
 * Created by dumingwei on 2017/6/18.
 */
public class HashTableTest {

    private static Hashtable<String, String> hashtable;
    private static HashMap<String, String> hashMap;

    public static void main(String[] args) {
        int i = 0;
        boolean x = i-- > 0;
        System.out.println(x);
        hashtable = new Hashtable<>();
        hashtable.put("String1", "string1");
        hashtable.put("String1", "string2");
        hashtable.get("String1");
        System.out.println(hashtable.toString());

        hashMap = new HashMap<>();
        hashMap.put("String1", "string1");
        hashMap.put("String1", "string2");
        System.out.println(hashMap.toString());

    }
}
