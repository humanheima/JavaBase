package com.hm.base.interview.sword_to_offer;

import com.hm.algorithm.ListNode;

/**
 * Created by dumingwei on 2018/12/10
 * <p>
 * Desc:定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * <p>
 * 测试用例
 * 1. head 为null
 * 2. 只有一个节点
 * 3. 正常的链表
 * <p>
 * 参考链接：https://blog.csdn.net/DERRANTCM/article/details/46669039
 */


public class Test24 {


    public ListNode reverseList2(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }
        //用于记录反转后的链表的头节点
        ListNode reverseHead = null;
        ListNode current = head;
        ListNode pre = null;
        ListNode next;


        while (current != null) {
            reverseHead = current;
            next = current.next;
            current.next = pre;
            pre = current;
            current = next;
        }
        return reverseHead;
    }


    public static ListNode reverseList(ListNode head) {

        //用于记录反转后的链表的头节点
        ListNode reverseHead = null;
        //用于记录当前处理的节点
        ListNode curr = head;
        //用于记录当前节点的前驱节点
        ListNode prev = null;
        //用于记录当前节点的下一个节点
        ListNode next = null;

        while (curr != null) {
            //记录当前处理的节点，最后一个记录的节点就是反转后的头节点
            reverseHead = curr;
            next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;

        }
        return reverseHead;

    }

    /**
     * 输出链表的元素值
     *
     * @param head 链表的头节点
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        //test0();
        //test1();
        test2();

    }

    private static void test0() {
        ListNode head = reverseList(null);
        printList(head);
    }

    private static void test1() {
        ListNode head = new ListNode();
        head.val = 1;
        printList(head);
        head = reverseList(head);
        printList(head);
        head = reverseList(head);
        printList(head);
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


}
