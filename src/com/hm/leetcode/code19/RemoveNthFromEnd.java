package com.hm.leetcode.code19;

import com.hm.algorithm.ListNode;

/**
 * Created by dumingwei on 2026-06-07
 * Desc: LeetCode 19. 删除链表的倒数第 N 个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * 进阶：你能尝试使用一趟扫描实现吗？
 * <p>
 * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEnd {

    /**
     * 解法一：快慢指针，一趟扫描（推荐）
     * <p>
     * 关键：让 fast 比 slow 先走 n 步，二者再同步前进；当 fast 到达末尾时，
     * slow 正好停在「待删结点的前一个」。配合 dummy 哑结点统一处理「删头结点」的边界。
     * 时间 O(L)，空间 O(1)，L 为链表长度。
     */
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 哑结点指向头，删头结点时也能用「前驱.next = 待删.next」统一处理
        ListNode dummy = new ListNode(0, head);
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n 步，拉开 n 的间距
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // 二者同步前进，直到 fast 走到最后一个结点
        // 此时 slow 停在「倒数第 n 个」的前驱
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 跳过待删结点
        slow.next = slow.next.next;

        return dummy.next;
    }

    /**
     * 解法二：两趟扫描（先求长度再删除）
     * <p>
     * 第一趟数出链表长度 L，倒数第 n 个就是正数第 (L-n+1) 个，
     * 它的前驱是正数第 (L-n) 个；第二趟走到前驱删除即可。
     * 时间 O(L)，空间 O(1)。思路最直白，便于理解。
     */
    public ListNode removeNthFromEndTwoPass(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head);

        // 第一趟：求长度
        int length = 0;
        for (ListNode p = head; p != null; p = p.next) {
            length++;
        }

        // 第二趟：从 dummy 走 (length - n) 步到达待删结点的前驱
        ListNode prev = dummy;
        for (int i = 0; i < length - n; i++) {
            prev = prev.next;
        }
        prev.next = prev.next.next;

        return dummy.next;
    }
}
