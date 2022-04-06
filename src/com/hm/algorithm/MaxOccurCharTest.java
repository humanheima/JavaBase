package com.hm.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dumingwei on 2022/4/4.
 * <p>
 * Desc:给你一个字符串，输出频率最高且最先出现的字符
 * 假设有一个字符串，字符串的所有字符都在ascii编码的范围内，编码求出字符串中出现频率最高的字符，如果频率最高的字符有多个，则输出最先出现的字符。
 * <p>
 * 参考链接：https://blog.csdn.net/hubiao_0618/article/details/105279236
 */
public class MaxOccurCharTest {

    public static void main(String[] args) {

        char result = getMaxOccurChar("hello world, every body!");
        System.out.println(result);
    }

    public static char getMaxOccurChar(String str) {

        int maxCount = 1;
        char result = str.charAt(0);

        Map<Character, Integer> map = new LinkedHashMap<>();
        for (int i = 0; i < str.length(); i++) {
            Character content = str.charAt(i);
            Integer count = map.get(content);
            if (count == null) {
                map.put(content, 1);
            } else {
                map.put(content, count + 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            if (entry.getValue() > maxCount) {
                result = entry.getKey();
                maxCount = entry.getValue();
            }
        }
        return result;
    }

    public static void sort(int[] array, int left, int right) {
        if (left < right) {
            int pivot = array[left];
            int i = left;
            int j = right;

            while (i < j) {

                while (i < j && array[j] > pivot) {
                    j--;
                }

                if (i < j) {
                    array[i] = array[j];
                    i++;
                }

                while (array[i] > pivot) {
                    i++;
                }
                if (i < j) {

                }
            }


        }
    }


}
