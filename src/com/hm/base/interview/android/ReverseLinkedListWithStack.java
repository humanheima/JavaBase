package com.hm.base.interview.android;

import com.hm.algorithm.ListNode;

import java.util.Stack;

/**
 * 如何使用栈来实现翻转单链表，比较好理解，就用这种方式来实现了
 */
public class ReverseLinkedListWithStack {

    public ListNode reverseList(ListNode head) {
        // 如果链表为空或只有一个节点，直接返回
        if (head == null || head.next == null) {
            return head;
        }

        // 初始化栈
        Stack<ListNode> stack = new Stack<>();

        // 第一步：将所有节点压入栈
        ListNode current = head;
        while (current != null) {
            stack.push(current);
            current = current.next;
        }

        // 第二步：弹出节点并重建链表
        ListNode newHead = stack.pop();  // 新头节点
        current = newHead;               // 用于连接后续节点

        while (!stack.isEmpty()) {
            ListNode node = stack.pop();
            current.next = node;         // 连接到下一个节点
            current = node;              // 移动到当前节点
        }

        // 注意：确保最后一个节点的next为null
        current.next = null;

        return newHead;
    }

    // 测试代码
    public static void main(String[] args) {
        // 创建链表: 1 -> 2 -> 3 -> 4 -> 5
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        // 打印原始链表
        System.out.print("Original List: ");
        printList(head);

        // 反转链表
        ReverseLinkedListWithStack solution = new ReverseLinkedListWithStack();
        ListNode newHead = solution.reverseList(head);

        // 打印反转后的链表
        System.out.print("Reversed List: ");
        printList(newHead);
    }

    // 辅助方法：打印链表
    public static void printList(ListNode head) {
        ListNode curr = head;
        while (curr != null) {
            System.out.print(curr.val);
            if (curr.next != null) {
                System.out.print(" -> ");
            }
            curr = curr.next;
        }
        System.out.println();
    }
}