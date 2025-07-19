package com.hm.base.interview.android;

import java.util.HashMap;

/**
 * Created by p_dmweidu on 2025/7/19
 * Desc:
 */
public class LruCache {

    // 节点类，用于双向链表
    private class Node {
        int key;
        int value;
        Node prev;
        Node next;
        
        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }
    
    private int capacity;
    private HashMap<Integer, Node> map;
    private Node head; // 虚拟头节点
    private Node tail; // 虚拟尾节点
    
    public LruCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);
        head.next = tail;
        tail.prev = head;
    }
    
    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        // 将访问的节点移到头部
        moveToHead(node);
        return node.value;
    }
    
    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            // 更新已有节点
            node.value = value;
            moveToHead(node);
        } else {
            // 创建新节点
            Node newNode = new Node(key, value);
            map.put(key, newNode);
            addToHead(newNode);
            
            // 检查是否超过容量
            if (map.size() > capacity) {
                // 移除尾部节点（最久未使用的）
                Node last = tail.prev;
                removeNode(last);
                map.remove(last.key);
            }
        }
    }
    
    // 将节点移到头部
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    
    // 移除节点
    private void removeNode(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }
    
    // 将节点添加到头部
    private void addToHead(Node node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }
}