package com.hm.leetcode.code24;

import com.hm.algorithm.ListNode;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by p_dmweidu on 2025/7/27
 * Desc: https://leetcode.cn/problems/swap-nodes-in-pairs/description/
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 */
class Code24 {

    public static void main(String[] args) {
        Code24 solution = new Code24();

        // 测试用例1：[1,2,3,4]
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        System.out.println("Test Case 1: [1,2,3,4]");
        printResult(solution.swapPairs(l1));

        // 测试用例2：[]
        ListNode l2 = null;
        System.out.println("Test Case 2: []");
        printResult(solution.swapPairs(l2));

        // 测试用例3：[1]
        ListNode l3 = new ListNode(1);
        System.out.println("Test Case 3: [1]");
        printResult(solution.swapPairs(l3));

        // 测试用例4：[1,2,3]
        ListNode l4 = new ListNode(1, new ListNode(2, new ListNode(3)));
        System.out.println("Test Case 4: [1,2,3]");
        printResult(solution.swapPairs(l4));

        // 递归解法交叉验证：[1,2,3,4,5]
        ListNode l5 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println("Test Case 5 (递归): [1,2,3,4,5]");
        printResult(solution.swapPairsRecursive(l5));
    }

    // 打印链表结果
    private static void printResult(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        System.out.println("Result: " + result);
        System.out.println();
    }


    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        // 哑节点简化操作
        ListNode dummy = new ListNode(0);
        ListNode prev = dummy;
        prev.next = head;

        ListNode first = head;
        ListNode second = head.next;

        //1 -> 2 -> 3 -> 4
        //0 -> 1 -> 2 -> 3 -> 4
        /**
         *  1 -> 3 -> 4
         *       ↑
         *  0 -> 2
         *
         *  0 -> 2 -> 1 -> 3 -> 4
         *
         *
         */

        while (first != null && second != null) {
            first.next= second.next;
            prev.next= second;
            second.next= first;

            prev = prev.next.next;
            first = prev.next;
            if (first != null) {
                second = first.next;
            }
        }
        return dummy.next;
    }

    /**
     * 解法二：递归
     * 每次处理最前面两个结点 first、second，交换它们，
     * 剩余部分递归交换后接到 first 之后。
     * 时间 O(n)，空间 O(n)（递归栈）。
     */
    public ListNode swapPairsRecursive(ListNode head) {
        // 不足两个结点，无需交换
        if (head == null || head.next == null) {
            return head;
        }
        ListNode first = head;
        ListNode second = head.next;
        // 交换后 second 成为新头，first 接到「剩余部分递归处理后的结果」
        first.next = swapPairsRecursive(second.next);
        second.next = first;
        return second;
    }
}