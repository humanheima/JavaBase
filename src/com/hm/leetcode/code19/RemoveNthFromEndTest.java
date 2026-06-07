package com.hm.leetcode.code19;

import com.hm.algorithm.ListNode;

import java.util.Arrays;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 19. 删除链表的倒数第 N 个结点
 * <p>
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 * <p>
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 * <p>
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 * <p>
 * 提示：
 * 链表中结点的数目为 sz；1 <= sz <= 30；0 <= Node.val <= 100；1 <= n <= sz
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/
 */
public class RemoveNthFromEndTest {

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4, 5}, 2, "[1, 2, 3, 5]");
        test(new int[]{1}, 1, "[]");
        test(new int[]{1, 2}, 1, "[1]");
        test(new int[]{1, 2}, 2, "[2]");           // 删头结点
        test(new int[]{1, 2, 3, 4, 5}, 5, "[2, 3, 4, 5]"); // 删头结点
        test(new int[]{7, 8, 9}, 3, "[8, 9]");
    }

    /**
     * 两种解法各跑一次（各自独立建链，避免互相破坏），与期望比较。
     */
    private static void test(int[] vals, int n, String expected) {
        RemoveNthFromEnd solution = new RemoveNthFromEnd();
        String r1 = toString(solution.removeNthFromEnd(build(vals), n));
        String r2 = toString(solution.removeNthFromEndTwoPass(build(vals), n));
        boolean ok = r1.equals(expected) && r2.equals(expected);
        System.out.printf("%-18s n=%d => 一趟:%-14s 两趟:%-14s 期望:%-14s %s%n",
                Arrays.toString(vals), n, r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /** 由数组构建链表 */
    private static ListNode build(int[] vals) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        for (int v : vals) {
            tail.next = new ListNode(v);
            tail = tail.next;
        }
        return dummy.next;
    }

    /** 链表转 [a, b, c] 形式字符串 */
    private static String toString(ListNode head) {
        StringBuilder sb = new StringBuilder("[");
        for (ListNode p = head; p != null; p = p.next) {
            if (sb.length() > 1) {
                sb.append(", ");
            }
            sb.append(p.val);
        }
        return sb.append("]").toString();
    }
}
