package com.hm.base.interview.android;

import com.hm.structure.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 序列化与反序列化二叉树.md
 * 使用前序遍历序列化二叉树
 */
public class SerializeTree {

    private List<Integer> result = new ArrayList<>();

    /**
     * 1
     * / \
     * 2   3
     * /     \
     * 4       5
     *
     * @param args
     */
    public static void main(String[] args) {
        // 构建测试二叉树
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.right = new TreeNode(5);

        // 实例化 Codec
        SerializeTree codec = new SerializeTree();

        // 序列化
        String serialized = codec.serialize(root);
        System.out.println("Serialized: " + serialized);

        // 反序列化
        TreeNode deserialized = codec.deserialize(serialized);

        codec.result.clear();
        codec.preorder(deserialized);
        System.out.println(codec.result);


        // 验证：再次序列化并比较
        //String serializedAgain = codec.serialize(deserialized);
        //System.out.println("Serialized again: " + serializedAgain);

        // 检查是否一致
        //System.out.println("Correct: " + serialized.equals(serializedAgain));
    }


    // 序列化二叉树为字符串
    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private void serializeHelper(TreeNode root, StringBuilder sb) {
        if (root == null) {
            sb.append("#,");
            return;
        }
        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // 反序列化字符串为二叉树
    public TreeNode deserialize(String data) {
        String[] nodes = data.split(",");

//        for (String node : nodes) {
//            System.out.print(node);
//        }
        int[] index = {0}; // 用数组包装索引，以便在递归中修改
        return deserializeHelper(nodes, index);
    }

    private TreeNode deserializeHelper(String[] nodes, int[] index) {
/*        if (index[0] >= nodes.length || nodes[index[0]].equals("#")) {
            index[0]++;
            return null;
        }*/


        if (index[0] >= nodes.length) {
            return null;
        }
        String val = nodes[index[0]];

        if (val.equals("#")) {
            index[0]++;
            return null;
        }

        TreeNode root = new TreeNode(Integer.parseInt(nodes[index[0]]));
        index[0]++;
        root.left = deserializeHelper(nodes, index);
        root.right = deserializeHelper(nodes, index);
        return root;
    }


    /**
     * 递归实现的前序遍历
     *
     * @param node
     */
    // 递归辅助函数
    private void preorder(TreeNode node) {
        if (node == null) {
            return; // 空节点，返回
        }
        result.add(node.val); // 访问根节点
        preorder(node.left);  // 遍历左子树
        preorder(node.right); // 遍历右子树
    }
}
