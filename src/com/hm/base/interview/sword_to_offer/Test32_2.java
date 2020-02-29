package com.hm.base.interview.sword_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dmw on 2018/12/16.
 * Desc:分行从上到下打印二叉树，分层打印二叉树
 * 从上往下打印出二叉树的每个节点，同一层的节点按照从左向右的顺序打印。每一层打印一行。
 * 参考根目录下的test32_2.png
 * <p>
 * 解题思路：这道题实质是考查树的遍历算法。从上到下打印二叉树的规律：每一次打印一个节点的时候，如果该节点有子节点，
 * 则把该节点的子节点放到一个队列的末尾。接下来到队列的头部取出最早进入队列的节点，重复前面的打印操作，直至队列中所有
 * 的节点都被打印出来为止。为了把二叉树的每一行单独打印到一行里，我们需要两个变量，一个变量表示在当前层中还没有打印的节点数；
 * 另一个变量表示下一层节点的数目。
 * <p>
 * 测试用例
 * 完全二叉树
 * 除了叶节点外，所有节点只有左节点
 * 除了叶节点外，所有节点只有右节点
 * 根节点为null
 * 只有一个节点的二叉树
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46705719
 */
public class Test32_2 {

    public static void main(String[] args) {
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        BinaryTreeNode root = new BinaryTreeNode();
        root.value = 8;
        root.left = new BinaryTreeNode();
        root.left.value = 6;
        root.left.left = new BinaryTreeNode();
        root.left.left.value = 5;
        root.left.right = new BinaryTreeNode();
        root.left.right.value = 7;
        root.right = new BinaryTreeNode();
        root.right.value = 10;
        root.right.left = new BinaryTreeNode();
        root.right.left.value = 9;
        root.right.right = new BinaryTreeNode();
        root.right.right.value = 11;
        printFromTopToBottomEveryLine(root);

        //         1
        //        /
        //       3
        //      /
        //     5
        //    /
        //   7
        //  /
        // 9
        BinaryTreeNode root2 = new BinaryTreeNode();
        root2.value = 1;
        root2.left = new BinaryTreeNode();
        root2.left.value = 3;
        root2.left.left = new BinaryTreeNode();
        root2.left.left.value = 5;
        root2.left.left.left = new BinaryTreeNode();
        root2.left.left.left.value = 7;
        root2.left.left.left.left = new BinaryTreeNode();
        root2.left.left.left.left.value = 9;
        System.out.println("\n");
        printFromTopToBottomEveryLine(root2);

        // 0
        //  \
        //   2
        //    \
        //     4
        //      \
        //       6
        //        \
        //         8
        BinaryTreeNode root3 = new BinaryTreeNode();
        root3.value = 0;
        root3.right = new BinaryTreeNode();
        root3.right.value = 2;
        root3.right.right = new BinaryTreeNode();
        root3.right.right.value = 4;
        root3.right.right.right = new BinaryTreeNode();
        root3.right.right.right.value = 6;
        root3.right.right.right.right = new BinaryTreeNode();
        root3.right.right.right.right.value = 8;
        System.out.println("\n");
        printFromTopToBottomEveryLine(root3);

        // 1
        BinaryTreeNode root4 = new BinaryTreeNode();
        root4.value = 1;
        System.out.println("\n");
        printFromTopToBottomEveryLine(root4);

        // null
        System.out.println("\n");
        printFromTopToBottomEveryLine(null);
    }

    /**
     * 从上往下打印出二叉树的每个节点，同一层的节点按照从左向右的顺序打印。每一层打印一行。
     *
     * @param root
     */
    private static void printFromTopToBottomEveryLine(BinaryTreeNode root) {
        if (root != null) {
            Queue<BinaryTreeNode> list = new LinkedList<>();
            list.add(root);
            // 用于记录当前处理的节点
            BinaryTreeNode curNode;
            //表示当前行还没有打印的节点数
            int toBePrinted = 1;
            //下一层节点的数目
            int nextLineNodeCount = 0;
            while (!list.isEmpty()) {
                curNode = list.remove();
                System.out.print(curNode.value + " ");
                if (curNode.left != null) {
                    list.add(curNode.left);
                    nextLineNodeCount++;
                }
                if (curNode.right != null) {
                    list.add(curNode.right);
                    nextLineNodeCount++;
                }
                toBePrinted--;
                if (toBePrinted == 0) {
                    System.out.println();
                    toBePrinted = nextLineNodeCount;
                    nextLineNodeCount = 0;
                }
            }
        }
    }


}
