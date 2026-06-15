package com.hm.leetcode.code194;

import java.util.HashMap;
import java.util.Map;

/**
 * Crete by dumingwei on 2026-06-15
 * LCR 194. 二叉树的最近公共祖先（剑指 Offer 68 - II）
 * Desc: 给定一棵二叉树，找出树中两个指定节点 p、q 的最近公共祖先（LCA）。
 * 最近公共祖先定义为：对于有根树的两个节点 p、q，最近公共祖先表示一个节点 x，
 * 满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 * 说明：所有节点的值互不相同；p、q 均存在于给定的二叉树中。
 *
 * 解题思路：
 * 1. 递归后序遍历：自底向上回溯，左右子树各命中一个目标，当前节点即 LCA。O(n)，最优。
 * 2. 父指针 + 哈希表：先记录每个节点的父节点，再沿 p 的祖先链标记，q 上溯遇到的第一个
 *    被标记节点即 LCA。思路直观、可改造为“无 root 引用、只给两节点”的场景。O(n)。
 *
 * 链接：https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 */
public class LowestCommonAncestor {

    /**
     * 二叉树节点
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
     * 含义：在以 root 为根的子树中查找 p、q，返回其 LCA；都不在则返回 null。
     * 1. 出口：root 为空，或 root 命中 p / q，直接返回 root。
     * 2. 左右子树分别递归。
     * 3. 左右都非空 -> p、q 分居两侧，root 即 LCA；否则返回非空的一侧上抛。
     * <p>
     * 时间复杂度：O(n)，每个节点访问一次。
     * 空间复杂度：O(h)，递归栈，h 为树高，最坏 O(n)。
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
        return left != null ? left : right;
    }

    /**
     * 解法二：父指针 + 哈希表
     * <p>
     * 1. 一次遍历记录 “子节点 -> 父节点” 映射。
     * 2. 从 p 出发沿父链向上，把途经节点（含 p 自己）全部放入 visited。
     * 3. 从 q 出发沿父链向上，第一个出现在 visited 中的节点即为 LCA。
     * <p>
     * 时间复杂度：O(n)。
     * 空间复杂度：O(n)，存父指针映射与 visited 集合。
     */
    public static TreeNode lowestCommonAncestorByParent(TreeNode root, TreeNode p, TreeNode q) {
        Map<TreeNode, TreeNode> parent = new HashMap<>();
        parent.put(root, null);
        buildParent(root, parent);

        // p 的祖先链（含自身）
        java.util.Set<TreeNode> visited = new java.util.HashSet<>();
        for (TreeNode cur = p; cur != null; cur = parent.get(cur)) {
            visited.add(cur);
        }
        // q 上溯，第一个被访问过的节点即 LCA
        for (TreeNode cur = q; cur != null; cur = parent.get(cur)) {
            if (visited.contains(cur)) {
                return cur;
            }
        }
        return null;
    }

    private static void buildParent(TreeNode node, Map<TreeNode, TreeNode> parent) {
        if (node == null) {
            return;
        }
        if (node.left != null) {
            parent.put(node.left, node);
            buildParent(node.left, parent);
        }
        if (node.right != null) {
            parent.put(node.right, node);
            buildParent(node.right, parent);
        }
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

        check(n3, n5, n1, 3); // 分居根两侧
        check(n3, n5, n4, 5); // 一个是另一个的祖先：5 是 4 的祖先
        check(n3, n7, n4, 2); // 同属子树 2
        check(n3, n6, n8, 3); // 跨越整棵树
        check(n3, n7, n8, 3);
    }

    private static void check(TreeNode root, TreeNode p, TreeNode q, int expected) {
        TreeNode r1 = lowestCommonAncestor(root, p, q);
        TreeNode r2 = lowestCommonAncestorByParent(root, p, q);
        System.out.printf("LCA(%d, %d) 递归=%d 父指针=%d 期望=%d %s%n",
                p.val, q.val, r1.val, r2.val, expected,
                (r1.val == expected && r2.val == expected) ? "✓" : "✗");
    }
}
