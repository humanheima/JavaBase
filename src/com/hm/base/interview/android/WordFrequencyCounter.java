package com.hm.base.interview.android;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by p_dmweidu on 2025/7/19
 * Desc:
 * 题目描述：
 * * 给定一段字符串（英文），统计其中每个“单词”出现的次数（大小写不敏感）。返回一个 Map<String, Integer> 表示每个单词的出现频次。
 * * <p>
 * * 你可以假设：
 * * 输入可能包含标点符号；
 * * 单词之间由空格分隔；
 * * 单词大小写应统一处理为小写。
 * * <p>
 * * 示例输入：String input = "Hello world!Hello, Android. Hello MiChat.";
 * * 示例输出：{hello=3, world=1, android=1, michat=1}
 */
public class WordFrequencyCounter {

    // 测试代码
    public static void main(String[] args) {
        //String input = "Hello world!Hello, Android. Hello MiChat.";
        //正则表达式
        /**
         * \s：匹配空白字符（空格、制表符 \t、换行符 \n、回车符 \r 等）。
         *
         * \S：匹配非空白字符 [^\\s]。
         */
        String input = "Hello world!He\nllo, And\rroid. Hello Mi\tChat.";
        Map<String, Integer> result = countWords(input);
        System.out.println(result);
    }

    public static Map<String, Integer> countWords(String input) {
        // 处理空或 null 输入
        if (input == null || input.trim().isEmpty()) {
            return new HashMap<>();
        }

        // 初始化结果 Map
        Map<String, Integer> wordFreq = new LinkedHashMap<>();

        // 转换为小写，替换标点为空格，规范化空格
        String cleanedInput = input.toLowerCase()
                //将非字母字符替换为空格。
                /**
                 *
                 */
                .replaceAll("[^a-zA-Z\\s]", " ")
                //将多个空格替换为单个空格。
                .replaceAll("\\s+", " ")
                .trim();

        // 按空格分割并计数
        String[] words = cleanedInput.split(" ");
        for (String word : words) {
            if (!word.isEmpty()) {
                wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
            }
        }

        return wordFreq;
    }


}