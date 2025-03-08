package com.hm.base.interview.sword_to_offer;

import com.hm.structure.TreeNode;

/**
 * Created by dumingwei on 2018/11/20
 * <p>
 * Desc:输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如：前序遍历序列｛ 1, 2, 4, 7, 3, 5, 6, 8｝和
 * 中序遍历序列｛4, 7, 2, 1, 5, 3, 8，6}，
 * 重建出项目根目录下的{RebuildTree.png}所示的二叉树并输出它的头节点。
 * 参考：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树.md
 * 参考链接：{@link <a herf="https://blog.csdn.net/derrantcm/article/details/45457557 ">
 */
public class Test7 {

    public static void main(String[] args) {

        test1();
        System.out.println();
//        test2();
//        System.out.println();
//        test3();
//        System.out.println();
//        test4();
//        System.out.println();
//        test5();
//        System.out.println();
//        test6();
//        System.out.println();
//        test7();

    }

    // 普通二叉树
    //              1
    //           /     \
    //          2       3
    //         /       / \
    //        4       5   6
    //         \         /
    //          7       8
    private static void test1() {
        int[] preorder = {1, 2, 4, 7, 3, 5, 6, 8};
        int[] inorder = {4, 7, 2, 1, 5, 3, 8, 6};
        TreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 所有结点都没有右子结点
    //            1
    //           /
    //          2
    //         /
    //        3
    //       /
    //      4
    //     /
    //    5
    private static void test2() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {5, 4, 3, 2, 1};
        TreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 所有结点都没有左子结点
    //            1
    //             \
    //              2
    //               \
    //                3
    //                 \
    //                  4
    //                   \
    //                    5
    private static void test3() {
        int[] preorder = {1, 2, 3, 4, 5};
        int[] inorder = {1, 2, 3, 4, 5};
        TreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 树中只有一个结点
    private static void test4() {
        int[] preorder = {1};
        int[] inorder = {1};
        TreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 完全二叉树
    //              1
    //           /     \
    //          2       3
    //         / \     / \
    //        4   5   6   7
    private static void test5() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 5, 1, 6, 3, 7};
        TreeNode root = construct(preorder, inorder);
        printTree(root);
    }

    // 输入空指针
    private static void test6() {
        construct(null, null);
    }

    // 输入的两个序列不匹配
    private static void test7() {
        int[] preorder = {1, 2, 4, 5, 3, 6, 7};
        int[] inorder = {4, 2, 8, 1, 6, 3, 7};
        TreeNode root = construct(preorder, inorder);
        printTree(root);
    }


    /**
     * @param preorder 二叉树前序遍历的结果
     * @param inorder  二叉树中序遍历的结果
     * @return
     */
    public static TreeNode construct(int[] preorder, int[] inorder) {
        if (preorder == null || inorder == null
                || preorder.length != inorder.length
                || preorder.length < 1) {
            return null;

        }
        return construct(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);

    }

    /**
     * @param preOrder 要重建的树前序遍历的数组
     * @param preStart   要重建的树的第一个节点在前序遍历数组中的位置
     * @param preEnd     要重建的树的最后一个节点在前序遍历数组中的位置
     * @param inOrder 要重建的树中序遍历的数组
     * @param inStart   要重建的树的第一个节点在中序遍历数组中的位置
     * @param inEnd     要重建的树的最后一个节点在中序遍历数组中的位置
     * @return 树的根节点
     */
    public static TreeNode construct(int[] preOrder, int preStart, int preEnd, int[] inOrder, int inStart, int inEnd) {
        //开始位置大于结束位置，说明已经没有需要处理的元素了。
        if (preStart > preEnd) {
            return null;
        }
        //取前序遍历的第一个数字，就是当前的根节点
        int rootVal = preOrder[preStart];

        TreeNode root = new TreeNode(rootVal);

        int rootIndex = inStart;
        //在中序遍历中找根节点的位置
        while (rootIndex <= inEnd && inOrder[rootIndex] != rootVal) {
            rootIndex++;
        }
        //如果在整个中序遍历的数组中没有找到，说明说入的参数是不合法的，抛出异常
        if (rootIndex > inEnd) {
            throw new IllegalArgumentException("Invalid input");
        }

        // 计算左子树节点数
        int leftTreeSize = rootIndex - inStart;

        /**
         * 递归构建当前根节点的左子树，左子树的元素个数：rootIndex-mStart个
         * 左子树对应的前序遍历的位置在[preStart+1, preStart+rootIndex-inStart]
         * 左子树对应的中序遍历的位置在[inStart, rootIndex-1]
         */
        root.left = construct(preOrder, preStart + 1, preStart + leftTreeSize, inOrder, inStart, rootIndex - 1);
        /**
         * 递归构建当前根结点的右子树，右子树的元素个数：inEnd-index个
         * 右子树对应的前序遍历的位置在[preStart+rootIndex-inStart+1, preEnd]
         * 右子树对应的中序遍历的位置在[rootIndex+1, inEnd]
         */

        root.right = construct(preOrder, preStart + leftTreeSize + 1, preEnd, inOrder, rootIndex + 1, inEnd);
        return root;
    }

    // 前序遍历二叉树
    public static void printTree(TreeNode root) {
        if (root != null) {
            System.out.print(root.val + " ");
            printTree(root.left);
            printTree(root.right);
        }

    }


}
