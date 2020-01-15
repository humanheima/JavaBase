package com.hm.pattern.iterator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by dumingwei on 2017/6/30.
 * 迭代器模式：提供遍历集合的方法，并隐藏内部实现细节。（一句话：封装集合遍历）
 */
public class Test {


    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(list);
        HashMap<String, String> hashMap = new HashMap<>();
        Set set = hashMap.entrySet();
        Iterator<String> iterator1 = hashMap.keySet().iterator();
        while (iterator1.hasNext()) {
            String key = iterator1.next();
            System.out.println("key:" + key + ",value:" + hashMap.get(key));
        }
    }

}
