package com.hm.leetcode.code21;

import com.hm.algorithm.ListNode;

import java.util.Arrays;

/**
 * Create by dumingwei on 2026-06-07
 * Desc: LeetCode 21. 合并两个有序链表
 * <p>
 * 将两个升序链表合并为一个新的升序链表并返回。新链表是通过拼接给定的两个链表的所有结点组成的。
 * <p>
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * <p>
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 提示：
 * 两个链表的结点数目范围是 [0, 50]；-100 <= Node.val <= 100；l1 和 l2 均按非递减顺序排列。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-two-sorted-lists/
 */
public class MergeTwoListsTest {

    public static void main(String[] args) {
        test(new int[]{1, 2, 4}, new int[]{1, 3, 4}, "[1, 1, 2, 3, 4, 4]");
        test(new int[]{}, new int[]{}, "[]");
        test(new int[]{}, new int[]{0}, "[0]");
        test(new int[]{5}, new int[]{1, 2, 3}, "[1, 2, 3, 5]");
        test(new int[]{1, 3, 5, 7}, new int[]{2, 4, 6}, "[1, 2, 3, 4, 5, 6, 7]");
        test(new int[]{2, 2}, new int[]{2, 2}, "[2, 2, 2, 2]");
    }

    /**
     * 跑「迭代解法（复用 LeetCode21）」与「递归解法」两种实现并交叉验证。
     * 两种解法各自独立建链，避免互相修改结点指针。
     */
    private static void test(int[] a, int[] b, String expected) {
        String r1 = toString(new LeetCode21().mergeTwoLists(build(a), build(b)));
        String r2 = toString(mergeRecursive(build(a), build(b)));
        boolean ok = r1.equals(expected) && r2.equals(expected);
        System.out.printf("l1=%-14s l2=%-12s => 迭代:%-20s 递归:%-20s 期望:%-20s %s%n",
                Arrays.toString(a), Arrays.toString(b), r1, r2, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法二：递归
     * 谁的头小，就把它作为结果头，其 next 递归合并剩余部分。
     * 时间 O(m+n)，空间 O(m+n)（递归栈）。
     */
    public static ListNode mergeRecursive(ListNode l1, ListNode l2) {
        // 任一为空，直接接上另一条
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val <= l2.val) {
            l1.next = mergeRecursive(l1.next, l2);
            return l1;
        } else {
            l2.next = mergeRecursive(l1, l2.next);
            return l2;
        }
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

    /** 链表转 [a, b, c] 字符串 */
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
