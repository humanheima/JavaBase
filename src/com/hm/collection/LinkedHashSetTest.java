package com.hm.collection;

import java.util.LinkedHashSet;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class LinkedHashSetTest {

    public static void main(String[] args) {
        LinkedHashSet books=new LinkedHashSet();
        books.add("疯狂java讲义");
        books.add("轻量级JavaEE企业应用实战");
        System.out.println(books);
        books.remove("疯狂java讲义");
        books.add("疯狂java讲义");
        System.out.println(books);
    }
}
