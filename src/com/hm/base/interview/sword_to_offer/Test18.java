package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/2
 * <p>
 * Desc:给定单向链表的头指针和一个结点指针，定义一个函数在0(1)时间删除该结点
 * <p>
 * 解题思路：我们要删除节点i，先把节点i的下一个节点j的内容复制到i，然后把i的指针指向节点j的下一个节点，然后删除j。
 * 上述思路还有一个问题：如果要删除的节点位于链表的尾部，那么他就没有下一个节点，怎么办？我们仍然从链表的头节点开始遍历，
 * 得到该节点的前一个节点，并完成删除操作。
 * <p>
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/45529213
 */
public class Test18 {

    public static void main(String[] args) {
        ListNode head = new ListNode();
        head.value = 1;

        ListNode second = new ListNode();
        head.next = second;
        second.value = 2;

        ListNode third = new ListNode();
        second.next = third;
        third.value = 3;

        ListNode forth = new ListNode();
        third.next = forth;
        forth.value = 4;

        ListNode last = new ListNode();
        forth.next = last;
        last.value = 5;


        //删除的节点为空
        head = deleteNode(head, null);
        printList(head);

        //删除头节点
        /*head=deleteNode(head,head);
        printList(head);*/

        //删除尾部节点
        head = deleteNode(head, last);
        printList(head);

        //删除一个正常的节点
        /*head=deleteNode(head,third);
        printList(head);*/

        //删除一个不存在的节点
        /*head = deleteNode(head, new ListNode());
        printList(head);*/

    }

    public static class ListNode {

        int value;
        ListNode next;
    }

    /**
     * 删除节点，前提是节点一定在链表中,如果节点不存在那么会抛出异常
     *
     * @param head
     * @param toBeDeleteNode
     * @return 返回被删除节点后的链表头节点
     */
    public static ListNode deleteNode(ListNode head, ListNode toBeDeleteNode) {
        if (head == null || toBeDeleteNode == null) {
            return head;
        }
        // 如果删除的是头结点，直接返回头结点的下一个结点
        if (head == toBeDeleteNode) {
            return head.next;
        }
        //在多个节点的情况下删除的是尾部节点
        if (toBeDeleteNode.next == null) {
            /**
             * 在这种情况下，为什么不直接把toBeDeleteNode置为null？
             * 因为把toBeDeleteNode置为null，只是toBeDeleteNode这个引用为null了，而链表中尾部节点的前一个节点的next仍然指向
             * 尾部节点所在的内存。
             *
             */
            //
            ListNode temp = head;
            while (temp.next != toBeDeleteNode) {
                temp = temp.next;
            }
            temp.next = null;
            //toBeDeleteNode = null;
        } else {
            toBeDeleteNode.value = toBeDeleteNode.next.value;
            toBeDeleteNode.next = toBeDeleteNode.next.next;
        }
        return head;

    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.value + "->");
            head = head.next;
        }
        System.out.println("null");
    }

}
