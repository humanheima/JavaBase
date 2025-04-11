package com.hm.base.interview.android;


import com.hm.algorithm.ListNode;

import java.util.List;

/**
 * Created by p_dmweidu on 2025/4/11
 * Desc: 输入一个List<LinkedNode>，每一个元素都是升序的单链表，把List排序，输出合并后的一个单链表
 *
 * 合并排序List<LinkedNode>.md
 */
public class MergeSortKListNode {

    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.isEmpty()) {
            return null;
        }
        return mergeKListsHelper(lists, 0, lists.size() - 1);
    }

    // 分治法递归合并
    private ListNode mergeKListsHelper(List<ListNode> lists, int start, int end) {
        // 基本情况：只有一个链表
        if (start == end) {
            return lists.get(start);
        }
        // 基本情况：空区间
        if (start > end) {
            return null;
        }
        // 分成两半
        int mid = start + (end - start) / 2;
        // 递归合并左半部分
        ListNode left = mergeKListsHelper(lists, start, mid);
        // 递归合并右半部分
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        // 合并两个链表
        return mergeTwoLists(left, right);
    }

    // 合并两个升序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // 虚拟头节点
        ListNode current = dummy;

        // 比较并合并
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // 处理剩余节点
        if (l1 != null) {
            current.next = l1;
        }
        if (l2 != null) {
            current.next = l2;
        }

        return dummy.next;
    }
}