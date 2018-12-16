package com.hm.base.interview.sword_to_offer;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by dmw on 2018/12/16.
 * Desc:之字形打印二叉树
 * 即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行按照从左到右的顺序打印，以此类推。
 * 参考根目录下的test32_3.png
 * <p>
 * 解题思路：按之字形顺序打印二叉树需要两个栈。我们在打印某一行节点时，把下一层的子节点保存到另一个栈里。
 * 如果当前打印的是奇数层，则先保存左子节点再保存右子节点到一个栈里；如果当前打印的是偶数层，则先保存右子节点再保存左子节点到第二个栈里。
 * <p>
 * 测试用例
 * 完全二叉树
 * 除了叶节点外，所有节点只有左节点
 * 除了叶节点外，所有节点只有右节点
 * 根节点为null
 * 只有一个节点的二叉树
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46705719
 */
public class Test32_3 {

    public static void main(String[] args) {
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 8;
        root.left = new BinaryTreeNode();
        root.left.value = 6;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 5;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 10;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 9;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 11;
        printFromTopToBottomRoundabout(root);

        //         1
        //        /
        //       3
        //      /
        //     5
        //    /
        //   7
        //  /
        // 9
        BinaryTreeNode root2 = new BinaryTreeNode();
        root2.value = 1;
        root2.left = new BinaryTreeNode();
        root2.left.value = 3;
        root2.left.left = new BinaryTreeNode();
        root2.left.left.value = 5;
        root2.left.left.left = new BinaryTreeNode();
        root2.left.left.left.value = 7;
        root2.left.left.left.left = new BinaryTreeNode();
        root2.left.left.left.left.value = 9;
        System.out.println("\n");
        printFromTopToBottomRoundabout(root2);

        // 0
        //  \
        //   2
        //    \
        //     4
        //      \
        //       6
        //        \
        //         8
        BinaryTreeNode root3 = new BinaryTreeNode();
        root3.value = 0;
        root3.right = new BinaryTreeNode();
        root3.right.value = 2;
        root3.right.right = new BinaryTreeNode();
        root3.right.right.value = 4;
        root3.right.right.right = new BinaryTreeNode();
        root3.right.right.right.value = 6;
        root3.right.right.right.right = new BinaryTreeNode();
        root3.right.right.right.right.value = 8;
        System.out.println("\n");
        printFromTopToBottomRoundabout(root3);

        // 1
        BinaryTreeNode root4 = new BinaryTreeNode();
        root4.value = 1;
        System.out.println("\n");
        printFromTopToBottomRoundabout(root4);

        // null
        System.out.println("\n");
        printFromTopToBottomRoundabout(null);
    }

    /**
     * 之字形打印二叉树
     *
     * @param root
     */
    private static void printFromTopToBottomRoundabout(BinaryTreeNode root) {
        if (root != null) {
            Deque<BinaryTreeNode> current = new LinkedList<>();
            Deque<BinaryTreeNode> reverse = new LinkedList<>();

            int flag = 0;
            BinaryTreeNode node;
            current.add(root);
            while (current.size() > 0) {
                node = current.pop();
                System.out.print(node.value + " ");
                if (flag == 0) {// 当前是从左往右打印的，那就按从左往右入栈
                    if (node.left != null) {
                        reverse.push(node.left);
                    }
                    if (node.right != null) {
                        reverse.push(node.right);
                    }
                } else {// 当前是从右往左打印的，那就按从右往左入栈
                    if (node.right != null) {
                        reverse.push(node.right);
                    }
                    if (node.left != null) {
                        reverse.push(node.left);
                    }
                }
                if (current.size() == 0) {
                    flag = 1 - flag;
                    Deque<BinaryTreeNode> temp = current;
                    current = reverse;
                    reverse = temp;
                    System.out.println();
                }
            }
        }
    }


}
