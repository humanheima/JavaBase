package com.hm.base.interview.android;

import com.hm.algorithm.ListNode;

/**
 * 删除链表的倒数第 N 个节点
 * <p>
 * 参考删除倒数第N个节点
 * 关联 {@link com.hm.base.interview.sword_to_offer.Test22}
 */
class RemoveNthFromEnd {


    public ListNode removeNthFromEnd(ListNode head, int n) {

        // 创建哑节点，指向头节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // 定义快慢指针
        ListNode fast = dummy;
        ListNode slow = dummy;

        // fast 先走 n 步
        for (int i = 0; i < n; i++) {
            fast = fast.next;
        }

        // fast 和 slow 同时移动，直到 fast 到达末尾
        while (fast.next != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除目标节点
        slow.next = slow.next.next;

        // 返回新头节点
        return dummy.next;
    }
}