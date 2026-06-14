package com.hm.leetcode.code160;

import com.hm.algorithm.ListNode;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dumingwei on 2022/4/15.
 * <p>
 * Desc: 160. 相交链表
 * 作者：LeetCode-Solution
 * 链接：https://leetcode-cn.com/problems/intersection-of-two-linked-lists/solution/xiang-jiao-lian-biao-by-leetcode-solutio-a8jn/
 * <p>
 * 推荐 hash 算法，或者双指针算法
 */
public class LeetCode160 {


    public static void main(String[] args) {
        new LeetCode160().test1();
    }

    private void test1() {
        ListNode headA = new ListNode(4);
        ListNode headA_1 = new ListNode(1);
        ListNode headA_2 = new ListNode(8);
        ListNode headA_3 = new ListNode(4);
        ListNode headA_4 = new ListNode(5);
        headA.next = headA_1;
        headA_1.next = headA_2;
        headA_2.next = headA_3;
        headA_3.next = headA_4;

        System.out.println("headA_2 = " + headA_2);

        ListNode headB = new ListNode(5);
        ListNode headB_1 = new ListNode(6);
        ListNode headB_2 = new ListNode(1);

        ListNode headB_3 = headA_2;
        ListNode headB_4 = headA_3;
        ListNode headB_5 = headA_4;

        headB.next = headB_1;
        headB_1.next = headB_2;
        headB_2.next = headB_3;
        headB_3.next = headB_4;
        headB_4.next = headB_5;

        System.out.println("headB_3 = " + headB_3);

        ListNode listNode = getIntersectionNode(headA, headB);

        System.out.println(listNode.val + " listNode = " + listNode);

    }

    /**
     * 使用HashSet的方式也挺好。
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        Set<ListNode> visited = new HashSet<>();
        ListNode temp = headA;
        while (temp != null) {
            visited.add(temp);
            temp = temp.next;
        }
        temp = headB;
        while (temp != null) {
            if (visited.contains(temp)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }

    /**
     * 双指针法，感觉也挺好
     *
     * @param headA
     * @param headB
     * @return
     */
    public ListNode getIntersectionNodeTowPoint(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode pA = headA;
        ListNode pB = headB;

        /**
         * 循环直到 pA == pB，返回结果（相交节点或 null）
         */
        while (pA != pB) {
            pA = (pA == null) ? headB : pA.next;
            pB = (pB == null) ? headA : pB.next;
        }

        return pA; // 返回相交节点或 null
    }

}
