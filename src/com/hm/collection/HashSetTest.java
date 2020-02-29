package com.hm.collection;

import java.util.HashSet;
import java.util.Iterator;

/**
 * Created by dumingwei on 2017/6/5.
 */
public class HashSetTest {

    public static void main(String[] args) {
        HashSet books = new HashSet();
        books.add(new A());
        books.add(new A());
        books.add(new B());
        books.add(new B());
        books.add(new C());
        books.add(new C());

        Iterator iterator = books.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
        System.out.println(books);
    }

    static class A {

        @Override
        public boolean equals(Object obj) {
            return true;
        }
    }

    static class B {

        @Override
        public int hashCode() {
            return 1;
        }
    }

    static class C {

        @Override
        public boolean equals(Object obj) {
            return true;
        }

        @Override
        public int hashCode() {
            return 2;
        }
    }

}
