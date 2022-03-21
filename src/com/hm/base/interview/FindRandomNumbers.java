package com.hm.base.interview;


import java.util.Random;

/**
 * Created by dumingwei on 2021/11/15.
 * <p>
 * Desc: 在一个不重复的数组中，随机取出几个不重复的数字。
 */
public class FindRandomNumbers {

    public static void main(String[] args) {
        int[] array = new int[100];
        for (int i = 0; i < 100; i++) {
            array[i] = i;
        }

        int[] result = getUniqueArray(array, 100);
        for (int i : result) {
            System.out.print(i + " ");

        }
        System.out.println();
    }

    /**
     * 从array中取出count个不重复的数
     *
     * @param array
     * @param count
     * @return 没有重复元素的数组
     */
    public static int[] getUniqueArray(int[] array, int count) {
        int length = array.length;
        if (count <= 0 || count > array.length) {
            return null;
        }
        int[] result = new int[count];

        int firstIndex = 0;

        int lastIndex = length - 1;

        int numbers = 0;
        Random random = new Random();
        while (numbers < count) {
            int nextInt = random.nextInt(length);
            if (nextInt <= lastIndex) {
                if (nextInt == lastIndex) {
                    //最后一个元素不需要交换
                    result[firstIndex] = array[nextInt];
                } else {
                    //加入元素
                    int item = array[nextInt];
                    result[firstIndex] = item;
                    //把已经加入的元素交换到array数组末尾
                    int temp = array[lastIndex];
                    array[lastIndex] = item;
                    array[nextInt] = temp;
                }
                firstIndex++;
                lastIndex--;
                numbers++;
            }

        }
        return result;
    }
}
