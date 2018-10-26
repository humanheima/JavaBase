package com.hm.parallel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

/**
 * Created by dumingwei on 2017/3/13.
 * 并行数组
 */
public class ParallelArrayTest {

    public static void main(String args[]) {

        /*long arrays[] = new long[20000];
        Arrays.parallelSetAll(arrays, index -> ThreadLocalRandom.current().nextInt(100000));
        Arrays.stream(arrays)
                .limit(10)
                .forEach(i -> System.out.println("i=" + i));
        Arrays.parallelSort(arrays);
        Arrays.stream(arrays)
                .limit(10)
                .forEach(i -> System.out.println("i=" + i));*/
        testParallelism();
    }

    private static void testParallelism() {
        List<String> result = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            result.add("i = " + i);
        }
        result.parallelStream().forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(Thread.currentThread().getName() + "," + s);
            }
        });
    }
}