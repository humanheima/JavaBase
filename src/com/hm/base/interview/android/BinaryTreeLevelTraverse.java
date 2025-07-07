package com.hm.base.interview.android;

import com.hm.structure.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 什么是层序遍历
 * 参考算法解题思路/二叉树的层序遍历.md 懂了
 */
public class BinaryTreeLevelTraverse {


    public static void main(String[] args) {
        BinaryTreeLevelTraverse solution = new BinaryTreeLevelTraverse();

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

        System.out.println("测试用例1（普通二叉树）:");
        System.out.println("层序遍历: " + solution.levelOrder(root1)); // [1, 2, 3, 4, 5]
        System.out.println("按层分开: " + solution.levelOrderByLevel(root1)); // [[1], [2, 3], [4, 5]]

        // 测试用例2：空树
        TreeNode root2 = null;
        System.out.println("\n测试用例2（空树）:");
        System.out.println("层序遍历: " + solution.levelOrder(root2)); // []
        System.out.println("按层分开: " + solution.levelOrderByLevel(root2)); // []

        // 测试用例3：单节点树
        TreeNode root3 = new TreeNode(1);
        System.out.println("\n测试用例3（单节点树）:");
        System.out.println("层序遍历: " + solution.levelOrder(root3)); // [1]
        System.out.println("按层分开: " + solution.levelOrderByLevel(root3)); // [[1]]

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
        System.out.println("层序遍历: " + solution.levelOrder(root4)); // [1, 2, 3]
        System.out.println("按层分开: " + solution.levelOrderByLevel(root4)); // [[1], [2], [3]]
    }


    // 简单层序遍历
    public List<Integer> levelOrder(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            result.add(node.val);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        return result;
    }

    // 按层分开返回
    public List<List<Integer>> levelOrderByLevel(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                currentLevel.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            result.add(currentLevel);
        }
        return result;
    }
}
