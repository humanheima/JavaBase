package com.hm.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class LinkedHashMapTest {

    static final int MAXIMUM_CAPACITY = 1 << 30;

    public static void main(String[] args) {
        /**
         * 指定访问顺序（true 表示按访问顺序，false 表示按插入顺序）
         * 访问顺序模式
         * 除了插入顺序，LinkedHashMap 还支持访问顺序（access order）。
         * 通过将构造方法的 accessOrder 参数设置为 true，可以让 LinkedHashMap 按照访问顺序排序，即最近访问的元素会被移动到链表的末尾。
         * 这种特性非常适合实现 LRU（Least Recently Used，最近最少使用）缓存。
         */

        //insertOrder();

        accessOrder();

    }

    public static void insertOrder() {
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("Apple", 1);
        map.put("Banana", 2);
        map.put("Orange", 3);

        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

    public static void accessOrder() {
        // 设置访问顺序，容量为 3
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>(3, 0.75f, true);
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);

        System.out.println("初始顺序: " + map);

        // 访问 A，A 会被移到末尾
        map.get("A");
        System.out.println("访问 A 后: " + map);

        // 添加 D，超出容量时移除最早未访问的元素，参考同目录下的 LRUCache 类。
        map.put("D", 4);
        System.out.println("添加 D 后: " + map);
    }

}
