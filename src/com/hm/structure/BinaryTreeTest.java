package com.hm.structure;

import java.util.*;

/**
 * Created by dumingwei on 2017/10/7.
 * <p>
 * 遍历的树以项目根目录下的BinaryTree.png为例
 * 前序遍历：1，2，4，6，7，8，3，5
 * 中序遍历：4，7，6，8，2，1，3，5
 * 后续遍历：7，8，6，4，2，5，3，1
 * Desc:二叉树遍历 递归，非递归。
 * 参考链接：<a href="https://www.jianshu.com/p/456af5480cee></a>
 * <p>
 * https://blog.csdn.net/snow_7/article/details/51818580
 * 二叉树，看看遍历的方式，前序遍历，中序遍历，后序遍历，层次遍历就行了。重建二叉树啥的太高级了，不看了。
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTreeNode root = createTree();
        //postOrderTraversal(root);
        //printTree(root);

        testPreOrder(root);


        testMidOrder(root);

        testEndOrder(root);

        /*recurseFront(root);
        System.out.println();
        recurseEnd(root);
        reverseBinaryTree(root);
        recurseMid(root);
        printTree(root);
        System.out.println(maxDepth(root));
        System.out.println(getMinDepth(root));
        System.out.println();

        preOrderTraversal(root);
        System.out.println();

        recurseMid(root);
        System.out.println();

        middleOrderTraversal(root);
        System.out.println();*/

        //recurseEnd(root);
        //System.out.println();
        //postOrderTraversal(root);
        //System.out.println();

        //System.out.println(findDeep(root));

