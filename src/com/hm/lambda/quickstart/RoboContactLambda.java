package com.hm.lambda.quickstart;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class RoboContactLambda {

    public void phoneContacts(List<Person> personList, Predicate<Person> pred) {
        for (Person person : personList) {
            if (pred.test(person)) {
                roboCall(person);
            }
        }
    }

    private void roboCall(Person person) {
        System.out.println("roboCall" + person.getSurName());
    }

    public void emailContacts(List<Person> personList, Predicate<Person> pred) {
        for (Person person : personList) {
            if (pred.test(person)) {
                roboEmail(person);
            }
        }
    }

    private void roboEmail(Person person) {
        System.out.println("roboEmail" + person.getSurName());
    }

    public void mailContacts(List<Person> personList, Predicate<Person> pred) {
        for (Person person : personList) {
            if (pred.test(person)) {
                roboMail(person);
            }
        }
    }

    private void roboMail(Person person) {
        System.out.println("roboMail" + person.getSurName());
    }
}
