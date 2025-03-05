package com.hm.base.interview.sword_to_offer;

import com.hm.structure.TreeNode;

import java.util.*;

/**
 * Created by dmw on 2018/12/16.
 * Desc:从上到下打印二叉树
 * 从上往下打印出二叉树的每个结点，同一层的结点按照从左向右的顺序打印。例如根目录下的test32.png，则依此打印出 8,6,10,5,7,9,11
 * <p>
 * 解题思路：这道题实质是考查树的遍历算法。从上到下打印二叉树的规律：每一次打印一个结点的时候，如果该结点有子结点， 则把该结点的子结点放到一个队列的末尾。
 * 接下来到队列的头部取出最早进入队列的结点，重复前面的打印操作，直至队列中所有的结点都被打印出来为止。
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
public class Test32 {


    /**
     * LeetCode 上的提交
     *
     * @param root
     * @return
     */
    //       8
    //    /    \
    //   6     10
    //  / \   / \
    // 5   7 9  11
    public int[] levelOrder(TreeNode root) {
        if (root == null) {
            int[] result = {};
            return result;
        }
        List<Integer> tempList = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            tempList.add(node.value);
            if (node.left != null) {
                queue.offer(node.left);
            }
            if (node.right != null) {
                queue.offer(node.right);
            }
        }
        int size = tempList.size();
        int[] result = new int[size];
        for (int i = 0; i < size; i++) {
            result[i] = tempList.get(i);
        }
        //System.out.println(tempList);
        return result;

    }

    private static void test1() {
        //       8
        //    /    \
        //   6     10
        //  / \   / \
        // 5   7 9  11
        TreeNode root = new TreeNode();
        root.value = 8;
        root.left = new TreeNode();
        root.left.value = 6;
        root.left.left = new TreeNode();
        root.left.left.value = 5;
        root.left.right = new TreeNode();
        root.left.right.value = 7;
        root.right = new TreeNode();
        root.right.value = 10;
        root.right.left = new TreeNode();
        root.right.left.value = 9;
        root.right.right = new TreeNode();
        root.right.right.value = 11;

        printFromTopToBottom(root);
        new Test32().levelOrder(root);
    }

    public static void main(String[] args) {
        test1();

        //         1
        //        /
        //       3
        //      /
        //     5
        //    /
        //   7
        //  /
        // 9
        TreeNode root2 = new TreeNode();
        root2.value = 1;
        root2.left = new TreeNode();
        root2.left.value = 3;
        root2.left.left = new TreeNode();
        root2.left.left.value = 5;
        root2.left.left.left = new TreeNode();
        root2.left.left.left.value = 7;
        root2.left.left.left.left = new TreeNode();
        root2.left.left.left.left.value = 9;
        System.out.println("\n");
        printFromTopToBottom(root2);

        // 0
        //  \
        //   2
        //    \
        //     4
        //      \
        //       6
        //        \
        //         8
        TreeNode root3 = new TreeNode();
        root3.value = 0;
        root3.right = new TreeNode();
        root3.right.value = 2;
        root3.right.right = new TreeNode();
        root3.right.right.value = 4;
        root3.right.right.right = new TreeNode();
        root3.right.right.right.value = 6;
        root3.right.right.right.right = new TreeNode();
        root3.right.right.right.right.value = 8;
        System.out.println("\n");
        printFromTopToBottom(root3);

        // 1
        TreeNode root4 = new TreeNode();
        root4.value = 1;
        System.out.println("\n");
        printFromTopToBottom(root4);

        // null
        System.out.println("\n");
        printFromTopToBottom(null);
    }

    /**
     * @param root
     */
    private static void printFromTopToBottom(TreeNode root) {
        if (root != null) {
            Queue<TreeNode> list = new LinkedList<>();
            list.add(root);

            TreeNode curNode;
            while (!list.isEmpty()) {
                curNode = list.remove();
                System.out.print(curNode.value + " ");
                if (curNode.left != null) {
                    list.add(curNode.left);
                }
                if (curNode.right != null) {
                    list.add(curNode.right);
                }
            }
        }
    }


}
