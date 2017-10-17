package com.hm.binary_tree;

/**
 * Created by dumingwei on 2017/10/7.
 */
public class binary_tree {

    public static void main(String[] args) {
        Node head = createTree();
        recurseFront(head);
        //recurseMid(head);
        //recurseEnd(head);
    }

    /**
     * 递归实现的先序遍历
     *
     * @param head
     */
    public static void recurseFront(Node head) {
        if (head == null) {
            return;
        }
        System.out.println("当前节点值：" + head.getValue());
        recurseFront(head.left);
        recurseFront(head.right);
    }

    /**
     * 递归实现的中序遍历
     *
     * @param head
     */
    public static void recurseMid(Node head) {
        if (head == null)
            return;
        recurseMid(head.getLeft());
        System.out.println("当前节点的值：" + head.getValue());
        recurseMid(head.getRight());
    }

    /**
     * 递归实现的后序遍历递归实现
     *
     * @param head
     */
    public static void recurseEnd(Node head) {
        if (head == null)
            return;
        recurseEnd(head.getLeft());
        recurseEnd(head.getRight());
        System.out.println("当前节点的值为：" + head.getValue());
    }

    public static Node createTree() {
        // 初始化节点
        Node head = new Node(1);
        Node headLeft = new Node(2);
        Node headRight = new Node(3);
        Node headLeftLeft = new Node(4);
        Node headLeftRigth = new Node(5);
        Node headRightLeft = new Node(6);
        // 为head节点 赋予左右值
        head.setLeft(headLeft);
        head.setRight(headRight);

        headLeft.setLeft(headLeftLeft);
        headLeft.setRight(headLeftRigth);
        headRight.setLeft(headRightLeft);

        // 返回树根节点
        return head;
    }

}
