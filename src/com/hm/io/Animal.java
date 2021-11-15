package com.hm.io;

import java.io.Serializable;

/**
 * Created by dumingwei on 2017/6/1.
 */
abstract public class Animal implements Serializable {

    protected int eyes;

    public Animal() {
    }

    public Animal(boolean b) {
        parse(b);
        System.out.println("父类构造函数");
    }

    public int getEyes() {
        return eyes;
    }

    public void setEyes(int eyes) {
        this.eyes = eyes;
    }

    abstract void parse(boolean b);
}
