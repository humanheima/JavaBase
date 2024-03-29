package com.hm.lambda.quickstart;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class SearchCriteria {

    public static final String ALL_DRIVERS = "allDrivers";
    public static final String ALL_DRAFTEES = "allDraftees";
    public static final String ALL_PILOTS = "allPilots";
    
    private final Map<String, Predicate<Person>> searchMap = new HashMap<>();

    private SearchCriteria() {
        super();
        initSearchMap();
    }

    private void initSearchMap() {
        Predicate<Person> allDrivers = p -> p.getAge() >= 16;
        Predicate<Person> allDraftees = p -> p.getAge() >= 18 && p.getAge() <= 25 && p.getGender() == Gender.MALE;
        Predicate<Person> allPilots = p -> p.getAge() >= 23 && p.getAge() <= 65;

        searchMap.put(ALL_DRIVERS, allDrivers);
        searchMap.put(ALL_DRAFTEES, allDraftees);
        searchMap.put(ALL_PILOTS, allPilots);

    }

    public Predicate<Person> getCriteria(String PredicateName) {
        Predicate<Person> target;

        target = searchMap.get(PredicateName);

        if (target == null) {

            System.out.println("Search Criteria not found... ");
            System.exit(1);

        }

        return target;

    }

    public static SearchCriteria getInstance() {
        return new SearchCriteria();
    }
}