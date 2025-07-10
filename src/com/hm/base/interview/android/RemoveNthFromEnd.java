package com.hm.base.interview.android;

import com.hm.algorithm.ListNode;

/**
 * 删除链表的倒数第 N 个节点
 * <p>
 * 参考删除倒数第N个节点
 * 关联 {@link com.hm.base.interview.sword_to_offer.Test22}
 */
class RemoveNthFromEnd {


    // 测试代码
    public static void main(String[] args) {
        RemoveNthFromEnd solution = new RemoveNthFromEnd();

//        test1(solution);
//
//        test2(solution);
//
//        test3(solution);

        test4(solution);
    }

    private static void test4(RemoveNthFromEnd solution) {
        ListNode head4 = createList(new int[]{2});
        ListNode result4 = solution.removeNthFromEnd(head4, 1);
        printList(result4); //
    }

    private static void test3(RemoveNthFromEnd solution) {
        // 测试用例3: 删除倒数第1个节点 [1,2], n=1 -> [1]
        ListNode head3 = createList(new int[]{1,2});
        ListNode result3 = solution.removeNthFromEnd(head3, 1);
        printList(result3); // 预期输出: 1
    }

    private static void test2(RemoveNthFromEnd solution) {
        // 测试用例2: 删除头节点 [1], n=1 -> []
        ListNode head2 = createList(new int[]{1});
        ListNode result2 = solution.removeNthFromEnd(head2, 1);
        printList(result2); // 预期输出: (空)
    }

    private static void test1(RemoveNthFromEnd solution) {
        // 测试用例1: 删除倒数第2个节点 [1,2,3,4,5], n=2 -> [1,2,3,5]
        ListNode head1 = createList(new int[]{1,2,3,4,5});
        ListNode result1 = solution.removeNthFromEnd(head1, 2);
        printList(result1); // 预期输出: 1->2->3->5
    }


    /**
     *
     * @param head
     * @param k
     * @return
     */
    public ListNode removeNthFromEnd(ListNode head, int k) {
        // 创建哑节点，处理删除头节点的情况
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode fast = dummy;
        ListNode slow = dummy;

        // 快指针先走 n+1 步
        for (int i = 0; i <= k; i++) {
            if (fast == null) return head; // n 超过链表长度
            fast = fast.next;
        }

        // 快慢指针一起移动，直到快指针到末尾
        while (fast != null) {
            fast = fast.next;
            slow = slow.next;
        }

        // 删除目标节点
        slow.next = slow.next.next;

        return dummy.next;
    }



    // 辅助方法：创建链表
    private static ListNode createList(int[] arr) {
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        for (int val : arr) {
            current.next = new ListNode(val);
            current = current.next;
        }
        return dummy.next;
    }

    // 辅助方法：打印链表
    private static void printList(ListNode head) {
        if (head == null) {
            System.out.println("(空)");
            return;
        }
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) {
                System.out.print("->");
            }
            head = head.next;
        }
        System.out.println();
    }
}