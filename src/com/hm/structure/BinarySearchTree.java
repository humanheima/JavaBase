package com.hm.structure;

import com.hm.collection.TreeSetTest;

/**
 * Created by dmw on 2018/12/27.
 * Desc: 二叉查找树
 * 二叉查找树的定义：
 * 二叉查找树（英语：Binary Search Tree），是指一棵空树或者具有下列性质的二叉树
 * 1. 若任意节点的左子树不空，则左子树上所有节点的值均小于它的根节点的值；
 * 2. 若任意节点的右子树不空，则右子树上所有节点的值均大于它的根节点的值；
 * 3. 任意节点的左、右子树也分别为二叉查找树；
 * 4. 没有键值相等的节点。
 * <p>
 * 参考链接：图解：二叉搜索树算法（https://www.bysocket.com/?p=1209）
 */
public class BinarySearchTree {

    public BinaryTreeNode root;

    public BinarySearchTree() {
    }

    public BinarySearchTree(BinaryTreeNode root) {
        this.root = root;
    }

    public BinaryTreeNode insert(int key) {
        BinaryTreeNode newNode = new BinaryTreeNode(key);
        BinaryTreeNode current = root;
        BinaryTreeNode parent;
        //如果根节点为空
        if (current == null) {
            root = newNode;
            return newNode;
        }
        while (true) {
            parent = current;
            if (key < current.value) {
                current = current.left;
                if (current == null) {
                    parent.left = newNode;
                    return newNode;
                }
            } else {
                current = current.right;
                if (current == null) {
                    parent.right = newNode;
                    return newNode;
                }
            }
        }
    }

    public BinaryTreeNode search(int key) {
        BinaryTreeNode current = root;
        while (current != null && key != current.value) {
            if (key < current.value) {
                current = current.left;
            } else {
                current = current.right;
            }
        }
        return current;
    }

    public BinaryTreeNode delete(int key) {
        BinaryTreeNode parent = root;
        BinaryTreeNode current = root;
        //标记当前节点是父节点的左节点还是右节点
        boolean isLeftChild = false;
        // 找到删除节点及是否在左子树
        while (current.value != key) {
            parent = current;
            if (current.value > key) {
                isLeftChild = true;
                current = current.left;
            } else {
                isLeftChild = false;
                current = current.right;
            }
            //如果要删除的节点为null，直接返回
            if (current == null) {
                return current;
            }
        }

        // 如果删除节点左节点为空 , 右节点也为空
        if (current.left == null && current.right == null) {
            if (current == root) {
                root = null;
            }
            if (isLeftChild) {
                parent.left = null;
            } else {
                parent.right = null;
            }
        } else if (current.right == null) {// 如果要删除的节点只有左节点
            if (current == root) {
                root = root.left;
            } else if (isLeftChild) {
                parent.left = current.left;
            } else {
                parent.right = current.left;
            }
        } else if (current.left == null) {// 如果要删除的节点只有右节点
            if (current == root) {
                root = current.right;
            } else if (isLeftChild) {
                parent.left = current.right;
            } else {
                parent.right = current.right;
            }
        } else {
            // 如果删除节点左右子节点都不为空,则先找到待删除节点的中序遍历的后继节点，用该后继节点的值替换待删除的节点的值，然后删除后继节点。
            BinaryTreeNode successor = getDeleteSuccessor(current);
            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.left = successor;
            } else {
                parent.right = successor;
            }
            successor.left = current.left;
        }
        //将删除节点的子节点都置为null
        current.left = null;
        current.right = null;
        return current;
    }

    public void printTree(BinaryTreeNode root) {
        if (root != null) {
            printTree(root.left);
            System.out.print("" + root.value + " ");
            printTree(root.right);
        }
    }

    /**
     * 获取删除节点的后继者，删除节点的后继者是在其右子树中最小的节点
     *
     * @param deleteNode
     * @return
     */
    private BinaryTreeNode getDeleteSuccessor(BinaryTreeNode deleteNode) {
        //要删除节点的后继节点
        BinaryTreeNode successor = null;
        BinaryTreeNode successorParent = null;
        BinaryTreeNode current = deleteNode.right;
        while (current != null) {
            successorParent = successor;
            successor = current;
            current = current.left;
        }
        if (successor != deleteNode.right) {
            successorParent.left = successor.right;
            successor.right = deleteNode.right;
        }
        return successor;
    }


    public static void main(String[] args) {
        //BinaryTreeNode root = new BinaryTreeNode(8);
        //BinaryTreeNode root = new BinaryTreeNode();
        BinarySearchTree treeTest = new BinarySearchTree();
        treeTest.insert(11);
        treeTest.insert(2);
        treeTest.insert(14);
        treeTest.insert(1);
        treeTest.insert(7);
        treeTest.insert(15);
        treeTest.insert(5);
        treeTest.insert(8);

        treeTest.printTree(treeTest.root);

        //treeTest.delete(4);

        System.out.println();

        treeTest.printTree(treeTest.root);
        /*BinaryTreeNode deletedNode = treeTest.delete(10);
        System.out.println(deletedNode.value);*/
    }

}
