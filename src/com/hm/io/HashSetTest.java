package com.hm.io;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by dumingwei on 2017/10/5.
 */
public class HashSetTest {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();
        set.add("a");
        set.add("b");
        set.add("c");
        set.add("c");
        for (String s : set) {
            System.out.println(s);
        }
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add(null);
    }
}
