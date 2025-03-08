package com.hm.handwrite;


import com.hm.structure.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by dumingwei on 2020/3/25
 * <p>
 * Desc: 二叉树相关
 */
public class BinaryTreeHandWrite {

    public static void main(String[] args) {

        TreeNode root = createTree();
        frontTraversal(root);
    }

    public static void recurseFront(TreeNode root) {

        if (root == null) {
            return;
        }
        System.out.println(root);
        recurseFront(root.left);
        recurseFront(root.right);
    }

    public static void frontTraversal(TreeNode root) {

        Deque<TreeNode> stack = new ArrayDeque<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                System.out.println(node.val + " ");
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

    public static TreeNode createTree() {
        // 初始化节点
        TreeNode root = new TreeNode(1);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootRight = new TreeNode(3);

        TreeNode rootLeftLeft = new TreeNode(4);
        TreeNode rootLeftLeftRight = new TreeNode(6);
        TreeNode rootLeftLeftRightLeft = new TreeNode(7);
        TreeNode rootLeftLeftRightRight = new TreeNode(8);
        TreeNode rootRightRight = new TreeNode(5);
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

}
