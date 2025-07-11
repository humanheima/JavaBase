package com.hm.base.interview.sword_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by dumingwei on 2018/11/22
 * <p>
 * Desc:使用两个队列来实现栈,用一个队列实现一个栈。栈的特点是先进后出。
 * 用队列实现栈
 * <p>
 * 参考链接：{@see <a herf="https://www.jianshu.com/p/99d919146bf5">用队列实现一个栈</a>}
 */
public class ImplementStackWithTwoQueue {

    public static void main(String[] args) {

//        TwoQueueStack<Integer> stack = new TwoQueueStack<>();
//        System.out.println(stack.pop());
//        stack.push(1);
//        stack.push(2);
//        stack.push(3);
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        stack.push(4);
//        stack.push(5);
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());
//        System.out.println(stack.pop());

        AQueueImplementStack<Integer> stack = new AQueueImplementStack<>();
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


    class MyStack {

        private Queue<Integer> queue1 = new LinkedList<>();
        private Queue<Integer> queue2 = new LinkedList<>();


        public MyStack() {

        }

        public void push(int x) {
            if (!queue2.isEmpty()) {
                queue2.offer(x);
            } else {
                queue1.offer(x);
            }
        }

        public int pop() {
            if (!queue1.isEmpty()) {
                int size = queue1.size();
                for (int i = 0; i < size - 1; i++) {
                    queue2.offer(queue1.poll());
                }
                return queue1.poll();
            } else if (!queue2.isEmpty()) {
                int size = queue2.size();
                for (int i = 0; i < size - 1; i++) {
                    queue1.offer(queue2.poll());
                }
                return queue2.poll();
            } else {
                return -1;
            }
        }

        public int top() {
            if (!queue1.isEmpty()) {
                int size = queue1.size();
                Integer top = -1;
                for (int i = 0; i < size; i++) {
                    top = queue1.poll();
                    queue2.offer(top);
                }
                return top;
            } else if (!queue2.isEmpty()) {
                int size = queue2.size();
                Integer top = -1;
                for (int i = 0; i < size; i++) {
                    top = queue2.poll();
                    queue1.offer(top);
                }
                return top;
            } else {
                return -1;
            }
        }

        public boolean empty() {
            return queue1.isEmpty() && queue2.isEmpty();
        }
    }

    /**
     * 两个队列实现栈
     * 栈，先进后出
     * 队列，先进先出
     *
     * @param <T>
     */
    public static class TwoQueueStack<T> {

        private final Queue<T> queue1 = new LinkedList<>();
        private final Queue<T> queue2 = new LinkedList<>();

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
    public static class AQueueImplementStack<E> {

        private Queue<E> queue = new LinkedList<>();

        public AQueueImplementStack() {
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
