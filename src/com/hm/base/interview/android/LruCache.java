package com.hm.base.interview.android;

import java.util.HashMap;

/**
 * Created by p_dmweidu on 2025/7/24
 * Desc: 使用 HashMap 和 手写双向链表实现 LRU 缓存
 */
public class LruCache<K, V> {
    // Node class for doubly-linked list
    private class Node {
        K key;
        V value;
        Node prev;
        Node next;
        String name;
        
        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private final int capacity;
    private final HashMap<K, Node> cache;
    private final Node head; // Most recently used
    private final Node tail; // Least recently used
    
    public LruCache(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }
        this.capacity = capacity;
        this.cache = new HashMap<>();
        this.head = new Node(null, null); // Dummy head
        head.name = "head";
        this.tail = new Node(null, null); // Dummy tail
        tail.name = "tail";
        head.next = tail;
        tail.prev = head;
    }
    
    // Get value by key
    public V get(K key) {
        Node node = cache.get(key);
        if (node == null) {
            return null;
        }
        moveToFront(node);
        return node.value;
    }
    
    // Put key-value pair
    public void put(K key, V value) {
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            moveToFront(node);
        } else {
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            addToFront(newNode);
            
            if (cache.size() > capacity) {
                Node lru = removeLast();
                cache.remove(lru.key);
            }
        }
    }
    
    // Move node to front (most recently used)
    private void moveToFront(Node node) {
        removeNode(node);
        addToFront(node);
    }
    
    // Add node to front
    private void addToFront(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
    
    // Remove node from linked list
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // Remove and return the least recently used node
    private Node removeLast() {
        Node lru = tail.prev;
        removeNode(lru);
        return lru;
    }
}