package com.hm.base.interview.android;

import com.hm.algorithm.ListNode;

/**
 * 给出两个整数，比如 123， 98 ，把这两个整数分别转化成一个链表，然后将两个链表相加，输出正确的结果为整数
 * <p>
 * 123 + 98 = 221
 * <p>
 * 123 +9 = 132
 * <p>
 * 这道题目和 StringAddition 类似。懂了
 */
public class AddTwoNumbers {

    public static void main(String[] args) {
        // 测试用例：123 + 98
        int num1 = 123;
        int num2 = 98;

        //int num1 = 123;
        //int num2 = 9;

        // 转换为链表
        ListNode l1 = numberToList(num1);
        ListNode l2 = numberToList(num2);

        // 相加
        ListNode result = addTwoNumbers(l1, l2);

        // 将结果链表转换回整数
        int sum = listToNum(result);

        // 输出结果
        System.out.println(num1 + " + " + num2 + " = " + sum);

        // 打印链表形式
        System.out.print("结果链表: ");
        ListNode temp = result;
        while (temp != null) {
            System.out.print(temp.val);
            if (temp.next != null) System.out.print(" -> ");
            temp = temp.next;
        }
    }

    public static ListNode numberToList(int num) {
        if (num == 0) {
            return new ListNode(0);
        }

        ListNode dummy = new ListNode(0);
        ListNode current = dummy;

        while (num > 0) {
            //个位数字
            int digit = num % 10;
            current.next = new ListNode(digit);
            current = current.next;
            num /= 10;
        }

        return dummy.next;
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        //进制，比如 3+9 = 12 ，要进1
        int carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            int x = (l1 != null) ? l1.val : 0;
            int y = (l2 != null) ? l2.val : 0;

            int sum = x + y + carry;
            carry = sum / 10;
            int digit = sum % 10;

            current.next = new ListNode(digit);
            current = current.next;

            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }

        return dummy.next;
    }

    // 将链表转换回整数
    private static int listToNum(ListNode head) {
        // 先将链表转为字符串
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val);
            head = head.next;
        }
        // 反转字符串并转换为整数
        return Integer.parseInt(sb.reverse().toString());
    }
}