package com.hm.structure;

import java.util.*;

/**
 * Created by dumingwei on 2017/10/7.
 * <p>
 * 遍历的树以项目根目录下的BinaryTree.png为例
 * 前序遍历：1，2，4，6，7，8，3，5
 * 中序遍历：4，7，6，8，2，1，3，5
 * 后续遍历：7，8，6，4，2，5，3，1
 * Desc:二叉树遍历 递归，非递归。
 * 参考链接：<a href="https://www.jianshu.com/p/456af5480cee></a>
 * <p>
 * https://blog.csdn.net/snow_7/article/details/51818580
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTreeNode root = createTree();
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

        //recurseEnd(root);
        //System.out.println();
        //postOrderTraversal(root);
        //System.out.println();

        System.out.println(findDeep(root));

    }

    /**
     * 递归实现的前序遍历
     *
     * @param root
     */
    public static void recurseFront(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        recurseFront(root.left);
        recurseFront(root.right);
    }

    /**
     * 非递归前序遍历
     *
     * @param root
     */
    public static void preOrderTraversal(BinaryTreeNode root) {
        Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        BinaryTreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                System.out.print(node.value + " ");
                //为了之后能找到该节点的右子树，暂存该节点
                stack.push(node);
                node = node.left;
            }
            //一直到当前节点的左子树为null，开始考虑当前节点的右子树
            if (!stack.isEmpty()) {
                node = stack.pop();
                node = node.right;
            }
        }

    }


    /**
     * 递归实现的中序遍历
     *
     * @param root
     */
    public static void recurseMid(BinaryTreeNode root) {
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
    public static void middleOrderTraversal(BinaryTreeNode root) {
        Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        BinaryTreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //一直到当前节点的左子树为null，开始考虑当前节点的右子树
            if (!stack.isEmpty()) {
                node = stack.pop();
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
    public static void recurseEnd(BinaryTreeNode root) {
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
    public static void postOrderTraversal(BinaryTreeNode root) {
        Stack<BinaryTreeNode> treeNodeStack = new Stack<>();
        BinaryTreeNode node = root;
        BinaryTreeNode lastVisit = root;
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


    public static BinaryTreeNode createTree() {
        // 初始化节点
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode rootLeft = new BinaryTreeNode(2);
        BinaryTreeNode rootRight = new BinaryTreeNode(3);

        BinaryTreeNode rootLeftLeft = new BinaryTreeNode(4);
        BinaryTreeNode rootLeftLeftRight = new BinaryTreeNode(6);
        BinaryTreeNode rootLeftLeftRightLeft = new BinaryTreeNode(7);
        BinaryTreeNode rootLeftLeftRightRight = new BinaryTreeNode(8);
        BinaryTreeNode rootRightRight = new BinaryTreeNode(5);
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

    private static BinaryTreeNode reverseBinaryTree(BinaryTreeNode root) {
        if (root == null) {
            return null;
        } else {
            BinaryTreeNode left = reverseBinaryTree(root.getLeft());
            BinaryTreeNode right = reverseBinaryTree(root.getRight());
            root.left = right;
            root.right = left;
            return root;
        }

    }

    private static void printTree(BinaryTreeNode root) {
        if (root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();
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
    private static int maxDepth(BinaryTreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    //获取最小深度
    private static int getMinDepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    private static int getMin(BinaryTreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }

    /**
     * 非递归获取树的最大深度
     *
     * @param root
     * @return
     */
    public static int findDeep(BinaryTreeNode root) {
        if (root == null)
            return 0;
        BinaryTreeNode current = null;
        Deque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cur, last;
        int level = 0;
        while (!queue.isEmpty()) {
            cur = 0;//记录本层已经遍历的节点个数
            last = queue.size();//当遍历完当前层以后，队列里元素全是下一层的元素，队列的长度是这一层的节点的个数
            while (cur < last) {
                //当还没有遍历到本层最后一个节点时循环
                current = queue.poll();//出队一个元素
                cur++;
                //把当前节点的左右节点入队（如果存在的话）
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;//每遍历完一层level+1
        }
        return level;
    }

}
