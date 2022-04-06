package com.hm.base.interview.sword_to_offer;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * Created by dumingwei on 2018/11/22
 * <p>
 * Desc:使用两个队列来实现栈,用一个队列实现一个栈。栈，先进后出。
 * <p>
 * 参考链接：参考链接：{@see <a herf="https://www.jianshu.com/p/99d919146bf5">用队列实现一个栈</a>}
 */
public class ImplementStackWithTwoQueue {

    public static void main(String[] args) {

        /*TwoQueueStack<Integer> stack = new TwoQueueStack<>();
        System.out.println(stack.pop());
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        stack.push(5);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());*/

        AQueueImplmentStack<Integer> stack = new AQueueImplmentStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        stack.push(4);
        stack.push(5);
        stack.push(6);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());


    }

    /**
     *  两个队列实现栈
     * @param <T>
     */
    public static class TwoQueueStack<T> {

        private Queue<T> queue1 = new LinkedList<>();
        private Queue<T> queue2 = new LinkedList<>();

        public TwoQueueStack() {
        }

        public void push(T t) {
            if (!queue2.isEmpty()) {
                queue2.offer(t);
            } else {
                queue1.offer(t);
            }
        }

        public T pop() {
            if (!queue2.isEmpty()) {
                int size = queue2.size();
                for (int i = 0; i < size - 1; i++) {
                    queue1.offer(queue2.poll());
                }
                return queue2.poll();
            } else if (!queue1.isEmpty()) {
                int size = queue1.size();
                for (int i = 0; i < size - 1; i++) {
                    queue2.offer(queue1.poll());
                }
                return queue1.poll();
            } else {
                return null;
            }
        }

    }

    /**
     * 用一个队列实现一个栈
     */
    public static class AQueueImplmentStack<E> {

        private Queue<E> queue = new LinkedList<>();

        public AQueueImplmentStack() {
        }

        public void push(E e) {
            queue.offer(e);
        }

        public E pop() {
            if (queue.isEmpty()) {
                return null;
            }
            int size = queue.size();
            /**
             * 把最后一个元素之前的所有元素重新加入到队列的末尾，然后弹出第一个元素
             */
            for (int i = 0; i < size - 1; i++) {
                queue.offer(queue.poll());
            }
            return queue.poll();
        }
    }

}
