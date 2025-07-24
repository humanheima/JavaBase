package com.hm.base.interview.android;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * Created by p_dmweidu on 2025/7/24
 * Desc: 使用 HashMap 和 LinkedList 实现 LRU 缓存
 */
public class LruCacheTest2<K, V> {


    // Node class to store key-value pair
    private static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final int capacity;
    private final Map<K, Node<K, V>> cache;
    private final LinkedList<Node<K, V>> list;

    // Constructor
    public LruCacheTest2(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.list = new LinkedList<>();
    }

    // Get value by key
    public V get(K key) {
        if (key == null) {
            return null;
        }
        Node<K, V> node = cache.get(key);
        if (node == null) {
            return null;
        }
        // Move to front (most recently used)
        list.remove(node);
        list.addFirst(node);
        return node.value;
    }

    // Put key-value pair
    public void put(K key, V value) {
        if (key == null || value == null) {
            return;
        }

        // Check if key already exists
        Node<K, V> node = cache.get(key);
        if (node != null) {
            // Update existing node
            node.value = value;
            list.remove(node);
            list.addFirst(node);
            return;
        }

        // Create new node
        Node<K, V> newNode = new Node<>(key, value);
        cache.put(key, newNode);
        list.addFirst(newNode);

        // Remove least recently used item if over capacity
        if (cache.size() > capacity) {
            Node<K, V> lruNode = list.removeLast();
            cache.remove(lruNode.key);
        }
    }

    // Get current size of cache
    public int size() {
        return cache.size();
    }
}
