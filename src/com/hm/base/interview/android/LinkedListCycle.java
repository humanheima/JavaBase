package com.hm.base.interview.android;

import java.util.HashSet;

/**
 * 链表中环的检测
 * 方法1：快慢指针法实现
 * 方法2：哈希表法，这种方法挺好的。而且，重复的点就是环的入口。
 */
public class LinkedListCycle {

    public static void main(String[] args) {
        // 创建链表: 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // 测试无环情况
        LinkedListCycle solution = new LinkedListCycle();
        System.out.println("No cycle: " + solution.hasCycle2(head));

        // 创建环: 4 -> 2
        head.next.next.next.next = head.next;
        System.out.println("Has cycle: " + solution.hasCycle2(head));
    }


    public boolean hasCycle1(ListNode head) {
        // 空链表或单节点无环
        if (head == null || head.next == null) {
            return false;
        }

        ListNode slow = head;  // 慢指针
        ListNode fast = head;  // 快指针

        // 遍历链表
        while (fast != null && fast.next != null) {
            slow = slow.next;          // 慢指针走一步
            fast = fast.next.next;     // 快指针走两步

            // 如果相遇，说明有环
            if (slow == fast) {
                return true;
            }
        }

        // 快指针到达末尾，无环
        return false;
    }


    /**
     * 哈希表法
     *
     * @param head
     * @return
     */
    public boolean hasCycle2(ListNode head) {
        // 初始化哈希集合
        HashSet<ListNode> visited = new HashSet<>();

        // 遍历链表
        ListNode current = head;
        while (current != null) {
            // 如果节点已访问过，说明有环
            if (visited.contains(current)) {
                return true;
            }
            // 否则将节点加入集合
            visited.add(current);
            current = current.next;
        }

        // 遍历到末尾，无环
        return false;
    }


}