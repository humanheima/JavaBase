package com.hm.handwrite;

import com.hm.algorithm.ListNode;
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

    public static ListNode reverseList(ListNode head) {

        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }
        ListNode curr = head;
        ListNode reverseHead = curr;
        ListNode pre = null;
        ListNode next;

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
        head.val = 1;

        head.next = new ListNode();
        head.next.val = 2;

        head.next.next = new ListNode();
        head.next.next.val = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.val = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.val = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.val = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.val = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.val = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.val = 9;

        printList(head);
        head = reverseList(head);
        printList(head);
        head = reverseList(head);
        printList(head);
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }
}
