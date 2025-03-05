
package com.hm.collection;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * LinkedHashMap 实现 LRU 缓存
 * @param <K>
 * @param <V>
 */
public class LRUCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity, 0.75f, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity; // 当大小超过容量时移除最老的元素
    }

    public static void main(String[] args) {
        LRUCache<String, Integer> cache = new LRUCache<>(3);
        cache.put("A", 1);
        cache.put("B", 2);
        cache.put("C", 3);
        System.out.println(cache); // {A=1, B=2, C=3}

        cache.put("D", 4); // 超出容量，移除 A
        System.out.println(cache); // {B=2, C=3, D=4}
    }
}