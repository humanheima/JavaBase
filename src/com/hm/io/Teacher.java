package com.hm.io;

import java.io.Serializable;

/**
 * Created by dumingwei on 2017/6/1.
 */
public class Teacher implements Serializable{

    private String name;
    private Person student;

    public Teacher(String name, Person student) {
        this.name = name;
        this.student = student;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }
}
