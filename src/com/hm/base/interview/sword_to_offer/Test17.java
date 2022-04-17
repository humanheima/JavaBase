package com.hm.base.interview.sword_to_offer;

/**
 * Created by dmw on 2018/12/1.
 * Desc: 输入数字n，按顺序打印出从1最大的n位十进制数。比如输入3，则打印出1、2、3 一直到最大的3位数即999。
 * <p>
 * 解题思路：
 * <p>
 * 1.用一个长度为n的数组，用来表示n位数，每一位进行都进行从0到9的排列即可。
 * <p>
 * <p>
 * 2.用一个长度为n的数组，用来表示n位数。每次把数组的从后向前加1(来模拟数字的加法),然后打印数组中每次加一存储的值。感觉这个思路比较直观。
 */
public class Test17 {

    public static void main(String[] args) {

//        printOneToNthDigits(0);
//        printOneToNthDigits(1);
//        printOneToNthDigits(2);
//        printOneToNthDigits(3);

        Test17 test17 = new Test17();
        test17.printArray2(test17.printNumbers(0));
        test17.printArray2(test17.printNumbers(1));
        test17.printArray2(test17.printNumbers(0));
        /*printOneToNthDigits2(1);
        printOneToNthDigits2(4);*/
    }

    public void printArray2(int[] ints) {
        if (ints == null) {
            return;
        }
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + " ");

        }
        System.out.println();

    }

    /**
     * 在 LeetCode上的提交
     * @param n
     * @return
     */
    public int[] printNumbers(int n) {
        if (n < 1) {
            int[] result = {};
            return result;

        }
        int maxLimit = 1;
        while (n > 0) {
            maxLimit *= 10;
            n--;
        }
        System.out.println("maxLimit = " + maxLimit);
        int[] result = new int[maxLimit - 1];
        for (int i = 0; i < maxLimit - 1; i++) {
            result[i] = i + 1;
        }
        return result;
    }


    /**
     * @param n 数字的最大位数
     */
    public static void printOneToNthDigits(int n) {

        if (n < 1) {
            throw new IllegalArgumentException("The input number must large than 0.");
        }

        int[] arr = new int[n];

        printOneToNthDigits(0, arr);

    }

    /**
     * @param n   当前处理的是第几个元素，从0开始计数
     * @param arr 存放结果的数组
     */
    private static void printOneToNthDigits(int n, int[] arr) {
        if (n >= arr.length) {
            printArray(arr);
        } else {
            for (int i = 0; i <= 9; i++) {
                arr[n] = i;
                printOneToNthDigits(n + 1, arr);
            }
        }
    }

    /**
     * @param n 数字的最大位数
     */
    private static void printOneToNthDigits2(int n) {
        if (n < 1) {
            throw new IllegalArgumentException("The input number must large than 0.");
        }

        int[] arr = new int[n];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = 0;
        }
        //求结果，如果最高位没有进位就一直进行处理
        while (!addOne(arr)) {
            printArray(arr);
        }

    }

    /**
     * 对arr表示的数组的最低位加1 arr中的每个数都不能超过9不能小于0，每个位置模拟一个数位
     *
     * @param arr 待加的数组
     * @return 判断最高位是否有进位，如果有进位就返回1，否则返回0
     */
    private static boolean addOne(int[] arr) {
        /**
         * 用来标志最高位是否进位
         */
        boolean overFlow = false;

        int carry = 1;
        int index = arr.length - 1;
        while (index >= 0) {
            arr[index] += carry;
            //如果arr[index]是0,到9，那么carry这时候是0了。
            carry = arr[index] / 10;
            if (carry == 1) {
                //如果进位了的话，当前index上的数值要对10取模。
                arr[index] = arr[index] % 10;
                //当前index需要向前进一位
                index--;
                if (index < 0) {
                    overFlow = true;
                    break;
                }
            } else {
                break;
            }
        }

        return overFlow;
       /* int index = arr.length;
        do {
            index--;
            arr[index] += carry;
            carry = arr[index] / 10;
            arr[index] %= 10;
        } while (carry != 0 && index > 0);
        if (carry > 0 && index == 0) {
            return 1;
        }
        return 0;*/
    }

    private static void printArray(int[] arr) {
        int index = 0;
        while (index < arr.length && arr[index] == 0) {
            index++;
        }
        for (int i = index; i < arr.length; i++) {
            System.out.print(arr[i]);
        }
        if (index < arr.length) {
            System.out.println();
        }
    }

}
