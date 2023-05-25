package com.hm.base;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dumingwei on 2022/4/18.
 * 测试集合排序
 */
public class CollectionSortTest {


    public static void main(String[] args) {
        testSort();
    }

    /**
     * 测试排序
     */
    public static void testSort() {
        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(5);
        list.add(6);
        list.add(1);

        Collections.sort(list, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if ( o2>o1) {
                    return 1;

                } else if (o1.equals(o2)) {
                    return 0;
                } else {
                    return -1;
                }
            }
        });

        System.out.println(list);


    }
}
