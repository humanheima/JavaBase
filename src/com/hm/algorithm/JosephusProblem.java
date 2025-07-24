package com.hm.algorithm;

// 约瑟夫环问题：n个人围成一圈，从第k个人开始报数，每次报m的人出列，直到圈中只剩一人。
// 原理：这是一个经典的循环淘汰问题，可以通过数学递推或数据结构（如循环链表）解决。
// 数学递推公式：f(n,m) = (f(n-1,m) + m) % n，其中f(1,m) = 0，表示最后剩下的人的编号。
// 以下是使用循环链表的Java实现，模拟实际淘汰过程。

public class JosephusProblem {

    public static void main(String[] args) {
        int n = 7; // 总人数
        int m = 3; // 报数间隔
        int k = 1; // 起始位置
        int result = josephus(n, m, k);
        System.out.println("最后剩下的人的编号是: " + result);
    }

    // 定义节点类，用于构建循环链表
    static class Node {
        int data;
        Node next;
        
        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }
    
    // 解决约瑟夫环问题

    /**
     * n个人围成一圈，从第k个人开始报数，每次报m的人出列，直到圈中只剩一人。
     * @param n n个人围成一圈
     * @param m 每次报m的人出列
     * @param k 从第k个人开始
     * @return
     */
    public static int josephus(int n, int m, int k) {
        if (n <= 0 || m <= 0 || k <= 0 || k > n) {
            return -1; // 输入无效
        }
        
        // 构建循环链表
        Node head = new Node(1);
        Node current = head;
        for (int i = 2; i <= n; i++) {
            current.next = new Node(i);
            current = current.next;
        }
        current.next = head; // 形成循环链表
        
        // 找到第k个人作为起始点
        for (int i = 1; i < k; i++) {
            current = current.next;
        }
        
        // 模拟淘汰过程
        // current.next == current 就是只剩一个节点了。
        while (current.next != current) { // 直到只剩一个节点
            // 找到第m-1个节点
            for (int i = 1; i < m - 1; i++) {
                current = current.next;
            }
            // 删除第m个节点
            current.next = current.next.next;
            current = current.next; // 移动到下一个节点
        }
        
        return current.data; // 返回最后剩下的节点编号
    }
    

}