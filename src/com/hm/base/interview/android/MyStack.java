package com.hm.base.interview.android;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 两个队列实现一个栈
 * 用队列实现栈（双队列交互）
 * <p>
 * 参考 {@link  com.hm.base.interview.sword_to_offer.ImplementStackWithTwoQueue } 用一个队列实现一个栈
 */
public class MyStack {

    private Queue<Integer> queue1; // 主队列
    private Queue<Integer> queue2; // 辅助队列


    // 测试代码
    public static void main(String[] args) {
        MyStack stack = new MyStack();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println("栈顶元素: " + stack.top()); // 输出 3
        System.out.println("出栈: " + stack.pop());    // 输出 3
        System.out.println("栈顶元素: " + stack.top()); // 输出 2
        System.out.println("是否为空: " + stack.empty()); // 输出 false
    }

    /**
     * 初始化栈
     */
    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    /**
     * 入栈操作
     */
    public void push(int x) {
        queue1.offer(x); // 直接将元素加入 queue1  1 -> 2 -> 3
    }

    /**
     * 出栈操作
     */
    public int pop() {
        if (queue1.isEmpty()) {
            throw new IllegalStateException("栈为空");
        }
        // 将 queue1 的前 n-1 个元素移到 queue2
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        // 取出 queue1 的最后一个元素
        int result = queue1.poll();
        // 交换 queue1 和 queue2（queue2 变为主队列）
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return result;
    }

    /**
     * 查看栈顶元素
     */
    public int top() {
        if (queue1.isEmpty()) {
            throw new IllegalStateException("栈为空");
        }
        // 将 queue1 的前 n-1 个元素移到 queue2
        while (queue1.size() > 1) {
            queue2.offer(queue1.poll());
        }
        // 获取最后一个元素但不移除
        int result = queue1.peek();
        queue2.offer(queue1.poll()); // 将最后一个元素也移到 queue2
        // 交换 queue1 和 queue2
        Queue<Integer> temp = queue1;
        queue1 = queue2;
        queue2 = temp;
        return result;
    }

    /**
     * 判断栈是否为空
     */
    public boolean empty() {
        return queue1.isEmpty();
    }

}