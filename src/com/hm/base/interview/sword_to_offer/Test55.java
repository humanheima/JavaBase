package com.hm.base.interview.sword_to_offer;

import com.hm.structure.TreeNode;

/**
 * Created by dumingwei on 2022/5/26
 * <p>
 * Desc:1. 求二叉树的深度
 * 2. 判断一个二叉树是否是平衡的
 * 力扣题解：
 * https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/solution/ping-heng-er-cha-shu-by-leetcode-solutio-6r1g/
 */
public class Test55 {


    public static void main(String[] args) {
        Test55 test55 = new Test55();
        test55.test1();
    }

    private void test1() {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(6);

        root.left = new TreeNode(2);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.left.right.left = new TreeNode(7);
        System.out.println(treeDepth(root));

        System.out.println(isBalanced1(root));
    }


    public int treeDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftChildDepth = treeDepth(root.left);
        int rightChildDepth = treeDepth(root.right);

        return leftChildDepth > rightChildDepth ? (leftChildDepth + 1) : (rightChildDepth + 1);
    }


    /**
     * @param root
     * @return
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null) {
            return true;
        }

        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        int diff = left - right;
        if (diff < -1 || diff > 1) {
            return true;
        }
        return isBalanced1(root.left) && isBalanced1(root.right);
    }

    /**
     * 力扣上的官方解法
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return height(root) >= 0;
    }

    /**
     * 如果一棵子树是平衡的，则返回其高度（高度一定是非负整数），否则返回 −1。如果存在一棵子树不平衡，则整个二叉树一定不平衡。
     *
     * @param root
     * @return
     */
    public int height(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        if (leftHeight == -1 || rightHeight == -1 || Math.abs(leftHeight - rightHeight) > 1) {
            return -1;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }


}
