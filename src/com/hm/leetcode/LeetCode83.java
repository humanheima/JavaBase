package com.hm.leetcode;

import com.hm.algorithm.ListNode;

/**
 * Created by p_dmweidu on 2025/4/4
 * Desc: 删除有序链表中的重复项
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-list/
 */
class LeetCode83 {

    public static void main(String[] args) {
        LeetCode83 solution = new LeetCode83();

        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(1);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(2);
        ListNode n5 = new ListNode(5);
        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;

        ListNode result = solution.deleteDuplicates(n1);
        ListNode.printList(result);

    }

    /**
     * 想复杂了
     * @param head
     * @return
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(Integer.MIN_VALUE);
        dummy.next = head;
        ListNode pre = dummy;
        ListNode cur = head;

        //1->1->3->3->4
        while (cur != null) {
            if (cur.next != null && cur.val == cur.next.val) {
                while (cur.next != null && cur.next.val == cur.val) {
                    cur = cur.next;
                }
                pre = pre.next;
                pre.next = cur.next;
            }
            pre = cur;
            cur = cur.next;
        }
        return dummy.next;


    }


    public ListNode deleteDuplicates(ListNode head) {
        // 边界情况：空链表或只有一个节点
        if (head == null || head.next == null) {
            return head;
        }

        // 当前指针
        ListNode current = head;

        // 遍历链表
        while (current != null && current.next != null) {
            // 如果当前节点和下一个节点值相同
            if (current.val == current.next.val) {
                // 跳过下一个节点（删除重复元素）
                current.next = current.next.next;
            } else {
                // 值不同，移动到下一个节点
                current = current.next;
            }

        }
        return head;
    }

}