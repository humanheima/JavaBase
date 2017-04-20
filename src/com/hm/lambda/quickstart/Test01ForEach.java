package com.hm.lambda.quickstart;

import java.util.List;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class Test01ForEach {

    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();
        pl.forEach(p -> p.printWesternName());
        pl.forEach(Person::printEasternName);
        pl.forEach(p -> {
            System.out.println(p.printCustom(r -> "name:" + r.getGivenName()));
        });
    }

}
