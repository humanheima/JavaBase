package com.hm.collection;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Created by dumingwei on 2017/6/6.
 */
public class LinkedListTest {

    public static void main(String[] args) {
        LinkedList<String> books = new LinkedList<>();
        books.offer("疯狂java讲义");
        books.offer("java ee");
        books.offer("android");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("遍历中:" + books.get(i));
        }
        ListIterator<String> it = books.listIterator(0);
        while (it.hasNext()){
            System.out.println(it.next());
        }
       /* System.out.println("peekFirst:" + books.peekFirst());
        System.out.println("peekLast:" + books.peekLast());
        System.out.println(books.pop());
        System.out.println(books);
        System.out.println(books.pollLast());
        System.out.println(books);*/
    }
}
