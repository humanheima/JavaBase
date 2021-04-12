package com.hm.generic.erase;

/**
 * Created by dumingwei on 2021/4/11.
 * <p>
 * Desc:
 */
public abstract class AbsDeveloper {

    public String name;
    public int age;

    public AbsDeveloper(String name, int age) {
        this.name = name;
        this.age = age;
    }


    @Override
    public String toString() {
        return "AbsDeveloper{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public abstract String workType();

}
