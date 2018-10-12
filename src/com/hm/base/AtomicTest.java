package com.hm.base;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by dmw on 2018/10/11.
 * Desc: 学习AtomicReference
 */
public class AtomicTest {

    public static void main(String[] args) {
        Person person1 = new Person(101);
        Person person2 = new Person(102);
        AtomicReference<Person> atomicReference = new AtomicReference<>(person1);
        atomicReference.compareAndSet(person1, person2);
        Person person3 = atomicReference.get();
        System.out.println("person3:" + person3);
        System.out.println("person1 equals person3:" + person1.equals(person3));
    }

}

class Person {
    volatile long id;

    public Person(long id) {
        this.id = id;
    }

    public String toString() {
        return "id:" + id;
    }
}



