package com.hm.handwrite;

import com.hm.algorithm.ListNode;
import com.hm.algorithm.PrintNodeUtil;

import java.util.Stack;

/**
 * Created by dumingwei on 2024/4/4
 * 选择用栈来实现，反转单链表
 */
public class ReverseLinkedListUseStack {

    public static void main(String[] args) {
        //test0();
        test1();
    }

    private static void test0() {
        ListNode head = new ListNode();
        head.val = 1;

        head.next = new ListNode();
        head.next.val = 2;

        head.next.next = new ListNode();
        head.next.next.val = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.val = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.val = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.val = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.val = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.val = 8;

        head.next.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.next.val = 9;

        PrintNodeUtil.printListNode(head);

        System.out.println("反转后的链表");
        //ListNode reverseHead = reverseListUseStack(head);
        ListNode reverseHead = reverseListUseStack2(head);
        PrintNodeUtil.printListNode(reverseHead);

    }

    private static void test1() {
        ListNode head = new ListNode();
        head.val = 1;

        head.next = new ListNode();
        head.next.val = 2;

        head.next.next = new ListNode();
        head.next.next.val = 3;

        head.next.next.next = new ListNode();
        head.next.next.next.val = 4;

        head.next.next.next.next = new ListNode();
        head.next.next.next.next.val = 5;

        head.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.val = 6;

        head.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.val = 7;

        head.next.next.next.next.next.next.next = new ListNode();
        head.next.next.next.next.next.next.next.val = 8;

//        head.next.next.next.next.next.next.next.next = new ListNode();
//        head.next.next.next.next.next.next.next.next.val = 9;

        PrintNodeUtil.printListNode(head);

        System.out.println("K节点反转后的链表");
        ListNode reverseHead = reverseKNodes(head, 3);
        PrintNodeUtil.printListNode(reverseHead);
    }

    /**
     * 用栈来实现反转单链表
     * <p>
     * 开始 1,2,3
     * <p>
     * 1.遍历链表，将链表的节点放入栈中
     * <p>
     * 3,2,1
     * <p>
     * 2.从栈中取出节点，重新连接节点
     *
     * @param head
     */
    private static ListNode reverseListUseStack(ListNode head) {
        if (head == null) {
            return null;
        }

        Stack<ListNode> stack = new Stack<>();
        ListNode cur = head;
        //记录翻转后的头节点
        ListNode reverseHead = null;
        //用来从反转后的头节点向前遍历
        ListNode reverseHeadTraverse = null;

        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 3 2 1
        //取出一个元素，要把它的next置为null
        while (!stack.isEmpty()) {
            if (reverseHead == null) {
                //最后一个元素next本来就是null，这里不需要再置为null
                reverseHead = stack.pop();
                reverseHeadTraverse = reverseHead;
            } else {
                ListNode temp = stack.pop();
                temp.next = null;
                reverseHeadTraverse.next = temp;
                reverseHeadTraverse = temp;
            }
        }

        return reverseHead;
    }


    /**
     * 这种方法比较好
     *
     * @param head
     * @return
     */
    private static ListNode reverseListUseStack2(ListNode head) {
        if (head == null) {
            return null;
        }

        //注释1处，创建一个虚拟节点，感觉挺方便的。
        //从栈里取出节点，直接连接到这个虚拟节点上。而不需要修改节点的next。
        // 不然取出一个节点，可能需要修改这个节点的next为null
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        Stack<ListNode> stack = new Stack<>();
        ListNode temp = head;
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }

        while (!stack.isEmpty()) {
            cur.next = stack.pop();
            cur = cur.next;
        }
        //注释2处，注意，遍历结束的时候，temp就是null了。最后一个节点的next要置为null
        cur.next = temp;
        return dummy.next;
    }

    /**
     * 每K个节点反转链表
     *
     * @param head
     * @param k
     * @return 返回反转后的头节点
     */
    private static ListNode reverseKNodes(ListNode head, int k) {
        if (head == null) {
            return head;
        }
        if (k <= 1) {
            return head;
        }

        Stack<ListNode> stack = new Stack<>();
        //创建一个虚拟节点
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;

        while (head != null) {
            System.out.println("head:" + head);
            int count = 0;
            ListNode temp = head;
            while (temp != null && count < k) {
                stack.push(temp);
                //注释1处，1轮结束后，temp指向的是第k+1个节点
                temp = temp.next;
                count++;
            }

            //注释2处，如果栈中的元素个数不等于k，说明剩下的节点不够k个，不需要反转。
            if (count < k) {
                cur.next = head;
                break;
            }
            while (!stack.isEmpty()) {
                //注释3处，这里是取出栈中的元素，连接到虚拟节点上。
                cur.next = stack.pop();
                cur = cur.next;
            }

            //注释4处，如果遍历到了最后，将最后一个节点的next置为null，temp是null
            cur.next = temp;
            head = temp;

        }
        return dummy.next;

    }

}
