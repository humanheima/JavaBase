package com.hm.base.interview.sword_to_offer;

/**
 * Created by dmw on 2018/12/16.
 * Desc:二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则返回true。否则返回false。假设输入的数组的任意两个数字都互不相同。
 * 参看根目录下的 test32.png。如果输入的数组是{5,7,6,9,11,10,8} ，则该数组是test32.png所示二叉搜索树的后续遍历结果。
 * <p>
 * 二叉搜索树的定义：
 * 二叉查找树（英语：Binary Search Tree），是指一棵空树或者具有下列性质的二叉树
 * 1. 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 2. 若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 3. 任意节点的左、右子树也分别为二叉查找树；
 * 4. 没有键值相等的节点。
 * <p>
 * 解题思路：在后序遍历得到的序列中， 最后一个数字是树的根结点的值。数组中前面的数字可以分为两部分： 第一部分是左子树结点的值，它们都比根结点的值小;
 * 第二部分是右子树结点的值，它们都比根结点的值大。
 * <p>
 * 测试用例：
 * 输入的后序遍历对应一棵 二叉树，包括完全二叉树，所有节点都没有左/右子树的二叉树，只有一个节点的二叉树
 * 输入null
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46705725
 */
public class Test33 {

    public static boolean verifySequenceOfBST(int[] sequence) {
        if (sequence == null || sequence.length == 0) {
            return false;
        }
        return verifySequenceOfBST(sequence, 0, sequence.length - 1);
    }

    private static boolean verifySequenceOfBST(int[] sequence, int start, int end) {
        if (start >= end) {
            return true;
        }
        int index = start;
        //找到第一个大于根节点（sequence[end]）的元素的位置
        while (index < end && sequence[index] < sequence[end]) {
            index++;
        }
        //说明这棵树只有左子节点
        if (index == end) {
            return true;
        }

        int right = index;
        //从[right,end-1]所有的元素必须大于根元素
        while (index < end && sequence[index] > sequence[end]) {
            index++;
        }
        if (index != end) {
            return false;
        }
        index = right;
        return verifySequenceOfBST(sequence, start, index - 1)
                && verifySequenceOfBST(sequence, index, end - 1);
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4();
        test5();
        test6();
        test7();
        test8();
    }

    private static void test1() {
        //           10
        //         /   \
        //        6     14
        //       /\     /\
        //      4  8  12  16
        int[] data = {4, 8, 6, 12, 16, 14, 10};
        System.out.println("true: " + verifySequenceOfBST(data));
    }

    private static void test2() {
        //           5
        //          / \
        //         4   7
        //            /
        //           6
        int[] data2 = {4, 6, 7, 5};
        System.out.println("true: " + verifySequenceOfBST(data2));
    }

    private static void test3() {
        //               5
        //              /
        //             4
        //            /
        //           3
        //          /
        //         2
        //        /
        //       1
        int[] data3 = {1, 2, 3, 4, 5};
        System.out.println("true: " + verifySequenceOfBST(data3));
    }

    private static void test4() {
        // 1
        //  \
        //   2
        //    \
        //     3
        //      \
        //       4
        //        \
        //         5
        int[] data4 = {5, 4, 3, 2, 1};
        System.out.println("true: " + verifySequenceOfBST(data4));
    }

    private static void test5() {
        // 树中只有1个结点
        int[] data5 = {5};
        System.out.println("true: " + verifySequenceOfBST(data5));
    }

    private static void test6() {
        int[] data6 = {7, 4, 6, 5};
        System.out.println("false: " + verifySequenceOfBST(data6));
    }

    private static void test7() {
        int[] data7 = {4, 6, 12, 8, 16, 14, 10};
        System.out.println("false: " + verifySequenceOfBST(data7));
    }

    private static void test8() {
        int[] data8 = {4, 8, 6, 12, 16, 1, 10};
        System.out.println("false: " + verifySequenceOfBST(data8));
    }

}
