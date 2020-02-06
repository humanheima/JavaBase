package com.hm.collection;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by dmw on 2018/12/30.
 * Desc:
 */
public class TreeMapTest {

    public static void main(String[] args) {

        //test0();
        test1();
    }

    private static void test1() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(11, 11);
        map.put(2, 2);
        map.put(14, 14);
        map.put(1, 1);
        map.put(7, 7);
        map.put(15, 15);
        map.put(5, 5);
        map.put(8, 8);
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            System.out.print(entry.getValue() + " ");
        }
        map.put(4, 4);

        System.out.println(map.firstEntry());
        System.out.println(map.firstKey());

        System.out.println(map.lastEntry());
        System.out.println(map.lastKey());
    }

    private static void test0() {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(10, 10);
        map.put(4, 4);
        map.put(14, 14);
        map.put(3, 3);
        map.put(6, 6);
        map.put(13, 13);
        map.put(18, 18);
        map.put(5, 5);
        map.put(8, 8);
        map.put(16, 16);
        map.put(19, 19);
        map.put(15, 15);
        map.put(17, 17);
        for (Map.Entry<Integer, Integer> integerIntegerEntry : map.entrySet()) {
            Integer value = integerIntegerEntry.getValue();
            System.out.print(value + " ");
        }
       /* map.get(10);

        map.get(4);

        map.get(14);

        map.get(3);

        map.get(6);

        map.get(13);

        map.get(18);

        map.get(5);

        map.get(8);

        map.get(16);

        map.get(19);

        map.get(15);

        map.get(17);*/

        map.remove(13);
        System.out.println();

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {

            System.out.print(entry.getValue() + " ");
        }
    }
}
