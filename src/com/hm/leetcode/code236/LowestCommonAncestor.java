package com.hm.leetcode.code236;

import java.util.ArrayList;
import java.util.List;

/**
 * Crete by dumingwei on 2026-06-15
 * 二叉树中两个叶子节点的最近公共祖先（LeetCode 236. 二叉树的最近公共祖先）
 * Desc: 给定一棵二叉树，以及树中的两个节点 p、q（本题关注两个叶子节点），
 * 找出它们的最近公共祖先（LCA, Lowest Common Ancestor）。
 * 最近公共祖先定义为：在 root 中，同时拥有 p 和 q 作为后代（一个节点也可以是自己的后代）
 * 的、深度最大的那个节点。
 *
 * 解题思路：
 * 求“两个叶子节点”的 LCA 与求任意两个节点的 LCA 完全一致，叶子只是后代集合为空的特例，
 * 不需要任何额外处理。给出两种解法：
 * 1. 递归后序遍历：自底向上回溯，左右子树各找一个目标，谁同时命中左右谁就是 LCA。O(n)。
 * 2. 两次路径查找：分别求出 root 到两个叶子的路径，再找两条路径最后一个相同节点。O(n)。
 *
 * 链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 */
public class LowestCommonAncestor {

    /**
     * 二叉树节点（本题自带，避免依赖外部辅助类）
     */
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    /**
     * 解法一：递归后序遍历（推荐）
     * <p>
     * 含义：在以 root 为根的子树里查找 p、q，返回它们的 LCA；若都不在该子树则返回 null。
     * 1. 递归出口：root 为空，或 root 命中 p / q，直接返回 root。
     * 2. 分别在左、右子树递归查找。
     * 3. 若左右都返回非空，说明 p、q 分别落在 root 两侧，root 即为 LCA。
     * 4. 否则哪边非空就把哪边上抛（两个目标都在同一侧，或都不存在）。
     * <p>
     * 时间复杂度：O(n)，每个节点最多访问一次。
     * 空间复杂度：O(h)，递归栈深度，h 为树高，最坏 O(n)。
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) {
            return root;
        }
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left != null && right != null) {
            // p、q 分居 root 两侧，当前 root 就是最近公共祖先
            return root;
        }
        // 都在同一侧（或都不在），返回非空的一侧
        return left != null ? left : right;
    }

    /**
     * 解法二：两次路径查找
     * <p>
     * 1. 用 DFS 分别求出 root 到 p、root 到 q 的路径（节点序列）。
     * 2. 同步遍历两条路径，最后一个相同的节点即为 LCA。
     * <p>
     * 时间复杂度：O(n)，两次遍历。
     * 空间复杂度：O(n)，存放两条路径。
     * 优点：思路直观，路径本身可复用（比如求两节点距离）；缺点：要存路径，常数更大。
     */
    public static TreeNode lowestCommonAncestorByPath(TreeNode root, TreeNode p, TreeNode q) {
        List<TreeNode> pathP = new ArrayList<>();
        List<TreeNode> pathQ = new ArrayList<>();
        if (!findPath(root, p, pathP) || !findPath(root, q, pathQ)) {
            return null; // 有节点不在树中
        }
        TreeNode lca = null;
        int n = Math.min(pathP.size(), pathQ.size());
        for (int i = 0; i < n; i++) {
            if (pathP.get(i) == pathQ.get(i)) {
                lca = pathP.get(i);
            } else {
                break;
            }
        }
        return lca;
    }

    /**
     * DFS 求 root 到 target 的路径，找到返回 true，并把路径写入 path。
     */
    private static boolean findPath(TreeNode root, TreeNode target, List<TreeNode> path) {
        if (root == null) {
            return false;
        }
        path.add(root);
        if (root == target) {
            return true;
        }
        if (findPath(root.left, target, path) || findPath(root.right, target, path)) {
            return true;
        }
        path.remove(path.size() - 1); // 回溯
        return false;
    }

    public static void main(String[] args) {
        /*
         * 构造测试二叉树：
         *            3
         *          /   \
         *         5     1
         *        / \   / \
         *       6   2 0   8
         *          / \
         *         7   4
         * 叶子节点：6, 7, 4, 0, 8
         */
        TreeNode n3 = new TreeNode(3);
        TreeNode n5 = new TreeNode(5);
        TreeNode n1 = new TreeNode(1);
        TreeNode n6 = new TreeNode(6);
        TreeNode n2 = new TreeNode(2);
        TreeNode n0 = new TreeNode(0);
        TreeNode n8 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n4 = new TreeNode(4);
        n3.left = n5;
        n3.right = n1;
        n5.left = n6;
        n5.right = n2;
        n1.left = n0;
        n1.right = n8;
        n2.left = n7;
        n2.right = n4;

        // 用例：两个叶子节点的 LCA
        check(n3, n7, n4, 2); // 同属子树 2
        check(n3, n6, n4, 5); // 同属子树 5
        check(n3, n6, n8, 3); // 分居根的两侧
        check(n3, n7, n8, 3); // 跨越整棵树
        check(n3, n0, n8, 1); // 右子树内两个叶子
    }

    private static void check(TreeNode root, TreeNode p, TreeNode q, int expected) {
        TreeNode r1 = lowestCommonAncestor(root, p, q);
        TreeNode r2 = lowestCommonAncestorByPath(root, p, q);
        System.out.printf("LCA(%d, %d) 递归=%d 路径=%d 期望=%d %s%n",
                p.val, q.val, r1.val, r2.val, expected,
                (r1.val == expected && r2.val == expected) ? "✓" : "✗");
    }
}
