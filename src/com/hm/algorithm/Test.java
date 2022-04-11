package com.hm.algorithm;

public class Test {

    public static void main(String[] args) {

        String original = "123456";
        //fun(original);

        //test0();
        //test1();
        test2();


    }

    private static void test0() {
        Test test = new Test();
        ListNode reverseHead = test.reverseList(null);
        while (reverseHead != null) {
            System.out.print(reverseHead.val + " ");
            reverseHead = reverseHead.next;
        }
        System.out.println();
    }

    private static void test1() {
        Test test = new Test();
        ListNode head = new ListNode(1);
        ListNode reverseHead = test.reverseList(head);
        while (reverseHead != null) {
            System.out.print(reverseHead.val + " ");
            reverseHead = reverseHead.next;
        }
        System.out.println();
    }

    private static void test2() {
        Test test = new Test();
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);

        ListNode reverseHead = test.reverseList(head);
        while (reverseHead != null) {
            System.out.print(reverseHead.val + " ");
            reverseHead = reverseHead.next;
        }
        System.out.println();
    }

    public ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }
        if (head.next == null) {
            return head;
        }

        ListNode reverseHead = head;
        ListNode next = null;
        ListNode pre = null;

        /**
         * 1. 记录下一个节点next
         * 2. 将当前节点赋值给pre
         * 3. 将reverseHead置为next
         * 4. 修改reverseHead的next为pre
         * 5. 将head置为next，循环
         */
        while (head.next != null) {
            next = head.next;
            reverseHead.next = pre;
            reverseHead = next;
            pre = head;
            head.next = null;
            head = next;


        }
        return reverseHead;
    }


    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }


}
