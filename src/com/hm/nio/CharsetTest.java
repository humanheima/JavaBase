package com.hm.nio;

import java.nio.charset.Charset;
import java.util.SortedMap;

/**
 * Created by dumingwei on 2017/10/16.
 */
public class CharsetTest {

    public static void main(String[] args) {
        //jdk支持的所有字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        for (String name : map.keySet()) {
            System.out.println("name=" + name + ",value=" + map.get(name));
        }
    }
}
