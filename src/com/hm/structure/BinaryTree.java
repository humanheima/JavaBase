package com.hm.structure;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dumingwei on 2017/10/7.
 */
public class BinaryTree {

    public static void main(String[] args) {
        Node head = createTree();
        //recurseFront(head);
        //recurseMid(head);
        //recurseEnd(head);
        //reverseBinaryTree(head);
        //recurseMid(head);
        //printTree(head);
        //System.out.println(maxDepth(head));
        System.out.println(getMinDepth(head));
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
        Node headRightRight = new Node(7);
        // 为head节点 赋予左右值
        head.setLeft(headLeft);
        head.setRight(headRight);

        headLeft.setLeft(headLeftLeft);
        headLeft.setRight(headLeftRigth);
        //headRight.setLeft(headRightLeft);
        //headRight.setRight(headRightRight);

        // 返回树根节点
        return head;
    }

    private static Node reverseBinaryTree(Node root) {
        if (root == null) {
            return null;
        } else {
            Node left = reverseBinaryTree(root.getLeft());
            Node right = reverseBinaryTree(root.getRight());
            root.left = right;
            root.right = left;
            return root;
        }

    }

    private static void printTree(Node root) {
        if (root == null)
            return;
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.println("current = " + current.value);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    //获取最大深度
    private static int maxDepth(Node node) {
        if (node == null)
            return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    //获取最小深度
    private static int getMinDepth(Node root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    private static int getMin(Node root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }

}
