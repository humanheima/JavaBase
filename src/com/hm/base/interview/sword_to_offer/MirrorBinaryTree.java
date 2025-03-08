package com.hm.base.interview.sword_to_offer;

import com.hm.structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Crete by dumingwei on 2020-02-09
 * Desc: 二叉树的镜像
 * 参考链接
 * [笔试面试算法经典--二叉树的镜像-递归与非递归实现（Java）](https://blog.csdn.net/u013309870/article/details/69952085)
 */
public class MirrorBinaryTree {


    public static void main(String[] args) {

        TreeNode root = new TreeNode(8);

        TreeNode left = new TreeNode(6);
        TreeNode right = new TreeNode(10);

        TreeNode leftleft = new TreeNode(5);
        TreeNode leftright = new TreeNode(7);

        TreeNode rightleft = new TreeNode(9);
        TreeNode rightright = new TreeNode(11);

        left.left = leftleft;
        left.right = leftright;

        right.left = rightleft;
        right.right = rightright;

        root.left = left;
        root.right = right;

        recurseFront(root);

        System.out.println();
        //mirrorTreeRecursively(root);
        //mirrorTreeWithQueue(root);
        mirrorTreeWithStack(root);
        //前序遍历输出
        recurseFront(root);

    }

    /**
     * 递归实现的前序遍历
     *
     * @param root
     */
    public static void recurseFront(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.val + " ");
        recurseFront(root.left);
        recurseFront(root.right);
    }


    /**
     * 二叉树的镜像，递归版本
     *
     * @param root
     */
    public static void mirrorTreeRecursively(TreeNode root) {
        if (root == null) {
            return;
        }
        //交换该节点指向的左右节点。
        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

        //对其左右孩子进行镜像处理。
        mirrorTreeRecursively(root.left);
        mirrorTreeRecursively(root.right);
    }

    /**
     * 非递归版本，借助队列
     *
     * @param root
     */
    public static void mirrorTreeWithQueue(TreeNode root) {
        if (root == null) {
            return;
        }
        //如果树为 null 直接返回。否则将根节点入队列。
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            //队列不为空时，节点出队，交换该节点的左右子树。
            TreeNode root1 = queue.poll();

            swap(root1);

            if (root1.right != null) {
                queue.add(root1.right);
            }

            if (root1.left != null) {
                queue.add(root1.left);
            }
        }

    }


    /**
     * 非递归版本，借助栈
     * <p>
     * 8 6 5 7 10 9 11
     *
     * @param root
     */
    public static void mirrorTreeWithStack(TreeNode root) {
        if (root == null)
            return;
        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            //当栈不为 null 时出栈，交换左右子树。
            TreeNode root1 = stack.pop();
            swap(root1);

            if (root1.left != null) {
                //左子树不为 null 入栈
                stack.push(root1.left);
            }

            if (root1.right != null) {
                //右子树不为 null 入栈
                stack.push(root1.right);
            }

        }
    }

    public static void swap(TreeNode root) {
        TreeNode temp;
        temp = root.right;
        root.right = root.left;
        root.left = temp;
    }

}
