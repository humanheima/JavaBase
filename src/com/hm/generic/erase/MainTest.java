package com.hm.generic.erase;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by dumingwei on 2020/10/16.
 * <p>
 * Desc:
 */
public class MainTest {

    public static void main(String[] args) {
        // list 定义时未指定泛型,实际上集合中存储为Integer类型
        List list = new ArrayList<Integer>();
        list.add(1);

        // 将无泛型的list对象,赋值给指定类型的strList变量, 此处已经发生堆污染
        List<String> strList = list;

        // 抛出异常 java.lang.ClassCastException
        String str = strList.get(0);

    }


    public static void function(List<String> ... strListArray) {
        // java 语言不允许创建泛型数组,所以只能使用不带泛型的数组List[] 接收参数
        List[] listArray = strListArray;

        // 创建一个指定泛型的list
        List<Integer> intList = Arrays.asList(1);

        // 发生堆污染: listArray[0] 为无泛型List, intList 为有泛型List, 此处发生堆污染
        listArray[0] = intList;

        // 抛出异常
        String str = strListArray[0].get(0);
    }
}



