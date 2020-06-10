package com.hm.collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class ListTest2 {

    public static void main(String[] args) {

        List<String> booksX = new ArrayList<>();
        booksX.add(null);

        List<String> books = new ArrayList<>();
        books.add("轻量级");
        books.add("java");
        books.add("Android");
        /**
         * 使用集合转数组的方法，必须使用集合的 toArray(T[] array) ，传入的是类型完全
         *  一样的数组，大小就是 list . size() 。
         */
        String arr[] = new String[books.size()];
        books.toArray(arr);

       /* System.out.println(books);
        books.remove(new A());
        System.out.println(books);
        books.remove(new A());
        System.out.println(books);
*/
       /* Iterator<String> iterator = books.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.remove();
        }
        System.out.println(books);*/

        ListIterator<String> listIterator = books.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }
        while (listIterator.hasPrevious()) {
            System.out.println(listIterator.previous());
        }
    }

    static class A {
        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }
}
