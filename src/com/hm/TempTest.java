package com.hm;

import com.hm.algorithm.ListNode;
import com.hm.algorithm.PrintNodeUtil;

import java.util.Stack;

public class TempTest {


    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.val = 1;

        head.next = new ListNode();
        head.next.val = 2;

        head.next.next = new ListNode();
        head.next.next.val = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.val = 4;

        //ListNode result = reverse(head);
        ListNode result = reverseKNode(head, 3);
        PrintNodeUtil.printListNode(result);

    }

    private static ListNode reverse(ListNode head) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        //1 -> 2 ->- 3 -> null
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        ListNode dummy = new ListNode(0);
        cur = dummy;
        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        cur.next = null;
        return dummy.next;
    }

    private static ListNode reverseKNode(ListNode head, int k) {
        if (head == null) {
            return null;
        }
        Stack<ListNode> stack = new Stack<>();

        ListNode dummy = new ListNode(0);

        ListNode cur2 = dummy;

        ListNode cur = head;
        //1 -> 2 ->- 3 ->4 ->5 -> null
        //核心算法
        int count = 0;
        while (cur != null) {
            ListNode temp = cur;
            while (temp != null && count < k) {
                stack.push(temp);
                temp = temp.next;
                count++;
            }
            if (count < k) {
                cur2.next = cur;
                break;
            }
            while (!stack.isEmpty()) {
                cur2.next = stack.pop();
                cur2 = cur2.next;
            }
            cur2.next = null;
            cur = temp;
            count = 0;
        }


        return dummy.next;

    }


}
