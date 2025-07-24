package com.hm.base.interview.android;

public class LruCacheTest {

    public static void main(String[] args) {

        LruCacheTest test = new LruCacheTest();
        test.test1();
    }

    private void test1() {
        LruCache<Integer, Integer> cache = new LruCache<>(4); // 容量为2
        cache.put(1, 1); // 缓存是 {1=1}
        cache.put(2, 2); // 缓存是 {1=1, 2=2}
        cache.put(3, 3); // 缓存是 {1=1, 2=2, 3=3}
        cache.put(4, 4); // 缓存是 {1=1, 2=2, 3=3, 4=4}
        cache.put(5, 5); // 缓存是 {2=2, 3=3, 4=4, 5=5}
        cache.put(6, 6); // 缓存是 {3=3, 4=4, 5=5, 6=6}

//        System.out.println(cache.get(1)); // 返回 1
//        cache.put(3, 3); // 移除键 2，缓存是 {1=1, 3=3}
//        System.out.println(cache.get(2)); // 返回 null
    }
}
