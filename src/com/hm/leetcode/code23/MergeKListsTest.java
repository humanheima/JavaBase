package com.hm.leetcode.code23;

import com.hm.algorithm.ListNode;

import java.util.PriorityQueue;

/**
 * Create by dumingwei on 2026-06-08
 * Desc: LeetCode 23. 合并 K 个升序链表
 * <p>
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 * <p>
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：把它们合并到一个有序链表中得到 1->1->2->3->4->4->5->6。
 * <p>
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 * <p>
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 * <p>
 * 提示：
 * k == lists.length，0 <= k <= 10^4；
 * 0 <= lists[i].length <= 500；-10^4 <= lists[i][j] <= 10^4；
 * lists[i] 按升序排列；lists[i].length 之和不超过 10^4。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/merge-k-sorted-lists/
 */
public class MergeKListsTest {

    public static void main(String[] args) {
        test(new int[][]{{1, 4, 5}, {1, 3, 4}, {2, 6}}, "[1, 1, 2, 3, 4, 4, 5, 6]");
        test(new int[][]{}, "[]");
        test(new int[][]{{}}, "[]");
        test(new int[][]{{}, {1}, {}}, "[1]");
        test(new int[][]{{-2, -1, 0}, {-3, 2}, {1, 1}}, "[-3, -2, -1, 0, 1, 1, 2]");
        test(new int[][]{{1, 2, 3, 4, 5}}, "[1, 2, 3, 4, 5]");
    }

    /**
     * 跑三种解法并交叉验证。每种解法各自独立建链，避免互相修改结点指针。
     */
    private static void test(int[][] arrays, String expected) {
        String heap = toString(new MergeKListsTest().mergeKListsByHeap(build(arrays)));
        String divide = toString(new MergeKListsTest().mergeKListsByDivide(build(arrays)));
        String seq = toString(new MergeKListsTest().mergeKListsBySequential(build(arrays)));
        boolean ok = heap.equals(expected) && divide.equals(expected) && seq.equals(expected);
        System.out.printf("最小堆:%-26s 分治:%-26s 逐一:%-26s 期望:%-26s %s%n",
                heap, divide, seq, expected, ok ? "✓" : "✗ FAIL");
    }

    /**
     * 解法一：最小堆（优先队列）
     * 把每条链表的头结点放入小顶堆，每次弹出最小者接到结果末尾，
     * 再把它的 next 入堆。堆中始终最多 k 个结点。
     * 时间 O(N log k)，空间 O(k)。N 为所有结点总数，k 为链表条数。
     */
    public ListNode mergeKListsByHeap(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>((a, b) -> a.val - b.val);
        for (ListNode node : lists) {
            if (node != null) {
                heap.offer(node);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!heap.isEmpty()) {
            ListNode min = heap.poll();
            tail.next = min;
            tail = tail.next;
            if (min.next != null) {
                heap.offer(min.next);
            }
        }
        tail.next = null; // 断开尾部，避免悬挂指针
        return dummy.next;
    }

    /**
     * 解法二：分治（两两归并）
     * 把 k 条链表两两配对合并，每轮链表数减半，logk 轮归并完成。
     * 时间 O(N log k)，空间 O(log k)（递归栈）。
     */
    public ListNode mergeKListsByDivide(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private ListNode merge(ListNode[] lists, int lo, int hi) {
        if (lo == hi) {
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2;
        ListNode left = merge(lists, lo, mid);
        ListNode right = merge(lists, mid + 1, hi);
        return mergeTwoLists(left, right);
    }

    /**
     * 解法三：逐一顺序合并（基准做法）
     * 用一个累积结果，依次与第 i 条链表合并。
     * 时间 O(N k)，空间 O(1)。直观但效率最差。
     */
    public ListNode mergeKListsBySequential(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        ListNode result = null;
        for (ListNode node : lists) {
            result = mergeTwoLists(result, node);
        }
        return result;
    }

    /**
     * 合并两个升序链表（复用 LeetCode 21 的迭代解法）。
     */
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }
        tail.next = (l1 != null) ? l1 : l2;
        return dummy.next;
    }

    /** 由二维数组构建链表数组 */
    private static ListNode[] build(int[][] arrays) {
        ListNode[] lists = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            ListNode dummy = new ListNode(0);
            ListNode tail = dummy;
            for (int v : arrays[i]) {
                tail.next = new ListNode(v);
                tail = tail.next;
            }
            lists[i] = dummy.next;
        }
        return lists;
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
