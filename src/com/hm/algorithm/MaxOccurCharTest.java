package com.hm.algorithm;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by dumingwei on 2022/4/4.
 * <p>
 * Desc:给你一个字符串，输出频率最高且最先出现的字符
 * 假设有一个字符串，字符串的所有字符都在ascii编码的范围内，编码求出字符串中出现频率最高的字符，如果频率最高的字符有多个，则输出最先出现的字符。
 * <p>
 * <p>
 * 参考链接：https://blog.csdn.net/hubiao_0618/article/details/105279236
 */
public class MaxOccurCharTest {

    public static void main(String[] args) {

        char result = getMaxOccurChar("hello world, every body!");
        System.out.println(result);

        System.out.println(getMaxOccurCharTwo("hello world, every body!"));
    }

    /**
     * 解题思路
     * <p>
     * 1. 遍历字符串中的字符，用Map 保存键值对， key 是字符 ，value 是字符出现的次数。但是为了保证输出最先出现的字符，所以要用LinkedHashMap，不能用HashMap
     * 2. 遍历Map，输出出现次数最多的 key
     *
     * @param str
     * @return
     */
    public static char getMaxOccurChar(String str) {

        int maxCount = 1;
        char result = str.charAt(0);

        /**
         * 为什么不能用HashMap，因为要确保输出最先出现的字符，所以要用LinkedHashMap
         */
        //Map<Character, Integer> map = new HashMap<>();
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
            Integer integer = entry.getValue();
            if (integer > maxCount) {
                result = entry.getKey();
                maxCount = integer;
            }
        }
        return result;
    }

    /**
     * @param str
     * @return
     */
    public static char getMaxOccurCharTwo(String str) {

        int maxCount = 1;
        char result = str.charAt(0);

        Map<Character, Integer> map = new LinkedHashMap<>();

        for (int i = str.length() - 1; i >= 0; i--) {
            Character content = str.charAt(i);
            Integer count = map.get(content);
            if (count == null) {
                map.put(content, 1);
                count = 1;
            } else {
                map.put(content, count + 1);
                count += 1;
            }
            if (count >= maxCount) {
                maxCount = count;
                result = str.charAt(i);
            }
        }
        return result;
    }

}
