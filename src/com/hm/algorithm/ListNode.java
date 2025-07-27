package com.hm.algorithm;

/**
 * Created by dumingwei on 2022/4/17.
 * <p>
 * Desc:LeetCode 上的算法题经常会用到这个数据结构
 */
public class ListNode {
    public int val;
    public ListNode next;

    public ListNode() {
    }

    public ListNode(int x) {
        val = x;
    }

    public ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    @Override
    public String toString() {
        return hashCode() + " : ListNode{" +
                "val=" + val + "}";
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
