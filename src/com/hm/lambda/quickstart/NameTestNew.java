package com.hm.lambda.quickstart;

import java.util.List;
import java.util.function.Function;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class NameTestNew {

    public static void main(String[] args) {
        List<Person> personList = Person.createShortList();

        for (Person person : personList) {
            System.out.println(person.printCustom(p -> "Name: " + p.getGivenName() + " EMail: " + p.geteMail()));
        }
        // Define Western and Eastern Lambdas
        Function<Person, String> westernStyle = p -> "westernStyle:" + p.getGivenName();
        Function<Person, String> easternStyle = p -> "easternStyle:" + p.getSurName();
        // Print Western List
        for (Person person : personList) {
            System.out.println(person.printCustom(westernStyle));
        }

        // Print Eastern List
        for (Person person : personList) {
            System.out.println(person.printCustom(easternStyle));
        }
    }

}
