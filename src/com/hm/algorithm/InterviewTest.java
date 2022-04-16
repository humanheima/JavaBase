package com.hm.algorithm;

import java.util.Random;

/**
 * Created by dumingwei on 2022/4/15.
 * <p>
 * Desc: 从 0 到 1000 随机取10个不同的数字。并从小到大打印出来。
 */
public class InterviewTest {

    public static void main(String[] args) {
        int[] array = new int[1000];
        for (int i = 0; i < 1000; i++) {
            array[i] = i;
        }
        select(array);
    }


    private static void select(int[] array) {
        int seed = array.length;
        int lastIndex = array.length - 1;

        for (int i = 10; i >= 0; i--) {
            int nextIndex = new Random().nextInt(seed);
            //System.out.println("nextIndex = " + nextIndex);
            int temp = array[lastIndex];
            array[lastIndex] = array[nextIndex];
            array[nextIndex] = temp;
            seed--;
            lastIndex--;
        }

        for (int i = array.length - 10; i < array.length; i++) {

            System.out.print(array[i] + " ");
        }

    }

}
