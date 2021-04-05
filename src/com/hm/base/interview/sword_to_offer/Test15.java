package com.hm.base.interview.sword_to_offer;

/**
 * Created by dmw on 2018/12/1.
 * Desc:请实现一个函数， 输入一个整数，输出该数二进制表示中1的个数。例如把9表示成二进制是1001 ，有2位是1. 因此如果输入9，该出2。
 * <p>
 * 解题思路：首先把数字 n 和数字 1 做 与运算，判断数字 n 的最低位是不是 1 .接着把数字 1 左移一位得到 2，再和 n 做与运算判断 n 的倒数第二位是不是1。
 * 这样反复把数字1左移，每次都能判断 n 的其中一位是不是1。
 * <p>
 * 移位相关的知识点请参考 {@link com.hm.base.ShiftTest}
 * <p>
 * 参考链接：https://blog.csdn.net/derrantcm/article/details/45476103
 */
public class Test15 {

    public static void main(String[] args) {

        /*System.out.println(numberOfOne(1));
        System.out.println(numberOfOne(0));
        System.out.println(numberOfOne(0x7FFFFFFF));
        System.out.println(numberOfOne(0x80000000));
        System.out.println(numberOfOne(0xFFFFFFFF));*/
        /**
         * -5在计算机中用补码表示是 1111 1111 1111 1111 1111 1111 1111 1011
         */
        System.out.println(numberOfOne(-5));
        System.out.println(numberOfOne(5));

    }

    /**
     * @param n
     * @return 数字二进制表示中1的个数
     */
    public static int numberOfOne(int n) {
        int result = 0;
        for (int i = 0; i < 32; i++) {

            result += (n & 1);
            /**
             * 这里用带符号右移和无符号右移都可以，因为循环移动32次就结束了,不会出现死循环的问题。
             */
            n >>= 1;
        }
        return result;
    }

    /**
     * @param n
     * @return 数字二进制表示中1的个数
     */
    public static int numberOfOne2(int n) {
        int result = 0;
        while (n != 0) {
            result++;
            n = (n - 1) & n;
        }
        return result;
    }

    public static String intToBinary(int value) {
        StringBuilder numstr = new StringBuilder();
        while (value > 0) {
            int res = value % 2; //除2 取余数作为二进制数
            numstr.insert(0, res);
            value = value / 2;
        }
        return numstr.toString();
    }
}
