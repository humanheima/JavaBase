package com.hm.base.interview.sword_to_offer;

import java.util.Stack;

/**
 * Created by dmw on 2018/11/17.
 * Desc: 从尾到头打印链表
 * 使用栈或者递归的方式来实现
 */
public class Test6 {

    public static void main(String[] args) {
        LinkNode head = new LinkNode();
        head.value = 1;
        head.next = new LinkNode();
        head.next.value = 2;
        head.next.next = new LinkNode();
        head.next.next.value = 3;
        head.next.next.next = new LinkNode();
        head.next.next.next.value = 4;
        head.next.next.next.next = new LinkNode();
        head.next.next.next.next.value = 5;
        printListInverselyUseStack(head);
        System.out.println("\n---------------------------------");
        printListInverselyRecursively(head);

        new Test6().reversePrint(null);
    }

    public static class LinkNode {

        int value;
        LinkNode next;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }


    public int[] reversePrint(ListNode head) {
        System.out.println();
        int[] arr = {};
        System.out.println(arr.length);
        if (head == null) {
            return arr;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }
        int size = stack.size();
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = stack.pop().val;
        }
        return array;

    }


    /**
     * 使用栈来实现
     *
     * @param head
     */
    public static void printListInverselyUseStack(LinkNode head) {
        Stack<LinkNode> stack = new Stack<>();
        while (head != null) {
            stack.push(head);
            head = head.next;
        }
        LinkNode tmp;
        while (!stack.isEmpty()) {
            tmp = stack.pop();
            System.out.print(tmp.value + " ");
        }
    }

    /**
     * 递归实现
     *
     * @param head
     */
    public static void printListInverselyRecursively(LinkNode head) {
        if (head != null) {
            printListInverselyRecursively(head.next);
            System.out.print(head.value + " ");
        }

    }
}
