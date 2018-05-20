package com.hm.reflect;

public class Person extends Animal implements RunInterface, LiveInterface {

    private int age;
    private String msg = "good morning 2017";

    public Person() {
    }

    public Person(String name) {
        this.name = name;
        System.out.println(name);
    }

    private Person(int age, String name) {
        this.age = age;
        this.name = name;
        System.out.println(name);
    }

    @Override
    public int getEyes() {
        return super.getEyes();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void fun() {
        System.out.println("fun");
    }

    private void info(String name, int age) {
        System.out.printf("ReflectActivity 我叫" + name + ",今年" + age + "岁");
    }

    @Override
    public void run() {

    }

    @Override
    public void jump() {

    }

    @Override
    public void eat() {

    }

    @Override
    public void sleep() {

    }
}