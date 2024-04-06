package com.hm.handwrite;

import com.hm.algorithm.ListNode;
import com.hm.base.interview.sword_to_offer.Test24;
import com.hm.base.interview.sword_to_offer.Test24.*;

/**
 * Created by dumingwei on 2020/3/25
 * <p>
 * Desc: 反转单链表
 * https://www.jianshu.com/p/ec0d4677bdd8
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

        //用于记录反转后的链表的头节点
        ListNode reverseHead = null;
        ListNode pre = null;
        ListNode next;

        while (curr != null) {
            //记录当前处理的节点，最后一个记录的节点就是反转后的头节点
            reverseHead = curr;
            next = curr.next;
            curr.next = pre;
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

    private static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }
}
