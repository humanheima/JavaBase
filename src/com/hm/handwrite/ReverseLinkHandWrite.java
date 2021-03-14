package com.hm.handwrite;

import com.hm.base.interview.sword_to_offer.Test24;
import com.hm.base.interview.sword_to_offer.Test24.*;

/**
 * Created by dumingwei on 2020/3/25
 * <p>
 * Desc: 反转单链表
 */
public class ReverseLinkHandWrite {

    public static void main(String[] args) {

        test2();
    }

    public static Test24.ListNode reverseList(Test24.ListNode head) {

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        Test24.ListNode curr = head;
        Test24.ListNode reverseHead = curr;
        Test24.ListNode pre = null;
        Test24.ListNode next;

        while (curr != null) {
            next = curr.next;
            curr.next = pre;
            reverseHead = curr;
            pre = curr;
            curr = next;

        }

        return reverseHead;

    }

    private static void test2() {
        ListNode head = new ListNode();
        head.value = 1;

        head.next = new ListNode();
        head.next.value = 2;

        head.next.next = new ListNode();
        head.next.next.value = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.value = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.value = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.value = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.value = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.value = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.value = 9;

        printList(head);
        head = reverseList(head);
        printList(head);
        head = reverseList(head);
        printList(head);
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }
}
