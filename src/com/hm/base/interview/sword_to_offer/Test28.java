package com.hm.base.interview.sword_to_offer;

import com.hm.structure.TreeNode;

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
public class Test28 {


    public boolean isSymmetric(TreeNode root) {
        return isSymmetrical2(root, root);
    }

    public boolean isSymmetrical2(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.value != right.value) {
            return false;
        }
        return isSymmetrical2(left.left, right.right) && isSymmetrical2(left.right, right.left);
    }


    public static boolean isSymmetrical(BinaryTreeNode root) {
        return isSymmetrical(root, root);
    }

    private static boolean isSymmetrical(BinaryTreeNode left, BinaryTreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.value != right.value) {
            return false;
        }
        return isSymmetrical(left.left, right.right) && isSymmetrical(left.right, right.left);
    }

    public static void main(String[] args) {

        //test01();
        //test02();
        //test03();
        test04();
        test05();
        test06();
        test07();

    }

    private static void assemble(BinaryTreeNode node, BinaryTreeNode left, BinaryTreeNode right) {
        node.left = left;
        node.right = right;
    }

    //结构对称
    //                            8
    //
    //                       6         6
    //
    //                    5     7   7    5

    public static void test01() {
        BinaryTreeNode n1 = new BinaryTreeNode(8);
        BinaryTreeNode n2 = new BinaryTreeNode(6);
        BinaryTreeNode n3 = new BinaryTreeNode(6);
        BinaryTreeNode n4 = new BinaryTreeNode(5);
        BinaryTreeNode n5 = new BinaryTreeNode(7);
        BinaryTreeNode n6 = new BinaryTreeNode(7);
        BinaryTreeNode n7 = new BinaryTreeNode(5);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);
        assemble(n7, null, null);

        System.out.println(isSymmetrical(n1));

    }


    // 结构不对称
    //                            8
    //
    //                       6         6
    //
    //                    5     7   7

    public static void test02() {
        BinaryTreeNode n1 = new BinaryTreeNode(8);
        BinaryTreeNode n2 = new BinaryTreeNode(6);
        BinaryTreeNode n3 = new BinaryTreeNode(6);
        BinaryTreeNode n4 = new BinaryTreeNode(5);
        BinaryTreeNode n5 = new BinaryTreeNode(7);
        BinaryTreeNode n6 = new BinaryTreeNode(7);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, null);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);

        System.out.println(isSymmetrical(n1));

    }

    //结构对称,但是节点不对称
    //                            8
    //
    //                       6         6
    //
    //                    5     7   7    10

    public static void test03() {
        BinaryTreeNode n1 = new BinaryTreeNode(8);
        BinaryTreeNode n2 = new BinaryTreeNode(6);
        BinaryTreeNode n3 = new BinaryTreeNode(6);
        BinaryTreeNode n4 = new BinaryTreeNode(5);
        BinaryTreeNode n5 = new BinaryTreeNode(7);
        BinaryTreeNode n6 = new BinaryTreeNode(7);
        BinaryTreeNode n7 = new BinaryTreeNode(10);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);
        assemble(n7, null, null);

        System.out.println(isSymmetrical(n1));

    }

    //节点为null
    public static void test04() {

        System.out.println(isSymmetrical(null));

    }

    //只有一个节点
    public static void test05() {
        BinaryTreeNode n1 = new BinaryTreeNode(1);
        assemble(n1, null, null);
        System.out.println(isSymmetrical(n1));

    }

    //结构对称,所有节点都相等的二叉树
    //                            8
    //
    //                       8         8
    //
    //                    8     8   8    8

    public static void test06() {
        BinaryTreeNode n1 = new BinaryTreeNode(8);
        BinaryTreeNode n2 = new BinaryTreeNode(8);
        BinaryTreeNode n3 = new BinaryTreeNode(8);
        BinaryTreeNode n4 = new BinaryTreeNode(8);
        BinaryTreeNode n5 = new BinaryTreeNode(8);
        BinaryTreeNode n6 = new BinaryTreeNode(8);
        BinaryTreeNode n7 = new BinaryTreeNode(8);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, n7);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);
        assemble(n7, null, null);

        System.out.println(isSymmetrical(n1));

    }

    //结构不对称,所有节点都相等的二叉树
    //                            8
    //
    //                       8         8
    //
    //                    8     8   8

    public static void test07() {
        BinaryTreeNode n1 = new BinaryTreeNode(8);
        BinaryTreeNode n2 = new BinaryTreeNode(8);
        BinaryTreeNode n3 = new BinaryTreeNode(8);
        BinaryTreeNode n4 = new BinaryTreeNode(8);
        BinaryTreeNode n5 = new BinaryTreeNode(8);
        BinaryTreeNode n6 = new BinaryTreeNode(8);

        assemble(n1, n2, n3);
        assemble(n2, n4, n5);
        assemble(n3, n6, null);
        assemble(n4, null, null);
        assemble(n5, null, null);
        assemble(n6, null, null);

        System.out.println(isSymmetrical(n1));

    }


}
