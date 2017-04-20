package com.hm.lambda.quickstart;

import java.util.List;
import java.util.function.Predicate;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class RoboCallTest04 {

    public static void main(String[] args) {
        List<Person> personList = Person.createShortList();

        RoboContactLambda robo = new RoboContactLambda();

        // Predicates
        Predicate<Person> allDrivers = p -> p.getAge() >= 16;
        Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
        Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;

        robo.phoneContacts(personList, allDrivers);
        robo.emailContacts(personList, allDraftees);
        robo.mailContacts(personList, allPilots);
        robo.mailContacts(personList, allDraftees);
        robo.phoneContacts(personList, allPilots);

    }
}
