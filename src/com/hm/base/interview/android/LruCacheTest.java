package com.hm.base.interview.android;

public class LruCacheTest {

    public static void main(String[] args) {

        LruCacheTest test = new LruCacheTest();
        test.test1();
    }

    private void test1() {
        LruCache cache = new LruCache(2); // 容量为2
        cache.put(1, 1); // 缓存是 {1=1}
        cache.put(2, 2); // 缓存是 {1=1, 2=2}
        int val = cache.get(1);    // 返回 1
        System.out.println(val);
        cache.put(3, 3); // 移除 key 2, 缓存是 {1=1, 3=3}
        int val2 = cache.get(2); // 返回 -1 (未找到)
        System.out.println(val2);
    }
}
