package com.hm.base.interview.android;

import java.util.HashSet;

/**
 * 找到环的入口
 * 使用HashSet 的方式挺好的。重复的点，就是环的入口。
 */
public class LinkedListCycleII {
    public static void main(String[] args) {
        // 创建链表: 1 -> 2 -> 3 -> 4
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);

        // 测试无环情况
        LinkedListCycleII solution = new LinkedListCycleII();
        ListNode result = solution.detectCycle(head);
        System.out.println("No cycle: " + (result == null ? "null" : result.val));

        // 创建环: 4 -> 2
        head.next.next.next.next = head.next;
        result = solution.detectCycle(head);
        System.out.println("Cycle entry: " + (result == null ? "null" : result.val));
    }

    public ListNode detectCycle(ListNode head) {
        // 空链表或单节点无环
        if (head == null || head.next == null) {
            return null;
        }

        // 第一阶段：检测是否有环
        ListNode slow = head;
        ListNode fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;          // 慢指针走一步
            fast = fast.next.next;     // 快指针走两步

            // 相遇，进入第二阶段
            if (slow == fast) {
                // 第二阶段：找到环入口
                slow = head;           // 慢指针回到起点
                while (slow != fast) { // 两者以相同速度移动
                    slow = slow.next;
                    fast = fast.next;
                }
                return slow;           // 返回环入口
            }
        }

        // 无环
        return null;
    }

    public ListNode detectCycleHash(ListNode head) {
        HashSet<ListNode> visited = new HashSet<>();
        ListNode current = head;

        while (current != null) {
            if (visited.contains(current)) {
                return current;  // 环入口
            }
            visited.add(current);
            current = current.next;
        }

        return null;  // 无环
    }
}