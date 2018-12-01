package com.hm.base.interview.sword_to_offer;

import java.util.Stack;

/**
 * Created by dumingwei on 2018/11/22
 * <p>
 * Desc:用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail 和deleteHead，
 * 分别完成在队列尾部插入结点和在队列头部删除结点的功能。
 * <p>
 * 参考链接：参考链接：{@see <a herf="https://blog.csdn.net/DERRANTCM/article/details/45457615">用两个栈实现队列</a>}
 */
public class ImplementQueueWithTwoStack {

    public static void main(String[] args) {

    }


    public static class TwoStackList<T> {

        /**
         * 插入栈，只用于插入数据
         */
        private Stack<T> stack1 = new Stack<>();

        /**
         * 弹出栈，用于弹出数据
         */
        private Stack<T> stack2 = new Stack<>();

        public TwoStackList() {
        }

        public void appendTail(T t) {
            stack1.add(t);
        }

        public T deleteHead() {

            /**
             * 判断弹出栈是否为空，如果为空就将插入栈的所有数据压入到弹出栈
             */
            if (stack2.isEmpty()) {
                while (!stack1.isEmpty()) {
                    stack2.add(stack1.pop());
                }
            }
            if (stack2.isEmpty()) {
                throw new RuntimeException("no more data");
            }
            return stack2.pop();
        }


    }

}
