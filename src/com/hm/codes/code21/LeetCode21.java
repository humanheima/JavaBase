package com.hm.codes.code21;

import com.hm.algorithm.ListNode;

/**
 * Created by dumingwei on 2022/4/4.
 * <p>
 * Desc:21. 合并两个有序链表
 * <p>
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 * <p>
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 * 示例 2：
 * <p>
 * 输入：l1 = [], l2 = []
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/merge-two-sorted-lists
 */
public class LeetCode21 {

    public static void main(String[] args) {
        LeetCode21 leetCode21 = new LeetCode21();
        leetCode21.test1();
        leetCode21.test2();
        leetCode21.test3();
    }


    private void test1() {
        ListNode list1 = new ListNode(1);
        ListNode list1_2 = new ListNode(2);
        ListNode list1_3 = new ListNode(4);
        list1.next = list1_2;
        list1_2.next = list1_3;

//        while (list1 != null) {
//            System.out.println(list1.value);
//            list1 = list1.next;
//        }

        ListNode list2 = new ListNode(1);
        ListNode list2_2 = new ListNode(3);
        ListNode list2_3 = new ListNode(4);
        list2.next = list2_2;
        list2_2.next = list2_3;

//        System.out.println("-----------");
//        while (list2 != null) {
//            System.out.println(list2.value);
//            list2 = list2.next;
//        }

        ListNode result = mergeTwoLists(list1, list2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }

        System.out.println("\n-------------------");
    }

    private void test2() {
        ListNode list1 = null;
        ListNode list2 = null;
        ListNode result = mergeTwoLists(list1, list2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println("\n-------------------");
    }

    private void test3() {
        ListNode list1 = null;
        ListNode list2 = new ListNode(0);
        ListNode result = mergeTwoLists(list1, list2);
        while (result != null) {
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println("\n-------------------");
    }

    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        if (list1 == null && list2 == null) {
            return null;
        }

        ListNode dummy = new ListNode(0);
        ListNode currentNode = dummy;

        if (list1 == null) {
            currentNode = list2;
            return currentNode;
        }

        if (list2 == null) {
            currentNode = list1;
            return currentNode;
        }

        while (list1 != null && list2 != null) {
            if (list1.val < list2.val) {
                currentNode.next = list1;
                list1 = list1.next;
            } else {
                currentNode.next = list2;
                list2 = list2.next;
            }
            currentNode = currentNode.next;
        }

        if (list1 != null) {
            currentNode.next = list1;
        }
        if (list2 != null) {
            currentNode.next = list2;
        }
        return dummy.next;
    }


}
