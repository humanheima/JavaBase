package com.hm.base.interview.sword_to_offer;

import java.util.Stack;

/**
 * Created by dmw on 2018/12/15.
 * <p>
 * Desc:包含min函数的栈
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小素的min 函数。在该栈中，调用min、push 及pop的时间复杂度都是0(1)
 * <p>
 * 解题思路：把每次的最小元素（之前的最小元素和新压入战的元素两者的较小值）都保存起来放到另外一个辅助栈里。
 * 如果每次都把最小元素压入辅助栈， 那么就能保证辅助栈的栈顶一直都是最小元素．当元素从数据栈内被弹出之后，
 * 同时弹出辅助栈的栈顶元素，此时辅助栈的新栈顶元素就是下一个最小值。
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46691057
 */
public class Test30 {

    public static void main(String[] args) {
        StackWithMin2<Integer> stack = new StackWithMin2<>();
        stack.push(3);
        System.out.println(stack.min());
        stack.push(4);
        System.out.println(stack.min());
        stack.push(2);
        System.out.println(stack.min());
        stack.push(3);
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.pop();
        System.out.println(stack.min());
        stack.push(0);
        System.out.println(stack.min());
    }

    /**
     * 这个类使用辅助站来保存最小值
     *
     * @param <E>
     */
    public static class StackWithMin2<E extends Comparable<E>> {
        //数据栈，用来存放插入的数据
        private Stack<E> dataStack;
        //注意了：最小数栈
        private Stack<E> minStack;

        public StackWithMin2() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(E e) {
            dataStack.push(e);
            if (minStack.size() == 0 || e.compareTo(minStack.peek()) < 0) {
                minStack.push(e);
            } else {
                minStack.push(minStack.peek());
            }
        }

        public E pop() {
            if (!dataStack.isEmpty() && !minStack.isEmpty()) {
                minStack.pop();
                return dataStack.pop();
            }
            return null;
        }

        public E min() {
            if (!dataStack.isEmpty() && !minStack.isEmpty()) {
                return minStack.peek();
            }
            return null;
        }
    }

    /**
     * 这个类是使用一个辅助栈来保存最小值的位置，而不是最小值
     *
     * @param <E>
     */
    public static class StackWithMin<E extends Comparable<E>> {

        //数据栈，用来存放插入的数据
        public Stack<E> dataStack;
        //注意了：最小数位置栈，存放数据栈中最小的数的位置
        public Stack<Integer> minStack;

        public StackWithMin() {
            dataStack = new Stack<>();
            minStack = new Stack<>();
        }

        public E pop() {
            minStack.pop();
            return dataStack.pop();
        }

        public void push(E e) {
            if (e == null) {
                throw new RuntimeException("Element can not be null");
            }
            if (dataStack.isEmpty()) {
                dataStack.push(e);
                minStack.push(0);
            } else {
                // 获取数据栈中的最小元素（未插入t之前的）
                E smallestElement = dataStack.get(minStack.peek());
                //将e入栈
                dataStack.push(e);
                if (e.compareTo(smallestElement) < 0) {
                    // 将新的最小元素的位置入最小栈
                    minStack.push(dataStack.size() - 1);
                } else {
                    // 插入的元素不比原来的最小元素小，复制最小栈栈顶元素，将其入栈
                    minStack.push(minStack.peek());
                }
            }
        }

        public E min() {
            if (minStack.isEmpty()) {
                throw new RuntimeException("No element is stack.");
            }
            return dataStack.get(minStack.peek());
        }

    }
}
