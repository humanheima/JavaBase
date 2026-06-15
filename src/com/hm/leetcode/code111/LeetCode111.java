package com.hm.leetcode.code111;

import com.hm.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Crete by dumingwei on 2026-06-14
 * 111. 二叉树的最小深度
 * Desc: 给定一个二叉树，找出其最小深度。最小深度是从根节点到最近【叶子节点】的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 解题思路：
 * 关键陷阱——与「最大深度」不同：当一个节点只有一个孩子时，
 * 不能用 min(左, 右) + 1，否则会被空子树的 0 带偏（把内部节点误当成叶子）。
 * 必须保证统计到的是真正的【叶子】路径。
 * 1. 递归 DFS：对「只有一个孩子」的节点特判，只往非空子树递归。
 * 2. 迭代 BFS：按层遍历，遇到的第一个叶子所在的层就是最小深度（最先触底）。
 *
 * 链接：https://leetcode.cn/problems/minimum-depth-of-binary-tree/
 */
public class LeetCode111 {

    public static void main(String[] args) {
        LeetCode111 solution = new LeetCode111();

        //       3
        //      / \
        //     9  20
        //        / \
        //       15  7      期望最小深度 = 2（根 3 → 叶子 9）
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(20);
        root.right.left = new TreeNode(15);
        root.right.right = new TreeNode(7);
        System.out.println("用例1 递归=" + solution.minDepth(root) + " BFS=" + solution.minDepthBfs(root));

        // 右斜链：2 -> 3 -> 4 -> 5 -> 6，唯一叶子在最底层，期望 5
        TreeNode skew = new TreeNode(2);
        skew.right = new TreeNode(3);
        skew.right.right = new TreeNode(4);
        skew.right.right.right = new TreeNode(5);
        skew.right.right.right.right = new TreeNode(6);
        System.out.println("用例2 递归=" + solution.minDepth(skew) + " BFS=" + solution.minDepthBfs(skew));

        // 空树 期望 0；单节点 期望 1
        System.out.println("空树  递归=" + solution.minDepth(null) + " BFS=" + solution.minDepthBfs(null));
        System.out.println("单节点 递归=" + solution.minDepth(new TreeNode(1)) + " BFS=" + solution.minDepthBfs(new TreeNode(1)));
    }

    /**
     * 解法一：递归 DFS
     * 时间复杂度：O(n)，每个节点访问一次
     * 空间复杂度：O(h)，h 为树高（递归栈），最坏 O(n)
     */
    public int minDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        // 叶子节点：深度为 1
        if (root.left == null && root.right == null) {
            return 1;
        }
        // 只有右子树：必须往右走，不能算空的左子树
        if (root.left == null) {
            return minDepth(root.right) + 1;
        }
        // 只有左子树：必须往左走
        if (root.right == null) {
            return minDepth(root.left) + 1;
        }
        // 左右子树都存在：取较小者
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
    }

    /**
     * 解法二：迭代 BFS（层序遍历）
     * 逐层向下，遇到第一个叶子节点时，当前层号即为最小深度。
     * BFS 最先到达的叶子一定在最浅的那一层，因此可提前返回，通常比 DFS 更快。
     * 时间复杂度：O(n)（最坏；通常提前返回）
     * 空间复杂度：O(n)，队列最多存一层节点
     */
    public int minDepthBfs(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int depth = 1;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                // 第一个遇到的叶子，所在层就是最小深度
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
            depth++;
        }
        return depth;
    }

}
