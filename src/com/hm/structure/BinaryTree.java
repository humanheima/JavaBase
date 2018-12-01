package com.hm.structure;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by dumingwei on 2017/10/7.
 * <p>
 * 遍历的树以项目根目录下的BinaryTree.png为例
 * 先序遍历：1，2，4，6，7，8，3，5
 * 中序遍历：4，7，6，8，2，1，3，5
 * 后续遍历：7，8，6，4，2，5，3，1
 * Desc:二叉树遍历 递归，非递归。
 * 参考链接：<a href="https://www.jianshu.com/p/456af5480cee></a>
 */
public class BinaryTree {

    public static void main(String[] args) {
        Node root = createTree();
        /*recurseFront(root);
        System.out.println();
        recurseEnd(root);
        reverseBinaryTree(root);
        recurseMid(root);
        printTree(root);
        System.out.println(maxDepth(root));
        System.out.println(getMinDepth(root));
        System.out.println();

        preOrderTraversal(root);
        System.out.println();

        recurseMid(root);
        System.out.println();

        middleOrderTraversal(root);
        System.out.println();*/

        recurseEnd(root);
        System.out.println();
        postOrderTraversal(root);
        System.out.println();


    }

    /**
     * 递归实现的先序遍历
     *
     * @param root
     */
    public static void recurseFront(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        recurseFront(root.left);
        recurseFront(root.right);
    }

    /**
     * 非递归先序遍历
     *
     * @param root
     */
    public static void preOrderTraversal(Node root) {
        Stack<Node> treeNodeStack = new Stack<>();
        Node node = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                System.out.print(node.value + " ");
                //为了之后能找到该节点的右子树，暂存该节点
                treeNodeStack.push(node);
                node = node.left;
            }
            //一直到当前节点的左子树为null，开始考虑当前节点的右子树
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                node = node.right;
            }
        }

    }


    /**
     * 递归实现的中序遍历
     *
     * @param root
     */
    public static void recurseMid(Node root) {
        if (root == null)
            return;
        recurseMid(root.getLeft());
        System.out.print(root.value + " ");
        recurseMid(root.getRight());
    }

    /**
     * 非递归中序遍历
     *
     * @param root
     */
    public static void middleOrderTraversal(Node root) {
        Stack<Node> treeNodeStack = new Stack<>();
        Node node = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            //一直到当前节点的左子树为null，开始考虑当前节点的右子树
            if (!treeNodeStack.isEmpty()) {
                node = treeNodeStack.pop();
                System.out.print(node.value + " ");
                node = node.right;
            }
        }

    }


    /**
     * 递归实现的后序遍历递归实现
     *
     * @param root
     */
    public static void recurseEnd(Node root) {
        if (root == null)
            return;
        recurseEnd(root.getLeft());
        recurseEnd(root.getRight());
        System.out.print(root.value + " ");
    }

    /**
     * 非递归后序遍历
     *
     * @param root
     */
    public static void postOrderTraversal(Node root) {
        Stack<Node> treeNodeStack = new Stack<>();
        Node node = root;
        Node lastVisit = root;
        while (node != null || !treeNodeStack.isEmpty()) {
            while (node != null) {
                treeNodeStack.push(node);
                node = node.left;
            }
            //查看当前栈顶元素
            node = treeNodeStack.peek();
            /**
             * 如果其右子树也为空，或者右子树已经访问，则可以直接输出当前节点的值
             */
            if (node.right == null || node.right == lastVisit) {
                System.out.print(node.value + " ");
                treeNodeStack.pop();
                lastVisit = node;
                node = null;
            } else {
                //继续遍历右子树
                node = node.right;
            }
        }

    }


    public static Node createTree() {
        // 初始化节点
        Node root = new Node(1);
        Node rootLeft = new Node(2);
        Node rootRight = new Node(3);
        Node rootLeftLeft = new Node(4);
        Node rootLeftLeftRight = new Node(6);
        Node rootLeftLeftRightLeft = new Node(7);
        Node rootLeftLeftRightRight = new Node(8);
        Node rootRightRight = new Node(5);
        // 为root节点 赋予左右值
        root.left = rootLeft;
        root.right = rootRight;
        root.left.left = rootLeftLeft;
        root.left.left.right = rootLeftLeftRight;
        root.left.left.right.left = rootLeftLeftRightLeft;
        root.left.left.right.right = rootLeftLeftRightRight;

        root.right.right = rootRightRight;

        // 返回树根节点
        return root;
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
