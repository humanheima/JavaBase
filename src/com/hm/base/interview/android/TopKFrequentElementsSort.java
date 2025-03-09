package com.hm.base.interview.android;

import java.util.*;

/**
 * 问题描述
 * 给定一个整数数组 nums 和一个整数 k，返回数组中出现频率最高的前 k 个元素。可以按任意顺序返回答案。
 */
public class TopKFrequentElementsSort {

    public static void main(String[] args) {
        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;
        int[] result = topKFrequent(nums, k);
        System.out.println(Arrays.toString(result)); // 输出: [1, 2]
    }

    public static int[] topKFrequent(int[] nums, int k) {
        // 1. 统计每个元素的频率
        Map<Integer, Integer> frequencyMap = new HashMap<>();
        for (int num : nums) {
            //存储的是次数
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // 2. 将 HashMap 转换为 List 并按频率排序
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(frequencyMap.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue()); // 按频率降序排序

        // 3. 取前 k 个元素
        int[] result = new int[k];
        for (int i = 0; i < k; i++) {
            result[i] = list.get(i).getKey();
        }

        return result;
    }

}