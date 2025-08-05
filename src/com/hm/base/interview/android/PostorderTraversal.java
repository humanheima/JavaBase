package com.hm.base.interview.android;

import com.hm.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 二叉树后序遍历.md
 */
public class PostorderTraversal {

    private List<Integer> result = new ArrayList<>();

    public static void main(String[] args) {
        // 构建测试树：    1
        //              / \
        //             2   3
        //            / \
        //           4   5
        //            \
        //             6
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        root.left.left.right = new TreeNode(6);


        PostorderTraversal solution = new PostorderTraversal();
        System.out.println("后序遍历结果：");

        //递归算法
        //solution.postorderTraversal(root);  // 输出: 6 4 5 2 3 1
        //System.out.println(solution.result);


        solution.result.clear();
        //使用栈实现
        solution.postorderTraversal1(root);  // 输出: 6 4 5 2 3 1
        System.out.println(solution.result);
    }


    public void postorderTraversal(TreeNode root) {
        result.clear();
        postorder(root);
    }

    public void postorder(TreeNode root) {
        if (root == null) {
            return;
        }
        // 先遍历左子树
        postorder(root.left);
        // 再遍历右子树
        postorder(root.right);
        // 最后访问根节点
        result.add(root.val);
    }

    /**
     * **原理分析：**
     * 1. 用栈保存未访问的节点，从根节点开始，将所有左子节点入栈。
     * 2. 检查栈顶节点：
     * - 如果右子树为空或已访问，则访问当前节点并出栈。
     * - 否则，转向右子树，继续入栈左子节点。
     * 3. 使用 `lastVisited` 记录上一个访问的节点，避免重复访问。
     * 4. 循环直到栈为空且当前节点为空。
     *
     * @param root
     */
    public void postorderTraversal1(TreeNode root) {
        if (root == null) {
            return;
        }

        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;  // 当前节点
        TreeNode lastVisited = null;  // 上一个访问的节点

        while (curr != null || !stack.isEmpty()) {
            // 先将所有左子节点入栈
            while (curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            // TODO 查看栈顶节点，注意 peek() 不会出栈
            TreeNode peekNode = stack.peek();

            // 如果右子树已访问过或不存在，则访问当前节点
            /**
             * 比如 4 的右节点是 6，访问过了以后，下次就不访问了。不然会一直访问。
             */
            if (peekNode.right == null || peekNode.right == lastVisited) {
                //System.out.print(peekNode.val + " ");
                result.add(peekNode.val);
                lastVisited = stack.pop();
            }
            // 否则，转向右子树
            else {
                curr = peekNode.right;
            }
        }
    }

}
