package com.hm.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by dumingwei on 2017/6/1.
 */
public class Person extends Animal implements Serializable {

    private String name;
    private int age;

    private boolean aBoolean = true;

    {
        System.out.println("构造代码块，b = " + aBoolean);
    }

    public Person() {
        super(false);
        // b=false;
        System.out.println("Person 构造函数");
    }

    public Person(String name, int age) {
        System.out.println("Person");
        this.name = name;
        this.age = age;
    }


    public boolean isB() {
        return aBoolean;
    }

    @Override
    void parse(boolean b) {
        aBoolean = b;

        System.out.println("parse aBoolean = " + aBoolean);
    }

    private void writeObject(ObjectOutputStream out) throws IOException {
        out.writeObject(new StringBuffer(name).reverse());
        out.writeInt(age);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        this.name = ((StringBuffer) in.readObject()).reverse().toString();
        this.age = in.readInt();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
