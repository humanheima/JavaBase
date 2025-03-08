package com.hm.base.interview.sword_to_offer;

import com.hm.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2018/12/18
 * <p>
 * Desc:输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 * <p>
 * 解题思路：由于路径是从根结点出发到叶结点， 也就是说路径总是以根结点为起始点，因此我们首先需要遍历根结点。
 * 在树的前序、中序、后序三种遍历方式中，只有前序遍历是首先访问根结点的。
 * <p>
 * 当用前序遍历的方式访问到某一结点时， 我们把该结点添加到路径上，并累加该结点的值。如果该结点为叶结点并且路径中结点值的和刚好等于输入的整数，
 * 则当前的路径符合要求，我们把它打印出来。如果当前结点不是叶结点，则继续访问它的子结点。当前结点访问结束后，递归函数将自动回到它的父结点。
 * 因此我们在函数退出之前要在路径上删除当前结点并减去当前结点的值，以确保返回父结点时路径刚好是从根结点到父结点的路径。
 * <p>
 * 不难看出保存路径的数据结构实际上是一个栈，因为路径要与递归调用状态一致， 而递归调用的本质就是一个压栈和出栈的过程。
 * <p>
 * 测试用例
 * <p>
 * 二叉树中有一条或者多条符合要求的路径
 * 二叉树中没有符合要求的路径
 * 输入null
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46705853
 */
public class Test34 {

    public static void main(String[] args) {
        test0();
        System.out.println("-----------------");
        test1();
        System.out.println("-----------------");

        test2();
        System.out.println("-----------------");

        test3();
        System.out.println("-----------------");

        test4();


    }


    private static void test0() {
        //            10
        //         /      \
        //        5        12
        //       /\
        //      4  7
        TreeNode root = new TreeNode();
        root.val = 10;
        root.left = new TreeNode();
        root.left.val = 5;
        root.left.left = new TreeNode();
        root.left.left.val = 4;
        root.left.right = new TreeNode();
        root.left.right.val = 7;
        root.right = new TreeNode();
        root.right.val = 12;

        // 有两条路径上的结点和为22
        System.out.println("findPath(root, 22);");
        findPath(root, 22);
        System.out.println(new Test34().pathSum(root, 22));

    }

    private static void test1() {
        //            10
        //         /      \
        //        5        12
        //       /\
        //      4  7
        TreeNode root = new TreeNode();
        root.val = 10;
        root.left = new TreeNode();
        root.left.val = 5;
        root.left.left = new TreeNode();
        root.left.left.val = 4;
        root.left.right = new TreeNode();
        root.left.right.val = 7;
        root.right = new TreeNode();
        root.right.val = 12;

        // 有两条路径上的结点和为10 这个是不行的，因为10不是叶子节点
        System.out.println("findPath(root, 10);");
        findPath(root, 10);

        System.out.println(new Test34().pathSum(root, 10));

    }

    private static void test2() {
        //            10
        //         /      \
        //        5        12
        //       /\
        //      4  7
        TreeNode root = new TreeNode();
        root.val = 10;
        root.left = new TreeNode();
        root.left.val = 5;
        root.left.left = new TreeNode();
        root.left.left.val = 4;
        root.left.right = new TreeNode();
        root.left.right.val = 7;
        root.right = new TreeNode();
        root.right.val = 12;

        System.out.println("findPath(root, 19);");
        findPath(root, 19);

        System.out.println(new Test34().pathSum(root, 19));

    }

    private static void test3() {
        //            10

        TreeNode root = new TreeNode();
        root.val = 10;

        System.out.println("findPath(root, 10);");
        findPath(root, 10);

        System.out.println(new Test34().pathSum(root, 10));

    }

