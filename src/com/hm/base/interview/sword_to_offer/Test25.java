package com.hm.base.interview.sword_to_offer;


import com.hm.algorithm.ListNode;

/**
 * Created by dumingwei on 2018/12/10
 * <p>
 * Desc:输入两个递增排序的链表，合并这两个链表并使新链表中的结点仍然是按照递增排序的
 * <p>
 * 测试用例
 * 1. 两个链表都是null
 * 2. 其中一个为null
 * 3. 两个链表都只有一个节点
 * 4. 正常的节点
 *
 * <p>
 * 参考链接：https://blog.csdn.net/DERRANTCM/article/details/46678155
 */
public class Test25 {


    /**
     * 使用循环的方式来实现
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode merge(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        // 创建一个临时结点，用于添加元素时方便
        ListNode root = new ListNode();
        // 用于指向合并后的新链的尾结点
        ListNode pointer = root;
        while (head1 != null && head2 != null) {
            // 下面的操作合并较小的元素
            if (head1.val < head2.val) {
                pointer.next = head1;
                head1 = head1.next;
            } else {
                pointer.next = head2;
                head2 = head2.next;
            }
            pointer = pointer.next;
        }
        //下面的两个if有且只一个if会内的内容会执行

        //如果第一个链表的元素未处理完,将其接到合并链表的最后一个结点之后
        if (head1 != null) {
            pointer.next = head1;
        }
        //如果第二个链表的元素未处理完,将其接到合并链表的最后一个结点之后
        if (head2 != null) {
            pointer.next = head2;
        }
        return root.next;
    }

    /**
     * 使用递归方式实现
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode merge2(ListNode head1, ListNode head2) {
        if (head1 == null) {
            return head2;
        }
        if (head2 == null) {
            return head1;
        }
        ListNode mergedHead = null;
        if (head1.val < head2.val) {
            mergedHead = head1;
            mergedHead.next = merge2(head1.next, head2);

        } else {
            mergedHead = head2;
            mergedHead.next = merge2(head1, head2.next);
        }
        return mergedHead;
    }

    public static void main(String[] args) {
        //test0();
        //test1();
        //test2();
        //test3();
    }

    private static void test1() {
        ListNode head = merge(null, null);
        printList(head);
    }

    private static void test2() {
        ListNode head = new ListNode();
        head.val = 1;
        ListNode mergedHead = merge(head, null);
        printList(mergedHead);
    }

    private static void test3() {
        ListNode head = new ListNode();
        head.val = 1;
        ListNode head2 = new ListNode();
        head2.val = 1;
        ListNode mergedHead = merge(head, head2);
        printList(mergedHead);
    }

    private static void test0() {
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


        ListNode head2 = new ListNode();
        head2.val = 1;

        head2.next = new ListNode();
        head2.next.val = 3;

        head2.next.next = new ListNode();
        head2.next.next.val = 5;

        head2.next.next.next = new ListNode();
        head2.next.next.next.val = 6;

        head2.next.next.next.next = new ListNode();
        head2.next.next.next.next.val = 7;

//        head = merge(head, head2);
        head = merge2(head, head2);
        printList(head);
    }

    /**
     * 输出链表的元素值
     *
     * @param head 链表的头结点
     */
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val + "->");
            head = head.next;
        }
        System.out.println("null");
    }

}
