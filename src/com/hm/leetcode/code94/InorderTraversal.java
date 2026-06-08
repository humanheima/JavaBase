package com.hm.leetcode.code94;

import com.hm.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树的中序遍历.md
 * 中序遍历的核心是按照“左-根-右”的顺序递归或迭代地访问每个节点：
 */
public class InorderTraversal {
    // 存储遍历结果
    private final List<Integer> result = new ArrayList<>();

    // 测试

    /**
     *                  1
     *                 / \
     *               2   3
     *              / \   \
     *             4   5   6
     * @param args
     */
    public static void main(String[] args) {
        // 构建示例树: 1 -> 2 -> 4, 5; 1 -> 3 -> 6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        InorderTraversal traversal = new InorderTraversal();
        List<Integer> result = traversal.inorderTraversal(root);
        System.out.println(result); // 输出: [4, 2, 5, 1, 3, 6]

        System.out.println("========= 下面使用非递归遍历 =========");
        List<Integer> result2 = traversal.inorderTraversal2(root);
        System.out.println(result2);

        System.out.println("========= 下面使用 Morris 遍历（O(1) 空间）=========");
        List<Integer> result3 = traversal.inorderTraversal3(root);
        System.out.println(result3);
    }


    // 中序遍历主函数
    public List<Integer> inorderTraversal(TreeNode root) {
        result.clear(); // 清空结果
        inorder(root);  // 调用递归函数
        return result;
    }

    /**
     * 递归实现中序遍历
     *
     * @param node
     */
    // 递归辅助函数
    private void inorder(TreeNode node) {
        if (node == null) {
            return; // 空节点，返回
        }
        inorder(node.left);   // 遍历左子树
        result.add(node.val); // 访问根节点
        inorder(node.right);  // 遍历右子树
    }


    /**
     * 使用栈实现中序遍历
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        //根节点为null，直接返回
        if (root == null) {
            return result;
        }

        // 使用栈模拟递归
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;

        while (current != null || !stack.isEmpty()) {
            // 将所有左子节点入栈
            while (current != null) {
                stack.push(current);
                current = current.left;
            }

            // 弹出栈顶节点
            current = stack.pop();
            result.add(current.val); // 访问根节点

            // 处理右子树
            current = current.right;
        }

        return result;
    }


    /**
     * Morris 中序遍历，空间复杂度 O(1)
     * 核心：利用叶子节点的空闲 right 指针建立指向后继的“线索”，
     * 遍历完左子树后顺着线索回到根，再拆除线索还原树结构。
     *
     * @param root
     * @return
     */
    public List<Integer> inorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                // 没有左子树：直接访问当前节点，再转向右子树
                result.add(current.val);
                current = current.right;
            } else {
                // 找到当前节点左子树中的最右节点（中序前驱）
                TreeNode predecessor = current.left;
                while (predecessor.right != null && predecessor.right != current) {
                    predecessor = predecessor.right;
                }

                if (predecessor.right == null) {
                    // 第一次到达：建立线索，指回 current，然后进入左子树
                    predecessor.right = current;
                    current = current.left;
                } else {
                    // 第二次到达：线索已存在，说明左子树遍历完毕
                    // 拆除线索，访问 current，再转向右子树
                    predecessor.right = null;
                    result.add(current.val);
                    current = current.right;
                }
            }
        }

        return result;
    }

}