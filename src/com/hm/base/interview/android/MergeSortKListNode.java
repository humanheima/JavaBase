package com.hm.base.interview.android;


import com.hm.algorithm.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by p_dmweidu on 2025/4/11
 * Desc: 输入一个List<LinkedNode>，每一个元素都是升序的单链表，把List排序，输出合并后的一个单链表
 *
 * 合并排序List<LinkedNode>.md，使用分治法
 */
public class MergeSortKListNode {

    // 测试代码
    public static void main(String[] args) {
        MergeSortKListNode solution = new MergeSortKListNode();

        // 创建测试用例
        // 链表1: 1->4->5
        ListNode l1 = new ListNode(1);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(5);

        // 链表2: 1->3->4
        ListNode l2 = new ListNode(1);
        l2.next = new ListNode(3);
        l2.next.next = new ListNode(4);

        // 链表3: 2->6
        ListNode l3 = new ListNode(2);
        l3.next = new ListNode(6);

        // 将链表放入List
        List<ListNode> lists = new ArrayList<>();
        lists.add(l1);
        lists.add(l2);
        lists.add(l3);

        // 合并链表
        ListNode result = solution.mergeKLists(lists);

        // 打印结果
        System.out.print("Merged List: ");
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print("->");
            result = result.next;
        }
        System.out.println();

        // 测试空链表情况
        List<ListNode> emptyLists = new ArrayList<>();
        result = solution.mergeKLists(emptyLists);
        System.out.println("Empty lists result: " + (result == null ? "null" : "not null"));

        // 测试单一链表情况
        lists = new ArrayList<>();

        ListNode l4 = new ListNode(1);
        l4.next = new ListNode(4);
        l4.next.next = new ListNode(5);
        lists.add(l4);

        result = solution.mergeKLists(lists);
        System.out.print("Single list result: ");
        while (result != null) {
            System.out.print(result.val);
            if (result.next != null) System.out.print("->");
            result = result.next;
        }
        System.out.println();
    }

    // 主函数：合并多个有序链表
    public ListNode mergeKLists(List<ListNode> lists) {
        if (lists == null || lists.isEmpty()) return null;
        return mergeKListsHelper(lists, 0, lists.size() - 1);
    }

    // 分治法辅助函数
    private ListNode mergeKListsHelper(List<ListNode> lists, int start, int end) {
        if (start == end) {
            return lists.get(start);
        }
        if (start + 1 == end) {
            return mergeTwoLists(lists.get(start), lists.get(end));
        }

        int mid = start + (end - start) / 2;
        ListNode left = mergeKListsHelper(lists, start, mid);
        ListNode right = mergeKListsHelper(lists, mid + 1, end);
        return mergeTwoLists(left, right);
    }

    // 合并两个有序链表
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

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

        current.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

}