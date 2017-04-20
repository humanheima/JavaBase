package com.hm.lambda.quickstart;

import java.util.List;
import java.util.OptionalDouble;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class Test04Map {

    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();
        SearchCriteria search = SearchCriteria.getInstance();
        int sum = 0;
        int count = 0;

        for (Person p : pl) {
            if (p.getAge() >= 23 && p.getAge() <= 65) {
                sum = sum + p.getAge();
                count++;
            }
        }

        long average = sum / count;
        System.out.println("Total Ages: " + sum);
        System.out.println("Average Age: " + average);

        // Get sum of ages
        long totalAge = pl
                .stream()
                .filter(search.getCriteria(SearchCriteria.ALL_PILOTS))
                .mapToInt(p -> p.getAge())
                .sum();
        // Get sum of ages
        OptionalDouble avarageAge = pl
                .parallelStream()
                .filter(search.getCriteria(SearchCriteria.ALL_PILOTS))
                .mapToDouble(p -> p.getAge())
                .average();
        System.out.println("Total Ages: " + totalAge);
        System.out.println("Average Age: " + avarageAge);
    }
}
