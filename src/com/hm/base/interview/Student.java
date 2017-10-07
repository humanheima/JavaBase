package com.hm.base.interview;

/**
 * Created by dumingwei on 2017/10/1.
 */
public class Student implements Comparable<Student> {

    private String name;
    private int age;

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public int compareTo(Student o) {
        return this.age - o.age;
    }

    @Override
    public String toString() {
        return "Student [name=" + name + ", age=" + age + "]";
    }

}
