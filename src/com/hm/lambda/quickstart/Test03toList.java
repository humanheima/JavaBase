package com.hm.lambda.quickstart;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class Test03toList {

    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();
        SearchCriteria searchCriteria=SearchCriteria.getInstance();
        // Make a new list after filtering.
        List<Person>piloatList=pl.stream()
                .filter(searchCriteria.getCriteria(SearchCriteria.ALL_PILOTS))
                .collect(Collectors.toList());
        piloatList.forEach(Person::print);
    }
}