//        BinaryTreeTest treeTest = new BinaryTreeTest();
//
//        TreeNode root2 = treeTest.createTree2();
//        System.out.println(treeTest.postorderTraversal(root2));

    }

    private static void testEndOrder(BinaryTreeNode root) {
        recurseEnd(root);
        System.out.println();
        postorderTraversal(root);
    }

    private static void testMidOrder(BinaryTreeNode root) {
        recurseMid(root);
        System.out.println();
        middleOrderTraversal(root);
        System.out.println();
    }

    private static void testPreOrder(BinaryTreeNode root) {
        recurseFront(root);
        System.out.println();

        preorderTraversalUseStack(root);
        System.out.println();
    }

    public List<Integer> preorderTraversal2(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        List<Integer> list = new ArrayList<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                list.add(current.val);
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            //if (current.right != null) {
            current = current.right;
            //}
        }
        return list;
    }

    public List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        List<Integer> list = new ArrayList<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.pop();
            list.add(current.val);

            // if (current.right != null) {
            current = current.right;
            // }
        }
        return list;
    }


    /**
     * 非递归后序遍历，先左节点，后右节点，再父节点
     * Copilot 给出的写法，这种方法好理解
     * <p>
     * 在 Java 中，我们可以使用两个栈来实现二叉树的后序遍历，而不使用递归。以下是实现步骤：
     * 创建两个空栈，记为 stack1 和 stack2。
     * 将根节点压入 stack1。
     * 弹出 stack1 的顶部节点，将其压入 stack2。
     * 然后将该节点的左子节点和右子节点分别压入 stack1。
     * 重复步骤3和4，直到 stack1 为空。
     * 所有元素都在 stack2 中，从栈顶到栈底的顺序为左-右-根，这就是后序遍历的顺序。
     *
     * @param root
     */
    public static void postorderTraversal(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack1 = new Stack<>();
        Stack<BinaryTreeNode> stack2 = new Stack<>();
        if (root != null) {
            stack1.push(root);
            while (!stack1.isEmpty()) {
                root = stack1.pop();
                stack2.push(root);
                if (root.left != null) {
                    stack1.push(root.left);
                }
                if (root.right != null) {
                    stack1.push(root.right);
                }
            }
            while (!stack2.isEmpty()) {
                System.out.print(stack2.pop().value + " ");
            }
        }
    }


    /**
     * 非递归后序遍历，先左节点，后右节点，再父节点
     *
     * @param root
     * @return
     */
    public List<Integer> postorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        TreeNode lastVisit = root;

        List<Integer> list = new ArrayList<>();
        while (current != null || !stack.isEmpty()) {
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            current = stack.peek();
            if (current.right == null || current.right == lastVisit) {
                TreeNode pop = stack.pop();
                lastVisit = pop;
                list.add(pop.val);
                current = null;
            } else {
                current = current.right;
            }
        }
        return list;
    }

    /**
     * 非递归前序遍历，使用栈
     * <p>
     * 在 Java 中，我们可以使用栈来实现二叉树的前序遍历，而不使用递归。以下是实现步骤：
     * 创建一个空栈。
     * 将根节点压入栈中。
     * 当栈不为空时，弹出栈顶元素，打印节点的值，并先将其右子节点（如果有）压入栈中，然后将其左子节点（如果有）压入栈中。
     * 重复步骤3，直到栈为空。
     *
     * @param root
     */
    public static void preorderTraversalUseStack(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            System.out.print(node.value + " ");

            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 递归实现的前序遍历
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


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * 递归实现的中序遍历
     *
     * @param root
     */
    public static void recurseMid(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        recurseMid(root.getLeft());
        System.out.print(root.value + " ");
        recurseMid(root.getRight());
    }

    /**
     * 非递归中序遍历
     * 创建一个空栈。
     * 初始化当前节点为根节点。
     * 将当前节点压入栈中，并将当前节点设置为其左子节点。
     * 如果当前节点为空且栈非空，则从栈中弹出一个节点，打印节点的值，并将当前节点设置为其右子节点。
     * 重复步骤3和4，直到当前节点为空且栈也为空。
     *
     * @param root
     */
    public static void middleOrderTraversal(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode node = root;
        while (node != null || !stack.isEmpty()) {
            while (node != null) {
                stack.push(node);
                node = node.left;
            }
            //一直到当前节点的左子树为null，开始考虑当前节点的右子树
            if (!stack.isEmpty()) {
                node = stack.pop();
                System.out.print(node.value + " ");
                node = node.right;
            }
        }
    }

    /**
     * 递归实现的后序遍历递归实现
     *
     * @param root
     */
    public static void recurseEnd(BinaryTreeNode root) {
        if (root == null) {
            return;
        }
        recurseEnd(root.getLeft());
        recurseEnd(root.getRight());
        System.out.print(root.value + " ");
    }


    public static BinaryTreeNode createTree() {
        // 初始化节点
        BinaryTreeNode root = new BinaryTreeNode(1);
        BinaryTreeNode rootLeft = new BinaryTreeNode(2);
        BinaryTreeNode rootRight = new BinaryTreeNode(3);

        BinaryTreeNode rootLeftLeft = new BinaryTreeNode(4);
        BinaryTreeNode rootLeftLeftRight = new BinaryTreeNode(6);
        BinaryTreeNode rootLeftLeftRightLeft = new BinaryTreeNode(7);
        BinaryTreeNode rootLeftLeftRightRight = new BinaryTreeNode(8);
        BinaryTreeNode rootRightRight = new BinaryTreeNode(5);
        // 为root节点 赋予左右值
        root.left = rootLeft;
        root.right = rootRight;
        root.left.left = rootLeftLeft;
        root.left.left.right = rootLeftLeftRight;
        root.left.left.right.left = rootLeftLeftRightLeft;
        root.left.left.right.right = rootLeftLeftRightRight;

        root.right.right = rootRightRight;

        // 返回树根节点
        return root;
    }

    public TreeNode createTree2() {
        // 初始化节点
        TreeNode root = new TreeNode(1);
        TreeNode rootLeft = new TreeNode(2);
        TreeNode rootRight = new TreeNode(3);

        TreeNode rootLeftLeft = new TreeNode(4);
        TreeNode rootLeftLeftRight = new TreeNode(6);
        TreeNode rootLeftLeftRightLeft = new TreeNode(7);
        TreeNode rootLeftLeftRightRight = new TreeNode(8);
        TreeNode rootRightRight = new TreeNode(5);
        // 为root节点 赋予左右值
        root.left = rootLeft;
        root.right = rootRight;
        root.left.left = rootLeftLeft;
        root.left.left.right = rootLeftLeftRight;
        root.left.left.right.left = rootLeftLeftRightLeft;
        root.left.left.right.right = rootLeftLeftRightRight;

        root.right.right = rootRightRight;

        // 返回树根节点
        return root;
    }

    private static BinaryTreeNode reverseBinaryTree(BinaryTreeNode root) {
        if (root == null) {
            return null;
        } else {
            BinaryTreeNode left = reverseBinaryTree(root.getLeft());
            BinaryTreeNode right = reverseBinaryTree(root.getRight());
            root.left = right;
            root.right = left;
            return root;
        }

    }

    private static void printTree(BinaryTreeNode root) {
        if (root == null)
            return;
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode current = queue.poll();
            //System.out.println("current = " + current.value);
            if (current.left != null) {
                queue.add(current.left);
            }
            if (current.right != null) {
                queue.add(current.right);
            }
        }
    }

    //获取最大深度
    private static int maxDepth(BinaryTreeNode node) {
        if (node == null)
            return 0;
        int leftDepth = maxDepth(node.left);
        int rightDepth = maxDepth(node.right);
        return 1 + Math.max(leftDepth, rightDepth);
    }

    //获取最小深度
    private static int getMinDepth(BinaryTreeNode root) {
        if (root == null) {
            return 0;
        }
        return getMin(root);
    }

    private static int getMin(BinaryTreeNode root) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }
        return Math.min(getMin(root.left), getMin(root.right)) + 1;
    }

    /**
     * 非递归获取树的最大深度
     *
     * @param root
     * @return
     */
    public static int findDeep(BinaryTreeNode root) {
        if (root == null)
            return 0;
        BinaryTreeNode current = null;
        Deque<BinaryTreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        int cur, last;
        int level = 0;
        while (!queue.isEmpty()) {
            cur = 0;//记录本层已经遍历的节点个数
            last = queue.size();//当遍历完当前层以后，队列里元素全是下一层的元素，队列的长度是这一层的节点的个数
            while (cur < last) {
                //当还没有遍历到本层最后一个节点时循环
                current = queue.poll();//出队一个元素
                cur++;
                //把当前节点的左右节点入队（如果存在的话）
                if (current.left != null) {
                    queue.offer(current.left);
                }
                if (current.right != null) {
                    queue.offer(current.right);
                }
            }
            level++;//每遍历完一层level+1
        }
        return level;
    }

}
