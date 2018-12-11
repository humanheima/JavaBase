package com.hm.base.interview.sword_to_offer;

import com.sun.glass.events.mac.NpapiEvent;

/**
 * Created by dumingwei on 2018/12/10
 * <p>
 * Desc:如果一个链表中包含环，如何找出环的入口节点？
 * <p>
 * 解题思路：
 * 1. 首先确定链表中是否包含环。我们可以用两个指针来解决这个问题。定义两个指针，同时从链表的头节点出发，一个指针一次走一步，
 * 一个指针一次走两步，如果走的快的指针追上了走得慢的指针，那么链表就包含环。入如果走得快的指针走到了链表的末尾都没有追上走的慢的指针，那么
 * 链表就不包含环。
 * <p>
 * 2. 找到环的入口。我们还是用两个指针来解决这个问题。先定义两个指针P1和P2指向链表的头结点。如果链表中环有n个结点，
 * 指针P1在链表上向前移动n步，然后两个指针以相同的速度向前移动。当第二个指针指向环的入口结点时，
 * 第一个指针已经围绕着环走了一圈又回到了入口结点。
 * <p>
 * 3. 剩下的问题就是如何得到环中结点的数目。我们在前面判断一个链表里是否有环的时候用到了一快一慢的两个指针。如果两个指针相遇，
 * 表明链表中存在环。两个指针相遇的结点一定是在环中。可以从这个结点出发，一边继续向前移动一边计数，
 * 当再次回到这个结点时就可以得到环中结点数了。
 * <p>
 * <p>
 * 关于快指针一次走两步，慢指针一次走一步一定相遇的问题：
 * 这个问题你可以用数学归纳法来思考。
 * 首先，由于链表是个环，所以相遇的过程可以看作是快指针从后边追赶慢指针的过程。那么做如下思考：
 * 1：快指针与慢指针之间差一步。此时继续往后走，慢指针前进一步，快指针前进两步，两者相遇。
 * 2：快指针与慢指针之间差两步。此时继续往后走，慢指针前进一步，快指针前进两步，两者之间相差一步，转化为第一种情况。
 * 3：快指针与慢指针之间差N步。此时继续往后走，慢指针前进一步，快指针前进两步，两者之间相差(N+1-2)-> N-1步。
 * 因此，此题得证。所以快指针必然与慢指针相遇。又因为快指针速度是慢指针的两倍，所以相遇时必然只绕了一圈。
 * <p>
 * 作者：冯昱尧
 * 链接：https://www.zhihu.com/question/23208893/answer/117115415
 * 来源：知乎
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46838835
 */
public class Test23 {

    private static class ListNode {
        private int val;
        private ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val + "";
        }
    }

    public static void main(String[] args) {
        test01();
        test02();
        test03();
    }

    public static ListNode meetingNode(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        //链表中没有环
        if (fast == null || fast.next == null) {
            return null;
        }
        //fast重新指向第一个节点
        fast = head;
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    /**
     * 存在环，返回相遇的节点，不存在，返回null
     *
     * @param head
     * @return
     */
    public static ListNode meetingNode1(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode slow = head.next;
        if (slow == null) {
            return null;
        }

        ListNode fast = slow.next;

        while (fast != null && slow != null) {
            if (fast == slow) {
                return fast;
            }
            slow = slow.next;
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        //链表中没有环
        return fast;
    }

    public static ListNode loopNode(ListNode head) {
        ListNode meetingNode = meetingNode1(head);
        if (meetingNode == null) {
            return null;
        }
        int nodesInLoop = 1;
        ListNode node1 = meetingNode;
        while (node1.next != meetingNode) {
            node1 = node1.next;
            nodesInLoop++;
        }
        node1 = head;
        //先移动node1，次数为环中节点的数目nodesInLoop
        for (int i = 0; i < nodesInLoop; i++) {
            node1 = node1.next;
        }
        ListNode node2 = head;
        while (node1 != node2) {
            node1 = node1.next;
            node2 = node2.next;
        }
        return node1;
    }


    // 1->2->3->4->5->6
    private static void test01() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;

        //System.out.println(meetingNode(n1));
        System.out.println(loopNode(n1));
    }

    // 1->2->3->4->5->6
    //       ^        |
    //       |        |
    //       +--------+
    private static void test02() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n3;

        //System.out.println(meetingNode(n1));
        System.out.println(loopNode(n1));
    }

    // 1->2->3->4->5->6 <-+
    //                |   |
    //                +---+
    private static void test03() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n6;

        //System.out.println(meetingNode(n1));
        System.out.println(loopNode(n1));
    }

}
