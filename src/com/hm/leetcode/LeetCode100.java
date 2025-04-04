package com.hm.leetcode;

import com.hm.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by p_dmweidu on 2025/4/4
 * Desc: 相同的树
 */
class LeetCode100 {


    public boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) return true;
        if (p == null || q == null) return false;
        if (p.val != q.val) return false;
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    /**
     * 使用迭代方式实现
     *
     * @param p
     * @param q
     * @return
     */
    public boolean isSameTreeIterative(TreeNode p, TreeNode q) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(p);
        queue.offer(q);

        while (!queue.isEmpty()) {
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            // 两节点都为空，继续
            if (node1 == null && node2 == null) continue;
            // 一为空一不为空，不同
            if (node1 == null || node2 == null) return false;
            // 值不同，不同
            if (node1.val != node2.val) return false;

            // 将子节点成对入队
            queue.offer(node1.left);
            queue.offer(node2.left);
            queue.offer(node1.right);
            queue.offer(node2.right);
        }

        return true;
    }
}