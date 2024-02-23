package com.hm.clone;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private int age;
    private String username;

    private Dog dog;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

}