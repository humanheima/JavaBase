package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/10
 * <p>
 * Desc:如果一个链表中包含环，如何找出环的入口节点？
 * <p>
 * 解题思路：
 * 1. 首先确定链表中是否包含环。我们可以用两个指针来解决这个问题。定义两个指针，同时从链表的头节点出发，一个指针一次走一步，
 * 一个指针一次走两步，如果走的快的指针追上了走得慢的指针，那么链表就包含环。如果走得快的指针走到了链表的末尾都没有追上走的慢的指针，那么
 * 链表就不包含环。
 * 1.2 我们确定有环以后，然后绕环走一圈，就可以得到环的长度N。比如说我们链表长度是L，那么环外面的长度就是（L-N）。
 * 也就是说我们从头节点再向后遍历走（L-N）个节点得到的就是环的入口节点。但是我们没法知道链表长度吗，所以我们改用另一种方法。
 * P1节点从头向后遍历走N步。这个节点再向前走（L-N）步就回到环的入口了。
 * P2从头节点向后遍历走（L-N）步，此时刚到达环的入口。此时条件是P1=P2。
 * <p>
 * 2. 找到环的入口。我们还是用两个指针来解决这个问题。先定义两个指针P1和P2指向链表的头节点。如果链表中环有n个节点，
 * 指针P1在链表上向前移动n步，然后两个指针以相同的速度向前移动。当第二个指针指向环的入口节点时，
 * 第一个指针已经围绕着环走了一圈又回到了入口节点。
 * <p>
 * 3. 剩下的问题就是如何得到环中节点的数目。我们在前面判断一个链表里是否有环的时候用到了一快一慢的两个指针。如果两个指针相遇，
 * 表明链表中存在环。两个指针相遇的节点一定是在环中。可以从这个节点出发，一边继续向前移动一边计数，
 * 当再次回到这个节点时就可以得到环中节点数了。
 * <p>
 * <p>
 * 关于快指针一次走两步，慢指针一次走一步一定相遇的问题：
 * 这个问题你可以用数学归纳法来思考。
 * 首先，由于链表是个环，所以相遇的过程可以看作是快指针从后边追赶慢指针的过程。那么做如下思考：
 * 1：快指针与慢指针之间差一步。此时继续往后走，慢指针前进一步，快指针前进两步，两者相遇。
 * 2：快指针与慢指针之间差两步。此时继续往后走，慢指针前进一步，快指针前进两步，两者之间相差一步，转化为第一种情况。
 * 3：快指针与慢指针之间差N步。此时继续往后走，慢指针前进一步，快指针前进两步，两者之间相差(N+1-2)-> N-1步。
 * <p>
 * 从上面三点可以得出：如果环的长度为N，那么当慢指针进入到环的入口的时候快指针已经在环中了，慢指针和快指针之间的步数之差最大是（N-1）。
 * 所以当慢指针绕环不到一圈的时候一定会和快指针相遇。
 *
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
        public ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "Node val is " + val + "\n";
        }
    }

    public static void main(String[] args) {
        test01();
        test02();
        test03();
        test04();
        test05();
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
        //注释1，只有一个节点，没有环，返回null
        if (slow == null) {
            return null;
        }

        //注释2，fast指针比slow指针快一步，也就是head.next.next
        ListNode fast = slow.next;

        while (fast != null && slow != null) {
            //注释3，快慢指针相遇，存在环
            if (fast == slow) {
                return fast;
            }
            slow = slow.next;
            //注释4，fast每次走两步 "没病，走两步" -- 赵本山
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            }
        }
        //链表中没有环
        return fast;
    }

    /**
     * @param head
     * @return 返回环的入口节点
     */
    public static ListNode loopNode(ListNode head) {
        //如果meetingNode不为null，说明链表有环
        // 1->2->3->4->5->6
        //       ^        |
        //       |        |
        //       +--------+
        //比如这个链表，meetingNode可以是3，4，5，6中的任意一个节点
        ListNode meetingNode = meetingNode1(head);
        if (meetingNode == null) {
            return null;
        }
        /**
         * 环中节点的个数
         */
        int nodesInLoop = 1;
        ListNode node1 = meetingNode;
        while (node1.next != meetingNode) {
            node1 = node1.next;
            nodesInLoop++;
        }
        System.out.println("环的长度是：" + nodesInLoop);
        //将node1赋值为head，然后向前走的长度就是环的长度
        node1 = head;
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

        System.out.println(loopNode(n1));
    }

    // 1->2->3->4->5->6->7
    //       ^          |
    //       |          |
    //       +----------+
    private static void test04() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n3;

        System.out.println(loopNode(n1));
    }

    // 1->2->3->4->5->6->7
    //          ^       |
    //          |       |
    //          +-------+
    private static void test05() {
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);

        n1.next = n2;
        n2.next = n3;
        n3.next = n4;
        n4.next = n5;
        n5.next = n6;
        n6.next = n7;
        n7.next = n4;

        System.out.println(loopNode(n1));
    }


}
