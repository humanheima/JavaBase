package com.hm.leetcode;

import com.hm.structure.TreeNode;

import java.util.*;

/**
 * 二叉树后序遍历 (LeetCode 145)
 * 后序遍历（Postorder Traversal）是二叉树遍历的一种方式，
 * 属于**深度优先搜索（DFS）**的一种。它的访问顺序是：
 * <p>
 * 先遍历左子树。
 * 再遍历右子树。
 * 最后访问根节点。
 * 用一句话概括：左 -> 右 -> 根。
 * <p>
 * 典型应用：计算目录大小、表达式求值（后缀表达式）、删除树节点等需要先处理子节点再处理父节点的场景。
 */
public class LeetCode145 {

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

        LeetCode145 traversal = new LeetCode145();
        List<Integer> result = traversal.postorderTraversal(root);
        System.out.println(result); // 输出: [4, 5, 2, 6, 3, 1]

        System.out.println("========= 下面使用非递归遍历 =========");
        List<Integer> result2 = traversal.postorderTraversal2(root);
        System.out.println(result2);

        System.out.println(traversal.postorderTraversal3(root));

    }


    // 后序遍历主函数（递归入口）
    public List<Integer> postorderTraversal(TreeNode root) {
        result.clear(); // 清空结果
        postorder(root); // 调用递归函数
        return result;
    }

    /**
     * 递归实现的后序遍历
     * 访问顺序：左子树 -> 右子树 -> 根节点
     *
     * @param node 当前节点
     */
    private void postorder(TreeNode node) {
        if (node == null) {
            return; // 空节点，返回
        }
        postorder(node.left);  // 遍历左子树
        postorder(node.right); // 遍历右子树
        result.add(node.val);  // 最后访问根节点
    }


    /**
     * 迭代实现后序遍历（双栈法，思路最清晰）
     *
     * 原理：
     * 1. 使用 stack1 进行类似前序的遍历（但先左后右），每次弹出节点后压入 stack2。
     * 2. 最后从 stack2 依次弹出即为 左->右->根 的后序顺序。
     *
     * @param root
     * @return 后序遍历结果
     */
    public List<Integer> postorderTraversal2(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();

        stack1.push(root);

        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);

            // 注意：先压左，再压右（与前序相反），这样 stack2 弹出时就是 左 右 根
            if (node.left != null) {
                stack1.push(node.left);
            }
            if (node.right != null) {
                stack1.push(node.right);
            }
        }

        // stack2 栈顶到栈底是 根 右 左，反向弹出即 左 右 根
        while (!stack2.isEmpty()) {
            result.add(stack2.pop().val);
        }

        return result;
    }

    /**
     * 迭代实现后序遍历（单栈 + lastVisited 标记，空间更优）
     *
     * 核心思路：
     * - 一直往左走到底并压栈。
     * - 查看栈顶节点：
     *   - 如果右子树为空，或者右子树已经被访问过（== lastVisited），则可以访问当前节点（弹出并记录）。
     *   - 否则转向右子树继续处理。
     * - 使用 lastVisited 防止右子树被重复处理。
     *
     * @param root
     * @return 后序遍历结果
     */
    public List<Integer> postorderTraversal3(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;
        TreeNode lastVisited = null;   // 记录上一次访问（弹出）的节点

        while (curr != null || !stack.isEmpty()) {
            // 1. 一直向左走到底
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // 2. 查看栈顶
            TreeNode peekNode = stack.peek();

            // 3. 如果没有右子树，或右子树已经处理完（上次访问的就是它的右子树），则访问根
            if (peekNode.right == null || peekNode.right == lastVisited) {
                TreeNode node = stack.pop();
                result.add(node.val);
                lastVisited = node;   // 更新上次访问
                curr = null;          // 防止重复向左
            } else {
                // 4. 否则先去处理右子树
                curr = peekNode.right;
            }
        }

        return result;
    }
}
