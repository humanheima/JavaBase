package com.hm.base.interview.sword_to_offer;

/**
 * Crete by dumingwei on 2020-02-09
 * Desc: 链表每k位逆序
 */
public class ReverseLinkEveryKNode {

    public static class ListNode {

        int value;

        public ListNode(int value) {
            this.value = value;
        }

        ListNode next;
    }

    public static void main(String[] args) {

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n8;

        ListNode head = reverseKGroup(n1, 3);

        while (head != null) {
            System.out.println(head.value);
            head = head.next;
        }
    }

    //k个为一组逆序
    public static ListNode reverseKGroup(ListNode head, int k) {
        ListNode temp = head;
        for (int i = 1; i < k && temp != null; i++) {
            temp = temp.next;
        }
        //判断节点的数量是否能够凑成一组
        if (temp == null)
            return head;

        ListNode t2 = temp.next;
        temp.next = null;
        //把当前的组进行逆序
        ListNode newHead = reverseList1(head);
        //把之后的节点进行分组逆序
        ListNode newTemp = reverseKGroup(t2, k);
        // 把两部分连接起来
        head.next = newTemp;

        return newHead;
    }

    /**
     * 反转
     * @param head
     * @return
     */
    public static ListNode reverseList1(ListNode head) {

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

    //逆序单链表
    private static ListNode reverseList(ListNode head) {
        if (head == null || head.next == null)
            return head;
        ListNode result = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return result;
    }

}
