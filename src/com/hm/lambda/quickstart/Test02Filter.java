package com.hm.lambda.quickstart;

import java.util.List;

/**
 * Created by dumingwei on 2017/3/11.
 */
public class Test02Filter {

    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();
        SearchCriteria search = SearchCriteria.getInstance();
        pl.stream()
                .filter(search.getCriteria(SearchCriteria.ALL_DRIVERS))
                .forEach(Person::printWesternName);
        pl.stream()
                .filter(search.getCriteria(SearchCriteria.ALL_PILOTS))
                .forEach(Person::printEasternName);
    }
}
