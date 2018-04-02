package com.hm.effective_java;

import java.util.Arrays;
import java.util.List;

/**
 * Created by dumingwei on 2017/9/5.
 */
public class Test {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("hello", "world");
        list.stream().forEach(System.out::println);
    }

}
