package com.hm.base.interview.android;

import com.hm.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树前序遍历
 * 前序遍历（Preorder Traversal）是二叉树遍历的一种方式，
 * 属于**深度优先搜索（DFS）**的一种。它的访问顺序是：
 * <p>
 * 先访问根节点。
 * 再遍历左子树。
 * 最后遍历右子树。
 * 用一句话概括：根 -> 左 -> 右。
 */
public class PreorderTraversal {

    // 存储遍历结果
    private List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {

        // 构建示例树: 1 -> 2 -> 4, 5; 1 -> 3 -> 6

        /**                 1
         *              /     \
         *             2       3
         *            / \      \
         *           4   5      6
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        PreorderTraversal traversal = new PreorderTraversal();
        List<Integer> result = traversal.preorderTraversal(root);
        System.out.println(result); // 输出: [1, 2, 4, 5, 3, 6]

        System.out.println("========= 下面使用非递归遍历 =========");
        List<Integer> result2 = traversal.preorderTraversal2(root);
        System.out.println(result2);

    }


    // 前序遍历主函数
    public List<Integer> preorderTraversal(TreeNode root) {
        result.clear(); // 清空结果
        preorder(root); // 调用递归函数
        return result;
    }

    /**
     * 递归实现的前序遍历
     *
     * @param node
     */
    // 递归辅助函数
    private void preorder(TreeNode node) {
        if (node == null) {
            return; // 空节点，返回
        }
        result.add(node.val); // 访问根节点
        preorder(node.left);  // 遍历左子树
        preorder(node.right); // 遍历右子树
    }


    /**
     * 使用栈来实现二叉树的前序遍历
     *
     * @param root
     * @return
     */
    private List<Integer> preorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        // 使用栈模拟递归
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop(); // 弹出当前节点
            result.add(node.val);        // 访问根节点

            // 先压右子节点（后访问）
            if (node.right != null) {
                stack.push(node.right);
            }
            // 再压左子节点（先访问）
            if (node.left != null) {
                stack.push(node.left);
            }
        }
        return result;
    }

}
