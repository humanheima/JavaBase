package com.hm.algorithm;

import java.util.*;

/**
 * Created by dumingwei on 2022/5/5
 * <p>
 * Desc:奇数位丢弃，感觉牛客网上的题目描述根本不清楚。
 */
public class DisCardOddNumber {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int n = input.nextInt();
            int result = getRes(n);
            System.out.println(result);
        }
    }

    // 约瑟夫环问题
    private static int getRes(int n) {
        List<Integer> list = new LinkedList<Integer>();
        for (int i = 0; i <= n; i++)
            list.add(i);
        while (list.size() != 1) {
            // 从0开始list移除一次,i再加一次,i始终指向奇数位
            for (int i = 0; i < list.size(); i = i + 1)
                list.remove(i);
        }
        return list.get(0);
    }
}