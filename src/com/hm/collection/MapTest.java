package com.hm.collection;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class MapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        Map<String, Integer> map = new HashMap<>();
        map.put("java", 109);
        map.put("ios", 10);
        map.put("ajax", 79);
        map.put("java ee", 79);
      /*  System.out.println(map.put("ios", 99));
        System.out.println(map.containsKey("ios"));
        System.out.println(map.containsValue(99));*/
       /* for (String key : map.keySet()) {
            System.out.println(key+",value:"+map.get(key));
        }
        map.forEach(new BiConsumer<String, Integer>() {
            @Override
            public void accept(String s, Integer integer) {
                System.out.println(s+","+integer);
            }
        });
        map.remove("java");
        System.out.println(map);*/
        System.out.println(3|3);
       // System.out.println(tableSizeFor(20));
    }

    private static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    }
}
