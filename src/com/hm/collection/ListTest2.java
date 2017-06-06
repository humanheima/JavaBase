package com.hm.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class ListTest2 {

    public static void main(String[] args) {
        List books = new ArrayList();
        books.add(new String("轻量级"));
        books.add(new String("java"));
        books.add(new String("Android"));
        System.out.println(books);
        books.remove(new A());
        System.out.println(books);
        books.remove(new A());
        System.out.println(books);

    }

    static class A {
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }
}
