package com.hm.algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by dumingwei on 2022/4/15.
 * <p>
 * Desc: 从 0 到 1000 随机取10个不同的数字。并从小到大打印出来。
 */
public class InterviewTest {


    public static void main(String[] args) {
        // 创建随机数生成器
        Random random = new Random();
        // 创建ArrayList存储随机数
        ArrayList<Integer> numbers = new ArrayList<>();

        // 生成10个不同的随机数
        while (numbers.size() < 10) {
            int num = random.nextInt(1001); // 生成0-1000的随机数
            if (!numbers.contains(num)) { // 确保不重复
                numbers.add(num);
            }
        }

        // 排序
        Collections.sort(numbers);

        // 打印结果
        for (int num : numbers) {
            System.out.println(num);
        }
    }

}
