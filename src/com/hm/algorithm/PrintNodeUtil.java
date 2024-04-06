package com.hm.algorithm;

/**
 * Created by dumingwei on 2024/4/6
 */
public class PrintNodeUtil {


    public static void printListNode(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode cur = head;
        while (cur != null) {
            System.out.println(cur.val);
            cur = cur.next;
        }
    }
}
