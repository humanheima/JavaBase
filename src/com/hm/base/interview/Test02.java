package com.hm.base.interview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dumingwei on 2017/10/1.
 */
public class Test02 {

    public static void main(String[] args) {
        List<Person> list = new ArrayList<>();
        list.add(new Person("Hao LUO", 33));
        list.add(new Person("XJ WANG", 32));
        list.add(new Person("Bruce LEE", 60));
        list.add(new Person("Bob YANG", 22));
        Comparator<Person> c = new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getName().compareTo(o2.getName());
            }
        };
        Collections.sort(list, c);
        for (Person person : list) {
            System.out.println(person);
        }
    }
}
