package com.hm.base.interview.sword_to_offer;

import com.hm.algorithm.ListNode;

/**
 * Created by dumingwei on 2018/12/6
 * <p>
 * Desc:输入一个链表，输出该链表中倒数第 k 个节点．为了符合大多数人的习惯，本题从1 开始计数，即链表的尾节点是倒数第1 个节点．
 * 例如一个链表有6 个节点，从头节点开始它们的值依次是1->2->3->4->5->6->null。这个个链表的倒数第3 个节点是值为4 的节点．
 * <p>
 * 解题思路：为了实现只遍历链表一次就能找到倒数第 k 个节点，我们可以定义两个指针。第一个指针从链表的头指针开始遍历向前走k-1步，
 * 第二个指针保持不动；从第k 步开始，第二个指针也开始从链表的头指针开始遍历。由于两个指针的距离保持在k-1 ，
 * 当第一个（走在前面的）指针到达链表的尾节点时，第二个指针（走在后面的）指针正好是倒数第k 个节点。
 * <p>
 * 参考链接：
 * https://blog.csdn.net/derrantcm/article/details/46669025
 * [链表算法面试问题？看我就够了！](https://www.cxyxiaowu.com/1400.html)
 */
public class Test22 {


    public static void main(String[] args) {
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

        System.out.println(findKthToTail(head, 1).val); // 倒数第一个
        System.out.println(findKthToTail(head, 5).val); // 中间的一个
        System.out.println(findKthToTail(head, 9).val); // 倒数最后一个就是顺数第一个

        System.out.println(findKthToTail(head, 10));

        System.out.println(findKthToTail(head, 0));
        System.out.println(findKthToTail(null, 0));
    }


    /**
     * LeetCode 的解法
     *
     * @param head 头节点
     * @param k    倒数第k个节点
     * @return
     */
    public ListNode getKthFromEnd(ListNode head, int k) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fastPoint = head;
        ListNode slowPoint = head;
        while (fastPoint != null && k > 0) {
            fastPoint = fastPoint.next;
            k--;
        }
        if (fastPoint == null) {
            return null;
        }

        while (fastPoint != null && slowPoint != null) {
            fastPoint = fastPoint.next;
            slowPoint = slowPoint.next;
        }

        return slowPoint;
    }


    /**
     * 输入一个键表，输出该链表中倒数第k 个节点．为了符合大多数人的习惯，
     * 本题从1开始计数，即链表的尾节点是倒数第1个节点．例如一个链表有6个节点，
     * 从头节点开始它们的值依次是1、2、3、4、5 6。这个链表的倒数第3个节点是值为4的节点．
     *
     * @param head 链表的头节点
     * @param k    倒数第k个节点
     * @return 倒数第k个节点
     */
    public static ListNode findKthToTail(ListNode head, int k) {

        if (k < 1 || head == null) {
            return null;
        }
        ListNode pointer = head;
        // 倒数第k个节点与倒数第一个节点相隔k-1个位置
        // pointer先走k-1个位置
        for (int i = 1; i < k; i++) {
            if (pointer.next != null) {
                pointer = pointer.next;
            } else {
                // 已经没有节点了，但是i还没有到达k-1说明k太大，链表中没有那么多的元素
                return null;
            }
        }
        // pointer还没有走到链表的末尾，那么pointer和head一起走，
        // 当pointer走到最后一个节点即，pointer.next=null时，head就是倒数第k个节点
        while (pointer.next != null) {
            head = head.next;
            pointer = pointer.next;
        }
        return head;
    }


}
