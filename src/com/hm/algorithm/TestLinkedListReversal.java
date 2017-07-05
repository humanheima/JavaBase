package com.hm.algorithm;

import java.util.LinkedList;

/**
 * Created by dumingwei on 2017/6/18.
 * 测试单链表翻转
 */
public class TestLinkedListReversal {

    public static void main(String[] args) {
        Node head = init(10);
        out(reverseHead(head));
        LinkedList<String> linkedList = new LinkedList<>();
    }

    public static Node init(int num) {
        Node node = new Node(0, null);
        Node cur = null;
        Node temp;
        for (int i = 1; i < num; i++) {
            temp = new Node(i, null);
            if (i == 1) {
                node.next = temp;
            } else {
                cur.next = temp;
            }
            cur = temp;
        }
        return node;
    }

    private static void out(Node head) {
        Node tempNode = head;
        while (tempNode != null) {
            System.out.print(tempNode.value + " ");
            tempNode = tempNode.next;
        }
    }

    private static Node reverseHead(Node head) {
        if (head == null)
            return null;
        Node pre = head;
        Node cur = head.next;
        Node next;
        while (cur != null) {
            next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        head.next = null;
        head = pre;
        return head;
    }
}
