package com.hm.base.interview.android;

import com.hm.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by p_dmweidu on 2025/7/10
 * Desc: 二叉树的最小深度.md
 */
class TreeMinDepth {

    public static void main(String[] args) {

        TreeMinDepth solution = new TreeMinDepth();

        // 测试用例1：普通二叉树
        //     1
        //    / \
        //   2   3
        //  / \
        // 4   5
        TreeNode root1 = new TreeNode(1);
        root1.left = new TreeNode(2);
        root1.right = new TreeNode(3);
        root1.left.left = new TreeNode(4);
        root1.left.right = new TreeNode(5);
        System.out.println("测试用例1:");
        System.out.println("最小深度（递归法）: " + solution.minDepthRecursive(root1)); // 应为2
        System.out.println("最小深度（迭代法）: " + solution.minDepthIterative(root1)); // 应为2

        // 测试用例2：空树
        TreeNode root2 = null;
        System.out.println("\n测试用例2（空树）:");
        System.out.println("最小深度（递归法）: " + solution.minDepthRecursive(root2)); // 应为0
        System.out.println("最小深度（迭代法）: " + solution.minDepthIterative(root2)); // 应为0

        // 测试用例3：单节点树
        TreeNode root3 = new TreeNode(1);
        System.out.println("\n测试用例3（单节点树）:");
        System.out.println("最小深度（递归法）: " + solution.minDepthRecursive(root3)); // 应为1
        System.out.println("最小深度（迭代法）: " + solution.minDepthIterative(root3)); // 应为1

        // 测试用例4：不平衡树
        //     1
        //    /
        //   2
        //  /
        // 3
        TreeNode root4 = new TreeNode(1);
        root4.left = new TreeNode(2);
        root4.left.left = new TreeNode(3);
        System.out.println("\n测试用例4（不平衡树）:");
        System.out.println("最小深度（递归法）: " + solution.minDepthRecursive(root4)); // 应为3
        System.out.println("最小深度（迭代法）: " + solution.minDepthIterative(root4)); // 应为3
    }


    public int minDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        if (root.left == null) {
            return minDepthRecursive(root.right) + 1;
        }
        if (root.right == null) {
            return minDepthRecursive(root.left) + 1;
        }
        return Math.min(minDepthRecursive(root.left), minDepthRecursive(root.right)) + 1;
    }

    public int minDepthIterative(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            depth++;
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left == null && node.right == null) {
                    return depth;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
        }
        return depth;
    }
}

