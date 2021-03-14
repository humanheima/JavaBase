package com.hm.handwrite;


import com.hm.structure.BinaryTreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Created by dumingwei on 2020/3/25
 * <p>
 * Desc: 二叉树相关
 */
public class BinaryTreeHandWrite {

    public static void main(String[] args) {

        BinaryTreeNode root = createTree();
        frontTraversal(root);
    }

    public static void recurseFront(BinaryTreeNode root) {

        if (root == null) {
            return;
        }
        System.out.println(root);
        recurseFront(root.left);
        recurseFront(root.right);
    }

    public static void frontTraversal(BinaryTreeNode root) {

        Deque<BinaryTreeNode> stack = new ArrayDeque<>();
        BinaryTreeNode node = root;
        while (node != null || !stack.isEmpty()) {

            while (node != null) {
                System.out.println(node.value + " ");
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

}
