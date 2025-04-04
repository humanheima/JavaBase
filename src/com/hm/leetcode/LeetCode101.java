package com.hm.leetcode;

import com.hm.structure.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dumingwei on 2018/12/12
 * <p>
 * Desc:请实现一个函数来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 * <p>
 * 解题思路：
 * 通常我们有三种不同的二叉树遍历算法，
 * 即前序遍历（父节点，left，right）
 * 中序遍历（left，父节点，right）
 * 后序遍历（left，right,父节点）
 * 在这三种遍历算法中，都是先遍历左子节点再遍历右子节点。
 * <p>
 * 我们是否可以定义一种遍历算法，先遍历右子节点再遍历左子节点？
 * 比如我们针对前序遍历定义一种对称的遍历算法，即先遍历父节点，再遍历它的右子节点，最后遍历它的左子节点。
 * 我们发现可以比较二叉树的前序遍历序列和对称前序遍历序列来判断二叉树是不是对称的。如果两个序列一样，那么二叉树就是对称的。
 * <p>
 * <p>
 * 测试用例：
 * 1 对称的二叉树
 * 2 结构不对称的二叉树
 * 3 结构对称但节点值不对称的二叉树
 * 4 根节点为null
 * 5 只有一个节点的二叉树
 * 6 所有值都相等的二叉树
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46847939
 */
public class LeetCode101 {

    public static void main(String[] args) {

        //test01();
        //test02();
        //test03();
        test04();
        test05();
        test06();
        test07();
    }

    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isMirror(root.left, root.right);
    }

    private static boolean isMirror(TreeNode left, TreeNode right) {
        // 两个节点都为空，对称
        if (left == null && right == null) return true;
        // 一个为空，另一个不为空，不对称
        if (left == null || right == null) return false;

        // 检查值是否相等，且子树是否镜像
        return left.val == right.val
                && isMirror(left.left, right.right)  // TODO 这里注意，左的左 vs 右的右
                && isMirror(left.right, right.left); // TODO 这里注意，左的右 vs 右的左
    }

    /**
     * 使用迭代
     *
     * @param root
     * @return
     */
    public boolean isSymmetricIterative(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root.left);
        queue.offer(root.right);

        while (!queue.isEmpty()) {
            TreeNode left = queue.poll();
            TreeNode right = queue.poll();

            // 两个节点都为空，继续
            if (left == null && right == null) continue;
            // 一个为空，另一个不为空，不对称
            if (left == null || right == null) return false;
            // 值不相等，不对称
            if (left.val != right.val) return false;

            queue.offer(left.left);
            queue.offer(right.right);
            queue.offer(left.right);
            queue.offer(right.left);
        }

        return true;
    }

    //结构对称
    //                            8
    //
    //                       6         6
    //
    //                    5     7   7    5

    public static void test01() {
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(5);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);
        assemble(n7, null, null);

        System.out.println(isSymmetric(n1));

    }


    // 结构不对称
    //                            8
    //
    //                       6         6
    //
    //                    5     7   7

    public static void test02() {
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(7);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, null);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);

        System.out.println(isSymmetric(n1));

    }

    //结构对称,但是节点不对称
    //                            8
    //
    //                       6         6
    //
    //                    5     7   7    10

    public static void test03() {
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(6);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(5);
        TreeNode n5 = new TreeNode(7);
        TreeNode n6 = new TreeNode(7);
        TreeNode n7 = new TreeNode(10);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);
        assemble(n7, null, null);

        System.out.println(isSymmetric(n1));

    }

    //节点为null
    public static void test04() {

        System.out.println(isSymmetric(null));

    }

    //只有一个节点
    public static void test05() {
        TreeNode n1 = new TreeNode(1);
        assemble(n1, null, null);
        System.out.println(isSymmetric(n1));

    }

    //结构对称,所有节点都相等的二叉树
    //                            8
    //
    //                       8         8
    //
    //                    8     8   8    8

    public static void test06() {
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(8);
        TreeNode n3 = new TreeNode(8);
        TreeNode n4 = new TreeNode(8);
        TreeNode n5 = new TreeNode(8);
        TreeNode n6 = new TreeNode(8);
        TreeNode n7 = new TreeNode(8);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);
        assemble(n7, null, null);

        System.out.println(isSymmetric(n1));

    }

    //结构不对称,所有节点都相等的二叉树
    //                            8
    //
    //                       8         8
    //
    //                    8     8   8

    public static void test07() {
        TreeNode n1 = new TreeNode(8);
        TreeNode n2 = new TreeNode(8);
        TreeNode n3 = new TreeNode(8);
        TreeNode n4 = new TreeNode(8);
        TreeNode n5 = new TreeNode(8);
        TreeNode n6 = new TreeNode(8);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, null);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);

        System.out.println(isSymmetric(n1));

    }

    private static void assemble(TreeNode node, TreeNode left, TreeNode right) {
        node.left = left;
        node.right = right;
    }


}
