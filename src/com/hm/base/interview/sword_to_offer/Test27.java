package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/11
 * <p>
 * Desc:请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * <p>
 * 解题思路：我们先前序遍历这棵树的每个结点，如果遍历到的结点有子结点，就交换它的两个子结点。当交换完所有非叶子结点的左右子结点之后，
 * 就得到了树的镜像。
 * <p>
 * 测试用例：
 * 1.正常的二叉树
 * 2. 只有左节点的二叉树
 * 3. 只有右节点的二叉树
 * 4. null
 * <p>
 * 参考链接：https://blog.csdn.net/DERRANTCM/article/details/46678173
 */
public class Test27 {

    public static void main(String[] args) {
        //test0();
        //test1();
        //test2();
        //test3();

    }

    public static void mirror(BinaryTreeNode node) {
        if (node != null) {
            BinaryTreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;
            mirror(node.left);
            mirror(node.right);
        }
    }


    public TreeNode mirrorTree(TreeNode root) {
        swap(root);
        return root;
    }

    public void swap(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode left = root.left;
        root.left = root.right;
        root.right = left;
        swap(root.left);
        swap(root.right);
    }


    private static void test3() {
        BinaryTreeNode node = null;
        mirror(node);
        Utils.printTree(node);
    }

    private static void test2() {
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
        Utils.printTree(root3);
        System.out.println();
        mirror(root3);
        Utils.printTree(root3);
    }

    private static void test1() {
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
        Utils.printTree(root2);
        System.out.println();
        mirror(root2);
        Utils.printTree(root2);
    }

    private static void test0() {
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

        Utils.printTree(root);
        System.out.println();

        mirror(root);
        Utils.printTree(root);
    }


}
