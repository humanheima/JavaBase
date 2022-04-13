package com.hm.base.interview.sword_to_offer;

/**
 * Created by dumingwei on 2022/4/13.
 * <p>
 * Desc:输入一个char数组，翻转单词 例如：输入[i want eat a apple]，输出 [apple a eat want i]
 * 解题思路：
 * 1. 先把整个数组全部翻转
 * 2. 根据空格再单独翻转单词。
 */
public class Test58_Simple {

    public static void main(String[] args) {
        char[] array = "i want eat a apple".toCharArray();
        for (char c : array) {
            System.out.print(c);
        }
        reverse(array);
    }

    /**
     * @param array
     */
    private static void reverse(char[] array) {
        if (array == null || array.length == 1) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        while (i < j) {
            char temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
        System.out.println("----------------");
        for (char c : array) {
            System.out.print(c);
        }
        int start = 0;
        int end = 0;
        while (start < array.length) {
            StringBuilder stringBuilder = new StringBuilder();
            while (end < array.length && array[end] != ' ') {
                stringBuilder.append(array[end]);
                end++;
            }
            stringBuilder.reverse();
            int tempLength = stringBuilder.length();
            for (int k = 0; k < tempLength; k++) {
                array[start + k] = stringBuilder.charAt(k);
            }
            start = end + 1;
            end = end + 1;
            System.out.println();
            System.out.println("start = " + start + " ，end = " + end);
        }
        for (char c : array) {
            System.out.print(c);
        }
    }


}
