package com.hm.base.interview;

import com.hm.structure.TreeNode;

import java.util.*;

/**
 * Created by dumingwei on 2017/10/1.
 */
public class Test01 {

    public static void main(String[] args) {
        Set<Student> set = new TreeSet<>();
        set.add(new Student("Hao LUO", 33));
        set.add(new Student("XJ WANG", 32));
        set.add(new Student("Bruce LEE", 60));
        set.add(new Student("Bob YANG", 22));
        for (Student student : set) {
            System.out.println(student);
        }
    }

    private List<Integer> preOrderTraversal2(TreeNode root) {
        if (root == null) {
            return null;
        }
        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            result.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }

        return result;

    }

}
