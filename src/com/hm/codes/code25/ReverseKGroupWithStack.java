
package com.hm.codes.code25;

import com.hm.algorithm.ListNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Created by p_dmweidu on 2025/7/27
 * Desc: K个一组翻转链表
 * https://grok.com/chat/907915c1-c53e-49fe-96f5-3904084635f3
 */
public class ReverseKGroupWithStack {
    public static void main(String[] args) {
        ReverseKGroupWithStack solution = new ReverseKGroupWithStack();

        // 测试用例1：[1,2,3,4,5], k = 2
        ListNode l1 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
        System.out.println("Test Case 1: [1,2,3,4,5], k = 2");
        printResult(solution.reverseKGroup(l1, 2));

        // 测试用例2：[1,2,3,4,5], k = 3
//        ListNode l2 = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5)))));
//        System.out.println("Test Case 2: [1,2,3,4,5], k = 3");
//        printResult(solution.reverseKGroup(l2, 3));
//
//        // 测试用例3：[], k = 1
//        ListNode l3 = null;
//        System.out.println("Test Case 3: [], k = 1");
//        printResult(solution.reverseKGroup(l3, 1));
//
//        // 测试用例4：[1], k = 1
//        ListNode l4 = new ListNode(1);
//        System.out.println("Test Case 4: [1], k = 1");
//        printResult(solution.reverseKGroup(l4, 1));
//
//        // 测试用例5：[1,2,3], k = 4
//        ListNode l5 = new ListNode(1, new ListNode(2, new ListNode(3)));
//        System.out.println("Test Case 5: [1,2,3], k = 4");
//        printResult(solution.reverseKGroup(l5, 4));
    }

    // 打印链表结果
    private static void printResult(ListNode head) {
        List<Integer> result = new ArrayList<>();
        while (head != null) {
            result.add(head.val);
            head = head.next;
        }
        System.out.println("Result: " + result);
        System.out.println();
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 边界：空链表或 k=1
        if (head == null || k == 1) {
            return head;
        }

        // 哑节点简化操作
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode prevGroup = dummy; // 上一组的尾节点

        while (head != null) {
            // 检查是否有 k 个节点
            ListNode groupEnd = head;
            for (int i = 0; i < k - 1 && groupEnd != null; i++) {
                groupEnd = groupEnd.next;
            }
            if (groupEnd == null) {
                break; // 不足 k 个，结束
            }

            // 保存下一组的头
            ListNode nextGroup = groupEnd.next;

            // 使用栈翻转当前组
            Stack<ListNode> stack = new Stack<>();
            ListNode curr = head;
            for (int i = 0; i < k; i++) {
                stack.push(curr);
                curr = curr.next;
            }

            // 出栈并重新连接
            ListNode newHead = stack.pop();
            prevGroup.next = newHead;
            ListNode newTail = newHead;
            while (!stack.isEmpty()) {
                newTail.next = stack.pop();
                newTail = newTail.next;
            }

            // 连接到下一组
            newTail.next = nextGroup;
            // 更新指针
            prevGroup = newTail;
            head = nextGroup;
        }

        return dummy.next;
    }
}