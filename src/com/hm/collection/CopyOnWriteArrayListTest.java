package com.hm.collection;

import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by dumingwei on 2017/6/24.
 */
public class CopyOnWriteArrayListTest {

    private static CopyOnWriteArrayList<String> list;

    public static void main(String[] args) {

        list = new CopyOnWriteArrayList<>();
        list.add("one");
        list.add("two");
        list.add("three");
        list.get(0);

    }
}
