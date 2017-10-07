package com.hm.collection;

import java.util.*;

/**
 * Created by dumingwei on 2017/10/4.
 */
public class CollectionsTest {

    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<String>());
        Map<Integer, String> map = Collections.synchronizedMap(new HashMap<Integer, String>());
        map.put(1, "1");
        map.put(2, "2");
        map.put(3, "1");
        for (String s : map.values()) {

        }
        synchronized (map) {
            Iterator<Integer> set = map.keySet().iterator();
            while (set.hasNext()) {
                System.out.println(map.get(set.next()));
            }
        }

    }
}
