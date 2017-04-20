package com.hm.parallel;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by dumingwei on 2017/3/13.
 * 并行数组
 */
public class ParallelArrayTest {

    public static void main(String args[]) {

        long arrays[] = new long[20000];
        Arrays.parallelSetAll(arrays, index -> ThreadLocalRandom.current().nextInt(100000));
        Arrays.stream(arrays)
                .limit(10)
                .forEach(i -> System.out.println("i=" + i));
        Arrays.parallelSort(arrays);
        Arrays.stream(arrays)
                .limit(10)
                .forEach(i -> System.out.println("i=" + i));
    }
}