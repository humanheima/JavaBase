package com.hm.lambda.quickstart;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class ComparatorTest {

    public static void main(String[] args) {
        List<Person> personList = Person.createShortList();
        Collections.sort(personList, new Comparator<Person>() {
            @Override
            public int compare(Person p1, Person p2) {
                return p1.getSurName().compareTo(p2.getSurName());
            }
        });
        Collections.sort(personList, (p1, p2) -> p1.getSurName().compareTo(p2.getSurName()));

        for (Person person : personList) {
            System.out.println(person.getSurName());
        }
    }
}
