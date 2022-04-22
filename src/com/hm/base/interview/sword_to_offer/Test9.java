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
public class Test9 {

    public static void main(String[] args) {

        CQueue twoStackList = new CQueue();

        twoStackList.appendTail(1);
        twoStackList.appendTail(2);
        System.out.println(twoStackList.deleteHead());
        twoStackList.appendTail(3);
        twoStackList.appendTail(4);
        System.out.println(twoStackList.deleteHead());

    }

    static class CQueue {

        Stack<Integer> firstStack = new Stack<>();
        Stack<Integer> secondStack = new Stack<>();

        public CQueue() {

        }

        /**
         * 栈是先进后出的
         * 队列是先进先出的
         * <p>
         * 1. 开始 两个栈都为空
         * 2. 第一个数据来 ，压入到 firstStack
         * 3. 第二个数据来，压入到 firstStack
         * 3. 第3个数据来，压入到 firstStack
         * 4. 取数据，firstStack 把栈中所有的元素 压入secondStack ，然后从secondStack中pop出第一个数据。
         * 5. 第4个数据来，压入到哪里呢？把secondStack所有元素压入firstStack。firstStack再压入新的元素
         *
         * @param value
         */
        public void appendTail(int value) {
            while (!secondStack.isEmpty()) {
                firstStack.push(secondStack.pop());
            }
            firstStack.push(value);
        }

        public int deleteHead() {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop());
            }
            if (secondStack.isEmpty()) {
                return -1;
            }
            return secondStack.pop();
        }
    }

}
