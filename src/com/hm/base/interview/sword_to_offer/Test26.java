package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2018/12/11
 * <p>
 * Desc:输入两棵二叉树A 和B，判断B 是不是A 的子结构。
 * <p>
 * 解题思路：
 * 要查找树A 中是否存在和树B 结构一样的子树，我们可以分成两步： 第一步在树A 中找到和B 的根结点的值一样的结点R，
 * 第二步再判断树A 中以R 为根结点的子树是不是包含和树B 一样的结构。
 * <p>
 * 树的结构参看根目录下的 test26.png
 * <p>
 * 参考链接：https://blog.csdn.net/DERRANTCM/article/details/46678157
 */
public class Test26 {


    /**
     * 递归实现的先序遍历
     *
     * @param root
     */
    public static void recurseFront(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + " ");
        recurseFront(root.left);
        recurseFront(root.right);
    }

    public static void main(String[] args) {
        test1();
    }

    private static void test1() {
        BinaryTreeNode root1 = new BinaryTreeNode();
        root1.value = 8;

        root1.right = new BinaryTreeNode();
        root1.right.value = 7;

        root1.left = new BinaryTreeNode();
        root1.left.value = 8;

        root1.left.left = new BinaryTreeNode();
        root1.left.left.value = 9;

        root1.left.right = new BinaryTreeNode();
        root1.left.right.value = 2;

        root1.left.right.left = new BinaryTreeNode();
        /**
         * root1.left.right.left的value没有赋值
         */
        //感觉下面不对，
        root1.left.right.left.value = 4;

        root1.left.right.right = new BinaryTreeNode();
        root1.left.right.right.value = 7;

        recurseFront(root1);

        BinaryTreeNode root2 = new BinaryTreeNode();
        root2.value = 8;
        root2.left = new BinaryTreeNode();
        root2.left.value = 9;
        root2.right = new BinaryTreeNode();
        root2.right.value = 2;

        System.out.println(hasSubTree(root1, root2));
        System.out.println(hasSubTree(root2, root1));
        System.out.println(hasSubTree(root1, root1.left));
        System.out.println(hasSubTree(root1, null));
        System.out.println(hasSubTree(null, root2));
        System.out.println(hasSubTree(null, null));
    }

    public static boolean hasSubTree(BinaryTreeNode root1, BinaryTreeNode root2) {

        //引用是同一个，当然是相等的了
        if (root1 == root2) {
            return true;
        }
        if (root2 == null) {
            return true;
        }
        if (root1 == null) {
            return false;
        }

        boolean result = false;

        //如果节点的值相等，就调用匹配方法
        if (root1.value == root2.value) {
            result = match(root1, root2);
        }
        if (result) {
            return true;
        }
        //如果不匹配就找到树A的左子节点和右子节点进行判断
        return hasSubTree(root1.left, root2) || hasSubTree(root1.right, root2);
    }

    /**
     * 从树A根结点root1和树B根结点root2开始，一个一个元素进行判断，判断B是不是A的子结构
     *
     * @param root1
     * @param root2
     * @return
     */
    private static boolean match(BinaryTreeNode root1, BinaryTreeNode root2) {
        //只要两个对象是同一个就返回true
        if (root1 == root2) {
            return true;
        }
        // 只要树B的根结点点为空就返回true
        if (root2 == null) {
            return true;
        }
        // 树B的根结点不为空，如果树A的根结点为空就返回false
        if (root1 == null) {
            return false;
        }
        // 如果两个结点的值相等，则分别判断其左子结点和右子结点
        if (root1.value == root2.value) {
            return match(root1.left, root2.left) && match(root1.right, root2.right);
        }
        // 结点值不相等返回false
        return false;
    }

}
