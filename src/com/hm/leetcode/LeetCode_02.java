package com.hm.leetcode;

/**
 * Crete by dumingwei on 2020-03-04
 * Desc:
 * <p>
 * 问题描述
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * <p>
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。
 * <p>
 * 您可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 * <p>
 * 示例：
 * <p>
 * 输入：(2 -> 4 -> 3) + (5 -> 6 -> 1)
 * 输出：5 -> 0 -> 8
 * 原因：342 + 165 = 508
 * <p>
 * 解题思路：
 * 1. 两个链表可能长度不一样，累加完对应的位数以后，长的链表剩余节点直接加入到返回的链表中即可。
 * 要有一个临时变量保存进位
 * 3.
 *
 *
 *
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-two-numbers
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode_02 {

    public static void main(String[] args) {

        ListNode l1 = new ListNode(2);
        l1.next = new ListNode(4);
        l1.next.next = new ListNode(3);

        ListNode l2 = new ListNode(5);
        l2.next = new ListNode(6);
        l2.next.next = new ListNode(4);

        /*ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(5);*/

       /* ListNode l1 = new ListNode(5);
        ListNode l2 = new ListNode(6);*/


        /*ListNode l1 = new ListNode(1);
        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);*/

        //ListNode result = addTwoNumbers(l1, l2);
        ListNode result = addTwoNumbers1(l1, l2);

        while (result != null) {
            System.out.println(result.val + " ,");
            result = result.next;
        }

    }

    private static class ListNode {

        int val;

        ListNode next;

        public ListNode(int val) {
            this.val = val;
        }
    }

    /**
     * 第一次提交的正确答案
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode head = null;
        ListNode cursor = null;

        //tmp用来表示进位
        int high = 0;
        while (l1 != null && l2 != null) {
            //累加结果
            int result = l1.val + l2.val + high;
            //累加结果的个位数
            int low = result % 10;

            //System.out.println("low = " + low);

            if (head == null) {
                head = new ListNode(low);
                cursor = head;
            } else {
                cursor.next = new ListNode(low);
                cursor = cursor.next;
            }
            //累加结果的十位数，可能为10
            high = (result - low) / 10;
            //System.out.println("high = " + high);
            l1 = l1.next;
            l2 = l2.next;
        }

        while (l1 != null) {
            int result = l1.val + high;
            //累加结果的个位数
            int low = result % 10;

            if (head == null) {
                head = new ListNode(low);
                cursor = head;
            } else {
                cursor.next = new ListNode(low);
                cursor = cursor.next;
                l1 = l1.next;
            }
            //累加结果的十位数，可能为10
            high = (result - low) / 10;

        }
        while (l2 != null) {
            int result = l2.val + high;
            //累加结果的个位数
            int low = result % 10;

            if (head == null) {
                head = new ListNode(low);
                cursor = head;
            } else {
                cursor.next = new ListNode(low);
                cursor = cursor.next;
                l2 = l2.next;
            }
            //累加结果的十位数，可能为10
            high = (result - low) / 10;
        }

        if (high > 0) {
            if (head == null) {
                head = new ListNode(high);
                cursor = head;
            } else {
                cursor.next = new ListNode(high);
                cursor = cursor.next;
            }
        }

        return head;
    }

    /**
     * 参考力扣官方答案
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        //先初始化头节点，避免额外的判断，最后返回head.next就行了
        ListNode head = new ListNode(0);
        ListNode curr = head;

        //carry用来表示进位
        int carry = 0;
        while (l1 != null || l2 != null) {
            //累加结果
            int x = l1 != null ? l1.val : 0;
            int y = l2 != null ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10;

            curr.next = new ListNode(sum % 10);
            curr = curr.next;

            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }

        if (carry > 0) {
            curr.next = new ListNode(carry);
        }

        return head.next;
    }


}
