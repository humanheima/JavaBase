package com.hm.base.interview;

import java.util.Set;
import java.util.TreeSet;

/**
 * Created by dumingwei on 2017/10/1.
 */
public class Test01 {

    public static void main(String[] args) {
        Set<Student> set = new TreeSet<>();
        set.add(new Student("Hao LUO", 33));
        set.add(new Student("XJ WANG", 32));
        set.add(new Student("Bruce LEE", 60));
        set.add(new Student("Bob YANG", 22));
        for (Student student : set) {
            System.out.println(student);
        }
    }
}
