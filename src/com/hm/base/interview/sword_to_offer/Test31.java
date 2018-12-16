package com.hm.base.interview.sword_to_offer;

import java.util.Stack;

/**
 * Created by dmw on 2018/12/15.
 * Desc: 栈的压入，弹出序列。 输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如 序列{1,2,3,4,5}是某栈的压栈序列，序列{4,5,3,2,1}是该压栈序列对应的一个弹出序列，但{4,3,5,1,2}就不是该压栈序列的弹出序列。
 * （当时看题目的时候不明白为什么： 序列{1,2,3,4,5}是某栈的压栈序列，序列{4,5,3,2,1}是该压栈序列对应的一个弹出序列，
 * 原因：先压入1,2,3,4，然后弹出4，然后压入5，然后弹出5，然后依次弹出3,2,1,弹出序列就是{4,5,3,2,1}）
 * <p>
 * 解题思路: 解决这个问题很直观的想法就是建立一个辅助栈，把输入的第一个序列中的数字依次压入该辅助栈，并按照第二个序列的顺序依次从该栈中弹出数字。
 * <p>
 * 判断一个序列是不是栈的弹出序列的规律：如果下一个弹出的数字刚好是栈顶数字，那么直接弹出。如果下一个弹出的数字不在栈顶，
 * 我们把压栈序列中还没有入栈的数字压入辅助栈，直到把下一个需要弹出的数字压入栈顶为止。
 * 如果所有的数字都压入栈了仍然没有找到下一个弹出的数字，那么该序列不可能是一个弹出序列。
 * <p>
 * 测试用例:
 * 1. 输入的两个数组含有1个数字，第二个数组是第一个数组表示的压入序列的弹出序列
 * 2. 输入的两个数组含有1个数字，第二个数组不是第一个数组表示的压入序列的弹出序列
 * <p>
 * 3. 输入的两个数组含有多个数字，第二个数组是第一个数组表示的压入序列的弹出序列
 * 4. 输入的两个数组含有多个数字，第二个数组不是第一个数组表示的压入序列的弹出序列
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/46691083
 */
public class Test31 {

    public static void main(String[] args) {
        int[] push = {1, 2, 3, 4, 5};

        int[] pop1 = {4, 5, 3, 2, 1};//true
        int[] pop2 = {3, 5, 4, 2, 1};//true

        int[] pop3 = {4, 3, 5, 1, 2};//false
        int[] pop4 = {3, 5, 4, 1, 2};//false

        int[] push5 = {1};
        int[] pop5 = {2};//false

        int[] push6 = {1};
        int[] pop6 = {1};//true

        System.out.println(isPopOrder(push, pop1));
        System.out.println(isPopOrder(push, pop2));
        System.out.println(isPopOrder(push, pop3));
        System.out.println(isPopOrder(push, pop4));

        System.out.println(isPopOrder(push5, pop5));

        System.out.println(isPopOrder(push6, pop6));

        System.out.println(isPopOrder(null, null));//false

        System.out.println("---------------------------------");

        System.out.println(isPopOrder2(push, pop1));
        System.out.println(isPopOrder2(push, pop2));
        System.out.println(isPopOrder2(push, pop3));
        System.out.println(isPopOrder2(push, pop4));

        System.out.println(isPopOrder2(push5, pop5));//false

        System.out.println(isPopOrder2(push6, pop6));//true

        System.out.println(isPopOrder2(null, null));//false
    }

    /**
     * @param push 压栈序列
     * @param pop  出栈序列
     * @return true 出栈序列是入栈序列的一个弹出序列
     */
    public static boolean isPopOrder(int[] push, int[] pop) {
        if (push == null || pop == null || push.length == 0 || pop.length == 0 || push.length != pop.length) {
            return false;
        }
        //用于存放入栈时的数据
        Stack<Integer> stack = new Stack<>();
        // 用于记录入栈数组元素的处理位置
        int pushIndex = 0;
        // 用于记录出栈数组元素的处理位置
        int popIndex = 0;

        //如果还有出栈的元素要处理
        while (popIndex < pop.length) {
            // 入栈元素还未全部入栈的条件下，如果栈为空，或者栈顶的元素不与当前处理的相等，则一直进行入栈操作，
            while (pushIndex < push.length && (stack.isEmpty() || (stack.peek() != pop[popIndex]))) {
                stack.push(push[pushIndex]);
                pushIndex++;
            }
            /**
             * 如果入栈元素等于出栈元素,入栈元素弹出，继续处理下一个出栈元素
             */
            if (stack.peek() == pop[popIndex]) {
                stack.pop();
                //处理下一个元素
                popIndex++;
            } else {
                return false;
            }
        }
        return true;
    }

    public static boolean isPopOrder2(int[] push, int[] pop) {

        boolean isPossible = false;

        if (push != null && pop != null && push.length > 0 && push.length == pop.length) {
            // 用于存放入栈时的数据
            Stack<Integer> stack = new Stack<>();
            // 记录下一个要处理的入栈元素的位置
            int nextPush = 0;
            // 记录下一个要处理的出栈元素的位置
            int nextPop = 0;
            // 如果出栈元素没有处理完就继续进行处理
            while (nextPop < pop.length) {
                // 如果栈为空或者栈顶的元素与当前处理的出栈元素不相同，一直进行入栈操作
                while (stack.isEmpty() || stack.peek() != pop[nextPop]) {
                    // 如果入栈的元素已经全部入栈了，就退出内层循环
                    if (nextPush >= push.length) {
                        break;
                    }
                    stack.push(push[nextPush]);
                    //指向下一个要处理的入栈元素的位置
                    nextPush++;
                }
                // 执行到此处有两种情况：
                // 第一种：在栈顶上找到了一个与入栈元素相等的元素
                // 第二种：在栈顶上没有找到一个与入栈元素相等的元素，而且输入栈的元素已经全部入栈了

                // 对于第二种情况就说弹出栈的顺序是不符合要求的，退出外层循环
                if (stack.peek() != pop[nextPop]) {
                    break;
                }
                // 对应到第一种情况：需要要栈的栈顶元素弹出
                stack.pop();
                // 指向下一个要处理的出栈元素的位置
                nextPop++;
            }

            // 执行到此处有两种情况
            // 第一种：外层while循环的在第一种情况下退出，
            // 第二种：所有的出栈元素都被正确匹配
            // 对于出现的第一种情况其stack.isEmpty()必不为空，原因为分析如下：
            // 所有的入栈元素一定会入栈，但是只有匹配的情况下才会出栈，
            // 匹配的次数最多与入栈元素个数元素相同（两个数组的长度相等），如果有不匹配的元素，
            // 必然会使出栈的次数比入栈的次数少，这样栈中至少会有一个元素
            // 对于第二种情况其stack.isEmpty()一定为空
            // 所以书本上的nextPop == pop.length（pNextPop-pPop==nLength）是多余的
            if (stack.isEmpty()) {
                isPossible = true;
            }
        }
        return isPossible;
    }


}
