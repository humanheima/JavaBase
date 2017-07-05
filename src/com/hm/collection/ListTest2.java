package com.hm.collection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.function.Consumer;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class ListTest2 {

    public static void main(String[] args) {
        List<String> books = new ArrayList<>();
        books.add("轻量级");
        books.add("java");
        books.add("Android");
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

       ListIterator<String> listIterator=books.listIterator();
       while (listIterator.hasNext()){
           System.out.println(listIterator.next());
       }
       while (listIterator.hasPrevious()){
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
