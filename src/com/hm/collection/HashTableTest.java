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
        hashtable = new Hashtable<>();
        hashtable.put("String1", "string1");
        hashtable.put("String1", "string1");
        hashtable.get("String1");
        hashtable.contains("");
        hashtable.clear();

        System.out.println(hashtable.toString());
    }
}
