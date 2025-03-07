package com.hm.base.interview.android;

import com.hm.structure.TreeNode;

/**
 * 验证二叉搜索树（BST）（中序遍历或递归边界），算法原理解析
 *
 * 使用第2种方式
 *
 * 或者另外一种方式
 *
 * 检查中序遍历结果是否严格递增即可判断是否为有效的二叉搜索树。
 *
 */
class ValidateBinaryTreeTest {


    private TreeNode prev = null;  // 记录前一个访问的节点


    public static void main(String[] args) {
        ValidateBinaryTreeTest validateBinaryTreeTest = new ValidateBinaryTreeTest();

        // 测试用例1：有效BST
        //     5
        //    / \
        //   3   7
        TreeNode root1 = new TreeNode(5);
        root1.left = new TreeNode(3);
        root1.right = new TreeNode(7);
        System.out.println("Test1: " + validateBinaryTreeTest.isValidBST(root1));  // true

        // 测试用例2：无效BST
        //     5
        //    / \
        //   3   4
        TreeNode root2 = new TreeNode(5);
        root2.left = new TreeNode(3);
        root2.right = new TreeNode(4);
        //System.out.println("Test2: " + solution.isValidBST(root2));  // false
    }

    public boolean isValidBST(TreeNode root) {
        prev = null;  // 重置prev
        return inorder(root);
    }

    private boolean inorder(TreeNode root) {
        if (root == null) {
            return true;  // 空树是BST
        }

        // 遍历左子树
        if (!inorder(root.left)) {
            return false;
        }

        // 检查当前节点与前一个节点
        if (prev != null && root.value <= prev.value) {
            return false;  // 必须严格大于，不能等于
        }
        prev = root;  // 更新prev

        // 遍历右子树
        return inorder(root.right);
    }


    /**
     * 第2种方式，感觉这种比较好理解。但是开始的边界其实是写死了。
     * @param root
     * @return
     */
    public boolean isValidBST2(TreeNode root) {
        return validate(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean validate(TreeNode node, long min, long max) {
        if (node == null) {
            return true;  // 空树是BST
        }

        // 检查当前节点值是否在范围内
        if (node.value <= min || node.value >= max) {
            return false;
        }

        // 递归检查左右子树，更新边界
        return validate(node.left, min, node.value) &&  // 左子树上限为当前值
                validate(node.right, node.value, max);   // 右子树下限为当前值
    }
}