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
 *
 * 单词出现次数.md
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

        Map<String, Integer> result2 = countWords2(input);
        System.out.println(result2);
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


    /**
     * 使用了正则表达式
     *
     * @param input
     * @return
     */
    public static Map<String, Integer> countWords2(String input) {
        // Handle null or empty input
        if (input == null || input.trim().isEmpty()) {
            return new HashMap<>();
        }

        // Initialize result map
        Map<String, Integer> wordFreq = new LinkedHashMap<>();

        // Convert to lowercase and prepare for manual cleaning
        StringBuilder cleaned = new StringBuilder();
        for (char c : input.toLowerCase().toCharArray()) {
            // Keep only letters and spaces
            if ((c >= 'a' && c <= 'z')) {
                cleaned.append(c);
            } else {
                //标点符号和空格都替换为空格
                cleaned.append(' ');
            }
        }

        // Split by spaces and process words
        String[] words = cleaned.toString().split("\\s+");

        // Count frequency of each word
        for (String word : words) {

            if (wordFreq.containsKey(word)) {
                int times = wordFreq.get(word);
                wordFreq.put(word, times + 1);
            } else {
                wordFreq.put(word, 1);
            }
        }

        return wordFreq;
    }


    /**
     * todo 使用这种算法
     * @param input
     * @return
     */
    public Map<String, Integer> countWords3(String input) {
        // 处理空输入或 null
        if (input == null || input.trim().isEmpty()) {
            return new HashMap<>();
        }

        // 初始化结果映射
        Map<String, Integer> wordFreq = new HashMap<>();

        // 转换为小写并清理标点符号
        StringBuilder cleaned = new StringBuilder();
        for (char c : input.toLowerCase().toCharArray()) {
            if (c >= 'a' && c <= 'z') { // 仅保留小写字母
                cleaned.append(c);
            } else {
                cleaned.append(' '); // 将标点替换为空格
            }
        }

        // 手动按空格分隔单词并计数
        StringBuilder currentWord = new StringBuilder();
        for (char c : cleaned.toString().toCharArray()) {
            if (c == ' ') {
                // 单词结束，注意有可能多个空格连着，比如原始字符串是 "Hello! World" 是这种。
                if (currentWord.length() > 0) {
                    String word = currentWord.toString();
                    wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
                    currentWord.setLength(0); // 重置当前单词
                }
            } else {
                currentWord.append(c); // 构建当前单词
            }
        }

        // 处理最后一个单词
        if (currentWord.length() > 0) {
            String word = currentWord.toString();
            wordFreq.put(word, wordFreq.getOrDefault(word, 0) + 1);
        }

        return wordFreq;
    }


}
