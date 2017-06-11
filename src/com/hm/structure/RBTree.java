package com.hm.structure;

/**
 * Created by dumingwei on 2017/6/7.
 * 红黑树的定义
 * 1 任何一个节点都有颜色，黑色或者红色
 * 2 根节点是黑色的
 * 3 父子节点之间不能出现两个连续的红节点
 * 4 任何一个节点向下遍历到其子孙的叶子节点，所经过的黑节点个数必须相等
 * 5 空节点被认为是黑色的
 */
public class RBTree {

    /**
     * 红黑树的数据结构
     */
    class Node<T> {
        public T value;
        public Node<T> parent;
        public Node<T> left;
        public Node<T> right;
        public boolean isRed;
    }
}
