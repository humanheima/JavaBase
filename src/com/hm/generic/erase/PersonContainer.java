package com.hm.generic.erase;

/**
 * Created by dumingwei on 2020/10/16.
 * <p>
 * Desc:
 */
public class PersonContainer extends ObjectContainer<Person> {

    @Override
    public void setContained(Person contained) {
        super.setContained(contained);
    }

    @Override
    public Person getContained() {
        return super.getContained();
    }
}
