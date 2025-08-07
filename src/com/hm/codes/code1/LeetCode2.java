package com.hm.codes.code1;

import com.hm.algorithm.ListNode;
import com.hm.algorithm.NodeUtil;

import static com.hm.algorithm.NodeUtil.createList;

/**
 * Created by p_dmweidu on 2025/3/28
 * Desc: 两数之和
 * review
 */
public class LeetCode2 {


    public static void main(String[] args) {

        LeetCode2 solution = new LeetCode2();
        // 测试用例1: 普通情况 [2,4,3] + [5,6,4] = [3,0,9]
        ListNode l1 = createList(new int[]{2, 4, 3});
        ListNode l2 = createList(new int[]{1, 6, 5});

        ListNode result1 = solution.addTwoNumbers(l1, l2);

        NodeUtil.printList(result1);

    }


    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) {
            return null;
        }
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        //进位
        int carry = 0;
        while (l1 != null || l2 != null || carry != 0) {
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;

            /**
             * 有可能是超过10的，取个位和进位，比如12
             * 个位 int digit = newVal % 10; = 1
             * 进位 carry = newVal / 10; =1
             */
            int newVal = val1 + val2 + carry;
            int digit = newVal % 10;
            carry = newVal / 10;
            ListNode node = new ListNode(digit);
            current.next = node;
            current = current.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        return dummy.next;
    }




}
