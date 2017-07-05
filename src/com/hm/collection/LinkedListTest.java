package com.hm.collection;

import java.util.*;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<String> books = new LinkedList<>();
        books.offer("疯狂java讲义");
        books.offer("java ee");
        books.offer("android");
        books.remove();
        books.remove(1);
        books.remove("android");
        ListIterator<String> it = books.listIterator(0);
        while (it.hasNext()){
            System.out.println(it.next());
        }
        List<String> strings = new ArrayList<>();
        strings.add("新元素1");
        strings.add("新元素2");
        books.addAll(strings);
        ListIterator<String> it1 = books.listIterator(0);
        while (it1.hasNext()){
            System.out.println(it1.next());
        }
        System.out.println("peekFirst:" + books.peekFirst());
        System.out.println("peekLast:" + books.peekLast());
        System.out.println(books.pop());
        System.out.println(books);
        System.out.println(books.pollLast());
        books.clear();
        System.out.println(books);
    }
}
