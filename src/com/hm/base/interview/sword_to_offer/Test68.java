package com.hm.base.interview.sword_to_offer;

import com.hm.structure.TreeNode;

import java.util.*;

/**
 * Created by dumingwei on 2022/5/22
 * <p>
 * Desc:剑指 Offer 68 - II. 二叉树的最近公共祖先
 * 说明:
 * <p>
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 * <p>
 * 作者：LeetCode-Solution
 * 链接：https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-6fdt7/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class Test68 {

    public static void main(String[] args) {
        //test1();
        Test68 test68 = new Test68();
        test68.test2();
    }


    private void test2() {
        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(5);

        root.left.left = new TreeNode(6);
        TreeNode val_2 = new TreeNode(2);
        root.left.right = val_2;

        root.left.right.left = new TreeNode(7);
        TreeNode val_4 = new TreeNode(4);
        root.left.right.right = val_4;

        root.right = new TreeNode(1);

        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        lowestCommonAncestor(root, val_2, val_4);


    }

    Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    Set<Integer> visited = new HashSet<Integer>();

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            //获取当前节点的父节点
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            //获取父节点
            q = parent.get(q.val);
        }
        return null;
    }

    public void dfs(TreeNode root) {
        if (root.left != null) {
            //放入的是当前节点的值，和当前节点的父节点
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            //放入的是当前节点的值，和当前节点的父节点
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }


    private static TreeNode deepSearchNode(TreeNode root, TreeNode p) {
        Stack<TreeNode> result = new Stack<>();
        result.push(root);
        while (!result.isEmpty()) {
            TreeNode node = result.pop();
            if (node == p) {
                return node;
            }
            if (node.right != null) {
                result.push(node.right);

            }
            if (node.left != null) {
                result.push(node.left);
            }
        }
        return null;
    }


    private static void test1() {
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(6);
        TreeNode val_5 = new TreeNode(5);
        root.left.left = val_5;
        root.left.right = new TreeNode(7);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(9);
        TreeNode val_11 = new TreeNode(11);
        root.right.right = val_11;

        System.out.println(findNode(root, val_5));

        System.out.println(deepSearchNode(root, val_11));

    }

    private static TreeNode findNode(TreeNode current, TreeNode target) {
        if (current == target) {
            return current;
        }
        TreeNode result = null;
        if (current.left != null) {
            result = findNode(current.left, target);
        }
        if (result != null) {
            return result;
        }
        if (current.right != null) {
            result = findNode(current.right, target);
        }
        return result;
    }

}
