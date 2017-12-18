package com.hm.generic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dumingwei on 2017/11/29 0029.
 */
public class New {

    public static <K, V> Map<K, V> map() {
        return new HashMap<>();
    }

    public <T> List<T> list() {
        return new ArrayList<>();
    }

    public void test() {
        //显式类型说明
        List<String> list = this.<String>list();
        Map<String, List<String>> sls = New.<String, List<String>>map();

        List<String> list1 = this.list();
        Map<String, List<String>> sls1 = New.map();
    }


    public static void f(Map<String, List<? extends String>> sls) {
        System.out.println(sls.getClass().getSimpleName());
    }

    public static void main(String[] args) {
        Map<String, List<String>> sls = New.map();
    }
}
