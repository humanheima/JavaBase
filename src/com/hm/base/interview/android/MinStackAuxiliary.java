package com.hm.base.interview.android;

import java.util.Stack;

/**
 * 最小栈.md
 * 最小栈是一个支持在O(1)时间内获取栈中最小元素的数据结构。
 */
class MinStackAuxiliary {


    // 测试代码
    public static void main(String[] args) {
        MinStackAuxiliary minStack = new MinStackAuxiliary();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Min: " + minStack.getMin()); // 输出 -3
        minStack.pop();
        System.out.println("Top: " + minStack.top());   // 输出 0
        System.out.println("Min: " + minStack.getMin()); // 输出 -2
    }

    private final Stack<Integer> dataStack;  // 数据栈
    private final Stack<Integer> minStack;   // 辅助栈，存储最小值

    public MinStackAuxiliary() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        dataStack.push(val);
        // 如果 minStack 为空，或新值小于等于当前最小值，压入 minStack
        if (minStack.isEmpty() || val <= minStack.peek()) {
            minStack.push(val);
        }
    }
    
    public void pop() {
        if (dataStack.isEmpty()) return;
        // 如果弹出的值是最小值，minStack 也要弹出
        int val = dataStack.pop();
        if (val == minStack.peek()) {
            minStack.pop();
        }
    }
    
    public int top() {
        if (dataStack.isEmpty()) throw new IllegalStateException("Stack is empty");
        return dataStack.peek();
    }
    
    public int getMin() {
        if (minStack.isEmpty()) throw new IllegalStateException("Stack is empty");
        return minStack.peek();
    }


}