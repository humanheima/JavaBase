package com.hm.lambda.quickstart;

import java.util.List;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class RoboContactMethods {

    public void callDrivers(List<Person> personList) {
        for (Person person : personList) {
            if (person.getAge() >= 16) {
                roboCall(person);
            }
        }
    }

    private void roboCall(Person person) {

    }

    public void emailDraftees(List<Person> personList) {
        for (Person p : personList) {
            if (p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE)
                roboEmail(p);
        }
    }

    private void roboEmail(Person p) {

    }

    public void mailPilots(List<Person> personList) {
        for (Person p : personList) {
            if (p.getAge() >= 23 && p.getAge() <= 65) {
                roboMail(p);
            }
        }
    }

    private void roboMail(Person person) {

    }
}
