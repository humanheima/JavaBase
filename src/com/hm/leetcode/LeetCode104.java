package com.hm.leetcode;

import com.hm.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by p_dmweidu on 2025/4/4
 * Desc: 树的最大深度
 * 二叉树的最大深度.md
 */
public class LeetCode104 {

    public static void main(String[] args) {
        // 创建测试用的二叉树
        //     1
        //    / \
        //   2   3
        //  / \
        // 4   5
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        LeetCode104 solution = new LeetCode104();

        // 测试递归法
        int depthRecursive = solution.maxDepthRecursive(root);
        System.out.println("最大深度（递归法）: " + depthRecursive);

        // 测试迭代法
        int depthIterative = solution.maxDepthIterative(root);
        System.out.println("最大深度（迭代法）: " + depthIterative);

        // 测试空树
//        TreeNode emptyTree = null;
//        System.out.println("空树深度（递归法）: " + solution.maxDepthRecursive(emptyTree));
//        System.out.println("空树深度（迭代法）: " + solution.maxDepthIterative(emptyTree));
//
//        // 测试单节点树
//        TreeNode singleNode = new TreeNode(1);
//        System.out.println("单节点树深度（递归法）: " + solution.maxDepthRecursive(singleNode));
//        System.out.println("单节点树深度（迭代法）: " + solution.maxDepthIterative(singleNode));
    }


    // 递归法
    public int maxDepthRecursive(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthRecursive(root.left);
        int rightDepth = maxDepthRecursive(root.right);
        return Math.max(leftDepth, rightDepth) + 1;
    }

    /**
     * 迭代法（广度优先搜索BFS）
     * 基本思想：按层遍历树，记录层数
     * 使用队列来逐层访问节点，每处理完一层，深度加1
     *
     * @param root
     * @return
     */
    public int maxDepthIterative(TreeNode root) {
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
