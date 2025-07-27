package com.hm.handwrite;

import com.hm.algorithm.ListNode;
import com.hm.algorithm.NodeUtil;

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

        NodeUtil.printListNode(head);

        System.out.println("反转后的链表");
        ListNode reverseHead = reverseListUseStack2(head);
        NodeUtil.printListNode(reverseHead);

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

        NodeUtil.printList(head);

        System.out.println("K节点反转后的链表");
        //ListNode reverseHead = reverseKNodes(head, 3);
        //NodeUtil.printList(reverseHead);
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

        //1 -> 2 -> 3  -> null
        while (!stack.isEmpty()) {
            //0->3
            //0->3 -> 2 -> 1
            cur.next = stack.pop();
            cur = cur.next;
        }
        //注释2处，注意，遍历结束的时候，temp就是null了。最后一个节点的next要置为null
        ////0->3 -> 2 -> 1 -> null
        cur.next = null;
        return dummy.next;
    }


}
