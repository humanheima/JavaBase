package com.hm.base.interview.sword_to_offer;

import java.util.*;

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


    /**
     * 之字形打印二叉树
     *
     * @param root
     */
    private static void printFromTopToBottomRoundabout(TreeNode root) {
        if (root != null) {
            Deque<TreeNode> current = new LinkedList<>();
            Deque<TreeNode> reverse = new LinkedList<>();

            int flag = 0;
            TreeNode node;
            current.add(root);
            while (current.size() > 0) {
                node = current.pop();
                System.out.print(node.val + " ");
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
                    Deque<TreeNode> temp = current;
                    current = reverse;
                    reverse = temp;
                    System.out.println();
                }
            }
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        int flag = 0;
        Stack<TreeNode> current = new Stack<>();
        Stack<TreeNode> reverse = new Stack<>();
        List<Integer> tempList = new ArrayList<>();

        current.add(root);
        while (current.size() > 0) {
            TreeNode node = current.pop();
            tempList.add(node.val);
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
                result.add(tempList);
                tempList = new ArrayList<>();
                flag = 1 - flag;
                Stack<TreeNode> temp = current;
                current = reverse;
                reverse = temp;
            }
        }

        return result;
    }


    public static void main(String[] args) {
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        TreeNode root = new TreeNode();
        root.val = 8;
        root.left = new TreeNode();
        root.left.val = 6;
        root.left.left = new TreeNode();
        root.left.left.val = 5;
        root.left.right = new TreeNode();
        root.left.right.val = 7;
        root.right = new TreeNode();
        root.right.val = 10;
        root.right.left = new TreeNode();
        root.right.left.val = 9;
        root.right.right = new TreeNode();
        root.right.right.val = 11;
        printFromTopToBottomRoundabout(root);

        Test32_3 test32_3 = new Test32_3();


        System.out.println("*************************");
        System.out.println(test32_3.levelOrder(root));
        System.out.println("*************************");

        //         1
        //        /
        //       3
        //      /
        //     5
        //    /
        //   7
        //  /
        // 9
        TreeNode root2 = new TreeNode();
        root2.val = 1;
        root2.left = new TreeNode();
        root2.left.val = 3;
        root2.left.left = new TreeNode();
        root2.left.left.val = 5;
        root2.left.left.left = new TreeNode();
        root2.left.left.left.val = 7;
        root2.left.left.left.left = new TreeNode();
        root2.left.left.left.left.val = 9;
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
        TreeNode root3 = new TreeNode();
        root3.val = 0;
        root3.right = new TreeNode();
        root3.right.val = 2;
        root3.right.right = new TreeNode();
        root3.right.right.val = 4;
        root3.right.right.right = new TreeNode();
        root3.right.right.right.val = 6;
        root3.right.right.right.right = new TreeNode();
        root3.right.right.right.right.val = 8;
        System.out.println("\n");
        printFromTopToBottomRoundabout(root3);

        // 1
        TreeNode root4 = new TreeNode();
        root4.val = 1;
        System.out.println("\n");
        printFromTopToBottomRoundabout(root4);

        // null
        System.out.println("\n");
        printFromTopToBottomRoundabout(null);
    }


}
