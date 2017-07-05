package com.hm.reflect;

import java.lang.reflect.Field;
import java.util.concurrent.Executors;

/**
 * Created by dumingwei on 2017/6/25.
 */
public class FieldTest {

    public static void main(String[] args) throws Exception {
        Person p = new Person();
        Class<Person> personClazz = Person.class;
        Field nameField = personClazz.getDeclaredField("name");
        nameField.setAccessible(true);
        nameField.set(p, "HELLO");
        Field ageField = personClazz.getDeclaredField("age");
        ageField.setAccessible(true);
        ageField.set(p, 30);
        System.out.println(p);

    }

    static class Person {

        private String name;
        private int age;

        @Override
        public String toString() {
            return "Person[name:" + name + ",age:" + age + "]";
        }
    }
}


