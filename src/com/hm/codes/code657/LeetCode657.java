package com.hm.codes.code657;

/**
 * Created by dumingwei on 2022/4/4.
 * <p>
 * Desc:657. 机器人能否返回原点
 * 在二维平面上，有一个机器人从原点 (0, 0) 开始。给出它的移动顺序，判断这个机器人在完成移动后是否在 (0, 0) 处结束。
 * <p>
 * 移动顺序由字符串 moves 表示。字符 move[i] 表示其第 i 次移动。机器人的有效动作有 R（右），L（左），U（上）和 D（下）。
 * <p>
 * 如果机器人在完成所有动作后返回原点，则返回 true。否则，返回 false。
 * <p>
 * 注意：机器人“面朝”的方向无关紧要。 “R” 将始终使机器人向右移动一次，“L” 将始终向左移动等。此外，假设每次移动机器人的移动幅度相同。
 * <p>
 * 链接：https://leetcode-cn.com/problems/robot-return-to-origin
 * <p>
 * 解题思路：向左移动的次数等于向右移动的次数 && 向上移动的次数=向下移动的次数。
 * 用两个值记录，初始化为0：value1，value2；
 * value1：向上减1，向左减1；
 * value2：向下加1，向右加1；
 * 如果最后value1和value2都为0，说明可以回到原点。
 * 看懂了，简单那
 *
 */
public class LeetCode657 {

    public static void main(String[] args) {

        LeetCode657 leetCode657 = new LeetCode657();
        leetCode657.test1();
        leetCode657.test2();
        leetCode657.test3();
    }


    private void test1() {
        String moves = "UD";
        System.out.println(judgeCircle(moves));
    }

    private void test2() {
        String moves = "LL";
        System.out.println(judgeCircle(moves));
    }

    private void test3() {
        String moves = "LDRRLRUULR";
        System.out.println(judgeCircle(moves));
    }

    public boolean judgeCircle(String moves) {
        int initialValue1 = 0;
        int initialValue2 = 0;
        if (moves == null) {
            return true;
        }
        char[] charArray = moves.toCharArray();
        for (char c : charArray) {
            switch (c) {
                case 'U':
                    initialValue1 -= 1;
                    break;
                case 'D':
                    initialValue1 += 1;
                    break;
                case 'L':
                    initialValue2 -= 1;
                    break;
                case 'R':
                    initialValue2 += 1;
                    break;
            }
        }
        return initialValue1 == 0 && initialValue2 == 0;

    }


}