    private static void test4() {
        //            -10
        //         /      \
        //        5        12
        //       /\
        //      0  7
        TreeNode root = new TreeNode();
        root.val = -10;
        root.left = new TreeNode();
        root.left.val = 5;
        root.left.left = new TreeNode();
        root.left.left.val = 0;
        root.left.right = new TreeNode();
        root.left.right.val = 7;
        root.right = new TreeNode();
        root.right.val = 12;

        System.out.println("findPath(root, -5);");
        findPath(root, -5);
        //System.out.println("findPath2(root, -5);");
        //findPath2(root, -5);
        Test34 test34 = new Test34();
        List<List<Integer>> result = test34.pathSum(root, -5);
        System.out.println(result);
    }


    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        findPath3(root, 0, target, new ArrayList<>(), result);
        return result;
    }

    public void findPath3(TreeNode root, int curSum, int target, List<Integer> path, List<List<Integer>> result) {
        curSum += root.val;
        path.add(root.val);
        /**
         * 如果是叶节点，并且路径上节点值的和等于输入的值
         */
        boolean isLeaf = (root.left == null) && (root.right == null);
        if (curSum == target && isLeaf) {
            List<Integer> temp = new ArrayList<>(path);
            result.add(temp);
        }

        //如果不是叶节点
        if (root.left != null) {
            findPath3(root.left, curSum, target, path, result);
        }
        if (root.right != null) {
            findPath3(root.right, curSum, target, path, result);
        }
        //返回父节点之前，在路径上删除当前节点
        path.remove(path.size() - 1);
    }


    /**
     * @param root
     * @param expectedNumber 期望的整数值
     */
    public static void findPath(TreeNode root, int expectedNumber) {
        if (root == null) {
            return;
        }
        List<Integer> list = new ArrayList<>();
        findPath(root, 0, expectedNumber, list);

    }

    private static void findPath(TreeNode root, int curSum, int expectedNumber, List<Integer> result) {
        curSum += root.val;
        result.add(root.val);
        /**
         * 如果是叶节点，并且路径上节点值的和等于输入的值
         */
        boolean isLeaf = (root.left == null) && (root.right == null);
        if (curSum == expectedNumber && isLeaf) {
            System.out.print("A path found:");
            for (Integer integer : result) {
                System.out.print(" " + integer);
            }
            System.out.println();
        }

        //如果不是叶节点
        if (root.left != null) {
            findPath(root.left, curSum, expectedNumber, result);
        }
        if (root.right != null) {
            findPath(root.right, curSum, expectedNumber, result);
        }
        //返回父节点之前，在路径上删除当前节点
        result.remove(result.size() - 1);
    }


    /**
     * 作者：derrantcm
     * 来源：CSDN
     * 原文：https://blog.csdn.net/derrantcm/article/details/46705853
     * 版权声明：本文为博主原创文章，转载请附上博文链接！
     * <p>
     * 输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。
     * 从树的根结点开始往下一直到叶销点所经过的结点形成一条路径。
     *
     * @param root        树的根结点
     * @param expectedSum 要求的路径和
     */
    public static void findPath2(TreeNode root, int expectedSum) {
        // 创建一个链表，用于存放根结点到当前处理结点的所经过的结点
        List<Integer> list = new ArrayList<>();

        // 如果根结点不为空，就调用辅助处理方法
        if (root != null) {
            findPath2(root, 0, expectedSum, list);
        }
    }

    /**
     * @param root        当前要处理的结点
     * @param curSum      当前记录的和（还未加上当前结点的值）
     * @param expectedSum 要求的路径和
     * @param result      根结点到当前处理结点的所经过的结点，（还未包括当前结点）
     */
    public static void findPath2(TreeNode root, int curSum, int expectedSum, List<Integer> result) {

        // 如果结点不为空就进行处理
        if (root != null) {
            // 加上当前结点的值
            curSum += root.val;
            // 将当前结点入队
            result.add(root.val);
            // 如果当前结点的值小于期望的和
            if (curSum < expectedSum) {
                // 递归处理左子树
                findPath2(root.left, curSum, expectedSum, result);
                // 递归处理右子树
                findPath2(root.right, curSum, expectedSum, result);
            } else if (curSum == expectedSum) {
                //如果当前和与期望的和相等，当前结点是叶结点，则输出结果
                if (root.left == null && root.right == null) {
                    System.out.println(result);
                }
            }
            // 移除当前结点
            result.remove(result.size() - 1);
        }
    }


}
