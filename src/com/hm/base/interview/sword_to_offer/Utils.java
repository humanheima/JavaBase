package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/11
 * <p>
 * Desc:
 */
public class Utils {

    public static void printTree(BinaryTreeNode node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(node.value + " ");
            printTree(node.right);
        }
    }
}
